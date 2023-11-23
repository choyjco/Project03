package ddit.project03.sec01.service;

import java.util.Scanner;

import ddit.project03.sec01.dao.ReservationDao;
import ddit.project03.sec01.dao.SeatDao;
import ddit.project03.sec01.util.LoginUserNo;
import ddit.project03.sec01.util.ScanUtil;
import ddit.project03.sec01.util.SpaceUtil;
import ddit.project03.sec01.util.View;
import ddit.project03.sec01.vo.ReservationVO;

public class SeatService {
    private static SeatService instance = null;

    private SeatService() {}

    public static SeatService getInstance() {
        if (instance == null)
            instance = new SeatService();
        return instance;
    }
    static ReservationDao rseDao = ReservationDao.getInstance();
    static SeatDao seatDao = SeatDao.getInstance();
    
    //열람실 이용
    public int seatUse() {
       System.out.println();
       System.out.println(" ───────────────────────────────────────────────────────────────────");
       System.out.println(SpaceUtil.format("                       열람실 이용",85,0));
       System.out.println(" ───────────────────────────────────────────────────────────────────");
       System.out.println();
       System.out.println(SpaceUtil.format("1. 좌석 이용",65,0));
       System.out.println(SpaceUtil.format("2. 좌석 이용 취소",70,0));
       System.out.println(SpaceUtil.format("9. 홈으로",61,0));
       System.out.println();
       System.out.println(" ───────────────────────────────────────────────────────────────────");
       System.out.println();
       System.out.print(" >> 번호 입력 : ");
       
       switch(ScanUtil.nextInt()) {
       case 1: return View.SEAT_INQUIRY;
       case 2: return View.SEAT_CANCEL_RES;
       case 9: return View.MEM_HOME;
       default:
          System.out.println("번호를 잘못 입력하셨습니다.");
          return View.READING_ROOM;
       } 
    }
    
    //좌석 조회하기, 회원용  
    public int currSeat() {
        int[][] seats = new int[5][8];      
        System.out.println();
        System.out.println(" *******************************************************************");
        System.out.println(SpaceUtil.format("           현재 좌석 현황                 ",120,0));
        System.out.println(" *******************************************************************");
        System.out.println();
        System.out.println(SpaceUtil.format(" 이용가능 : □", 112, 1));
        System.out.println(SpaceUtil.format("  불가능  : ■", 112, 1));
     
        System.out.println("────────────────────────────────────────────────────────────────────");

        seatDao.currSeatStatus(seats);

        for (int i = 0; i < seats[0].length; i++) { 
            System.out.print("" + (i + 1) + "  ");
        }
        System.out.println(" [ 열 ] "); 

        for (int i = 0; i < seats.length; i++) {
            System.out.println();
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.print("■  ");
                } else {
                    System.out.print("□  ");
                }
            }
            System.out.println(" [ " + (char) (i + 65) + " ] 행"); 
        }       
        System.out.println();
        System.out.println(" ───────────────────────────────────────────────────────────────────");
        System.out.println();
        System.out.println(SpaceUtil.format("1. 좌석 이용 하기 ",70,0));
        System.out.println(SpaceUtil.format("2. 뒤로 가기 ",65,0));  
        System.out.println();
        System.out.println(" ───────────────────────────────────────────────────────────────────");
        System.out.println();
        System.out.print(" >> 번호 입력 : ");
        switch (ScanUtil.nextInt()) {
            case 1:
                return View.SEAT_RESERVATION;
            case 2:
                return View.READING_ROOM;
            default:
                System.out.println("번호를 잘못 입력하셨습니다.");
                return View.SEAT_INQUIRY;
        }         
    }

  //좌석 조회하기, 관리자  
    public int admcurrSeat() {
    	int[][] seats = new int[5][8];      
        
        System.out.println(" *******************************************************************");
        System.out.println(SpaceUtil.format("           현재 좌석 현황                 ",120,0));
        System.out.println(" *******************************************************************");
        System.out.println(SpaceUtil.format(" 이용가능 : □", 80, 1));
        System.out.println(SpaceUtil.format("  불가능  : ■", 80, 1));
        System.out.println("─────────────────────────────────────────────────");

        seatDao.currSeatStatus(seats);

        for (int i = 0; i < seats[0].length; i++) { 
            System.out.print("" + (i + 1) + "  ");
        }
        System.out.println(" [ 열 ] "); 

        for (int i = 0; i < seats.length; i++) {
            System.out.println();
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.print("■  ");
                } else {
                    System.out.print("□  ");
                }
            }
            System.out.println(" [ " + (char) (i + 65) + " ] 행"); 
        } 
        System.out.println();
        System.out.println(" ───────────────────────────────────────────────────────────────────");
        System.out.println();
 	   System.out.print(" >> 확인 : enter");
        System.out.println();
        System.out.println(" ───────────────────────────────────────────────────────────────────");
        ScanUtil.nextLine();         
        return View.ADMIN_HOME;
    }

    

    
       //좌석 등록 하기
    public int rseSeat() {
        int[][] seats = new int[5][8];
        boolean isRun = true;
        String strColumn;//사용자로부터 입력받는 컬럼=행(A~E)의 값
        char inputColumn = 0;//입력받은 행의 값을 Char 타입으로 받음(유니코드로 변환하여 비교연산자로 사용하기 위해=>A~E외의 값은 받지않기 위해)
        int rowNum;//입력받는 열의 값
        String seatNo;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("=================================================");
            System.out.println("\n\n");
            System.out.println("\t\t   <<좌석등록>>\n");
            System.out.println("=================================================");
            System.out.println(SpaceUtil.format(" 이용가능 : □", 80, 1));
            System.out.println(SpaceUtil.format("  불가능  : ■", 80, 1));
            System.out.println("─────────────────────────────────────────────────");

            seatDao.currSeatStatus(seats);

            for (int i = 0; i < seats[1].length; i++) {
                System.out.print("" + (i + 1) + "  ");
            }
            System.out.println(" [ 열 ] ");

            for (int i = 0; i < seats.length; i++) {
                System.out.println();
                for (int j = 0; j < seats[i].length; j++) {
                    if (seats[i][j] == 0) {
                        System.out.print("■  ");
                    } else {
                        System.out.print("□  ");
                    }
                }
                System.out.println(" [ " + (char) (i + 65) + " ] 행");
            }

            System.out.println(" ───────────────────────────────────────────────────────────────────");
            System.out.print("\n예약하실 좌석의 행을 입력해주세요 : ");
            strColumn = sc.next();
            inputColumn = strColumn.trim().charAt(0);//trim=문자열 앞뒤의 공백 제거, charAt(0)=문자열 첫번째 문자 추출 => 추출한 첫번째 문자를 inputColum에 저장

            if (inputColumn < 'A' || inputColumn > 'E') {//유니코드 65보다 작거나, 69보다 크면 선택할 수 없는 행이다=>오류다
                System.out.println("***********************************");
                System.out.println("***********************************");
                System.out.println("\n\n\t※선택할 수 없는 행입니다.\n\n");
                System.out.println("***********************************");
                System.out.println("***********************************");
                continue;
    }
     System.out.print("예약하실 좌석의 열 번호를 입력해주세요 : ");
        rowNum = Integer.parseInt(sc.next());//입력받는 열의 번호를 정수형으로 바꿔서 int타입인 rowNum에 입력

        if (rowNum < 1 || rowNum > seats[0].length) {//입력 받은 열의 번호가 1보다 작거나 8보다 크면 오류다
            System.out.println("***********************************");
            System.out.println("***********************************");
            System.out.println("\n\n\t※선택할 수 없는 열 번호입니다.\n\n");
            System.out.println("***********************************");
            System.out.println("***********************************");
            continue;
        }
        
        if (seats[inputColumn - 'A'][rowNum - 1] == 0) {//입력받는 행과 열에서 각 'A'와 '1'을 빼줘야 실질적 배열의 주소가 나옴. 그 놈의 값이 0이면 이미 예약되어 있는 좌석
           //DB에서 현재 상태현황을 O와 X로 표시해놨는데 O로 표시되어 있으면 배열의 값에 1을 넣어주고, X로 되어있는 놈은 0을 넣어줌
            System.out.println("***********************************");
            System.out.println("***********************************");
            System.out.println("\n\n\t※이미 사용중인 좌석입니다.\n\t다른 좌석을 선택해 주세요.\n");
            System.out.println("***********************************");
            System.out.println("***********************************");
            continue;
        }
       //위의 오류 if문을 전부 통과했다면 아래 안내문 실행
        System.out.println();
        System.out.println("=================================================");
        System.out.println("선택하신 좌석은 : " + inputColumn + " 열이고 " + rowNum + " 행입니다.");
        System.out.print("\n   >> 예약 완료 하시겠습니까 ? (Y/N) : ");
       
        
        String s = sc.next();//최종 대답을 입력받을 s
        if (s.equalsIgnoreCase("Y")) {//입력받은 s를 대소문자 구분없이 "y"랑 같으면
            seats[inputColumn - 'A'][rowNum - 1] = 1;//입력받은 행과 열에 a와 1을 뺀 실질적 
            
            seatNo = String.valueOf(inputColumn) + String.valueOf(rowNum);                    
            
//            LoginUserNo.getInstance().setLoggedInUserNo(loggedInUserNo)
            String userNo = LoginUserNo.getInstance().getLoggedInUserNo();            
            
            ReservationDao rseDao = ReservationDao.getInstance();
       
            boolean isAlreadyReserved2 = rseDao.checkReservationDuplicate(userNo);
            if (isAlreadyReserved2) {
               System.out.println("\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n");
                System.out.println("하루에 한 번만 예약할 수 있습니다.\n 내일 다시 이용해 주세요");
               System.out.println("\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n");
                return View.READING_ROOM;
            }
            
            ReservationVO reservation = new ReservationVO();
            reservation.setMem_no(userNo);
            
            this.rseDao.insertReservation(reservation, seatNo);
            System.out.print("=================================================");
            System.out.println("\n>>완료되었습니다.");       
            return View.MEM_HOME;
        } else {
            System.out.println("취소되었습니다.");
            isRun = false;
            return View.MEM_HOME;
        }
    } while (isRun);
        
        return View.MEM_HOME;
    }    
    


    public int rseCheckout() {
        Scanner sc = new Scanner(System.in);
        String userNo = LoginUserNo.getInstance().getLoggedInUserNo();
        ReservationVO reservation = new ReservationVO();
        reservation.setMem_no(userNo);
        System.out.println();
        System.out.println(" ───────────────────────────────────────────────────────────────────");
        System.out.println(SpaceUtil.format("           좌석 이용 취소                 ",120,0));
  	   	System.out.println(" ───────────────────────────────────────────────────────────────────");
  	   	System.out.println();
        System.out.print(" >> 퇴실하시겠습니까? (Y/N) :");
        String s = sc.next();
 

        if (s.equalsIgnoreCase("Y")) {
            SeatDao seatDao = SeatDao.getInstance();
            int result = seatDao.checkout(reservation);
            if (result > 0) {
                System.out.println("======================================");
                System.out.println("\n퇴실 처리 되었습니다.\n");
                System.out.println("======================================");
                return View.MEM_HOME;
            } else {
                System.out.println("======================================");
                System.out.println("\n등록되어 있는 정보가 없습니다.\n"); // 수정된 부분
                System.out.println("======================================");
                return View.READING_ROOM;
            } 
        } else if (s.equalsIgnoreCase("N")) {
         System.out.println(" >> 퇴실 작업을 취소합니다.");
         System.out.println();
  	   System.out.print(" >> 확인 : enter");
 		ScanUtil.nextLine();
        } else {
         System.out.println(">> 잘못 입력하셨습니다.");
        }
        return View.READING_ROOM;
    }

}