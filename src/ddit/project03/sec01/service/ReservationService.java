package ddit.project03.sec01.service;

import java.util.List;

import ddit.project03.sec01.dao.MemberDao;
import ddit.project03.sec01.dao.ReservationDao;
import ddit.project03.sec01.util.ScanUtil;
import ddit.project03.sec01.util.SpaceUtil;
import ddit.project03.sec01.util.View;
import ddit.project03.sec01.vo.MemberVO;
import ddit.project03.sec01.vo.ReservationVO;

public class ReservationService {

   private static ReservationService instance =null;
   private ReservationService() {}
   public static ReservationService getInstance() {
      if(instance==null)
         instance= new ReservationService();
      return instance;
   }
   
   static ReservationDao rseDao = ReservationDao.getInstance();
   static MemberDao memDao = MemberDao.getInstance();
   
   public int readingRoomManager() {
      System.out.println();
      System.out.println(" ───────────────────────────────────────────────────────────────────");
	  System.out.println(SpaceUtil.format("                       열람실 관리",100,0));
      System.out.println(" ───────────────────────────────────────────────────────────────────");
	  System.out.println(SpaceUtil.format("1. 좌석 현황",65,0));
	  System.out.println(SpaceUtil.format("2. 열람실 예약 현황 조회",76,0));
	  System.out.println(SpaceUtil.format("9. 홈으로",61,0));
      System.out.println(" ───────────────────────────────────────────────────────────────────");
       System.out.println();
       System.out.print(" >> 번호 입력 : ");
       
       switch (ScanUtil.nextInt()) {
       case 1: return View.ADM_SEAT_INQUIRY;
       case 2: return View.SEAT_RES_LIST;
       case 9: return View.ADMIN_HOME;
       default:
            System.out.println(" >> 번호를 잘못 입력하셨습니다.");
            return View.READIND_ROOM_MANAGER;   
   }
}
   public int seatList() {
       System.out.println();
       System.out.println(" ───────────────────────────────────────────────────────────────────");
 	   System.out.println(SpaceUtil.format("           열람실 예약 정보 조회                 ",70,0));
       System.out.println(" ───────────────────────────────────────────────────────────────────");

       List<ReservationVO> reservations = rseDao.getAllReservations();

       if (reservations.isEmpty()) {
           System.out.println(" >> 예약 정보가 없습니다.");
       } else {
           for (ReservationVO reservation : reservations) {
               MemberVO member = reservation.getMember(); // 예약에 대한 회원 정보 가져오기

               if (member != null) {
            	   System.out.println();
            	   System.out.println("회원명: " + member.getMem_name());
               } else {
                   System.out.println("회원명: 회원 정보 없음");
               }
               System.out.println("좌석 번호: " + reservation.getSeat_no());
               System.out.println("시작 시간: " + reservation.getRse_start_time());
               System.out.println("종료 시간: " + reservation.getRse_end_time());
               System.out.println("예약 번호: " + reservation.getRse_no());
               System.out.println();
               System.out.println(" ───────────────────────────────────────────────────────────────────");
           }
       }
	   System.out.print(" >> 확인 : enter");
		ScanUtil.nextLine();
        return View.READIND_ROOM_MANAGER;
   }

   public int myResInfo() {
      System.out.println();
      System.out.println(" *******************************************************************");
      System.out.println(SpaceUtil.format("           나의 열람실 이용 내역                 ",120,0));
      System.out.println(" *******************************************************************");

      List<ReservationVO> reservations = rseDao.myResInfo();

      if (reservations.isEmpty()) {
           System.out.println(" >> 예약 정보가 없습니다.");
    	   System.out.print(" >> 확인 : enter");
           ScanUtil.nextLine();
           System.out.println();
           
       } else {
           for (ReservationVO reservation : reservations) {
        	   System.out.println();
               System.out.println("회원명: " + reservation.getMember().getMem_name());
               System.out.println("좌석 번호: " + reservation.getSeat_no());
               System.out.println("시작 시간: " + reservation.getRse_start_time());
               System.out.println("종료 시간: " + reservation.getRse_end_time());
               System.out.println("예약 번호: " + reservation.getRse_no());
               System.out.println();
               System.out.println(" -------------------------------------------------------------------");

           }
       }    System.out.print(" >> 확인 : enter");

			ScanUtil.nextLine();
			return View.MYPAGE;
}
}