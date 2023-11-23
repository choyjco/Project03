package ddit.project03.sec01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ddit.project03.sec01.service.MemberService;
import ddit.project03.sec01.util.JDBCUtil02;
import ddit.project03.sec01.util.LoginUserNo;
import ddit.project03.sec01.vo.MemberVO;
import ddit.project03.sec01.vo.ReservationVO;

public class ReservationDao {
   private static ReservationDao instance= null;
   private ReservationDao() {}
   MemberVO memVO = new MemberVO();
   
   public static ReservationDao getInstance() {
      if(instance==null) instance= new ReservationDao();
      return instance;
   }
   
   //열람실 예약정보  조회(관리자용)
   public List<ReservationVO> getAllReservations() {
       List<ReservationVO> reservations = new ArrayList<>();

       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       try {
           conn = JDBCUtil02.getConnection();
           String sql = "SELECT M.MEM_NAME, R.SEAT_NO, R.RSE_START_TIME, R.RSE_END_TIME, R.RSE_NO " +
                   "FROM MEMBER M, RESERVATION R " +
                   "WHERE M.MEM_NO = R.MEM_NO " +
                   "ORDER BY R.RSE_START_TIME";
           pstmt = conn.prepareStatement(sql);
           rs = pstmt.executeQuery();

           while (rs.next()) {
               MemberVO member = new MemberVO();
               member.setMem_name(rs.getString("MEM_NAME"));
               
               ReservationVO reservation = new ReservationVO();
               reservation.setSeat_no(rs.getString("SEAT_NO"));
               reservation.setRse_start_time(rs.getString("RSE_START_TIME"));
               reservation.setRse_end_time(rs.getString("RSE_END_TIME"));
               reservation.setRse_no(rs.getString("RSE_NO"));
               reservation.setMember(member);

               reservations.add(reservation);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           JDBCUtil02.close(rs);
           JDBCUtil02.close(pstmt);
           JDBCUtil02.close(conn);
       }

       return reservations;
   }
   
     // 나의 열람실 예약 정보 조회(회원용)
   public List<ReservationVO> myResInfo(){
       List<ReservationVO> reservations = new ArrayList<>();
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       
        try {
          conn = JDBCUtil02.getConnection();
          String sql = "SELECT M.MEM_NAME, R.SEAT_NO, R.RSE_START_TIME, R.RSE_END_TIME, R.RSE_NO " +
                  "FROM MEMBER M, RESERVATION R " +
                  "WHERE M.MEM_NO = R.MEM_NO AND M.MEM_NO = ?" +
                  "ORDER BY R.RSE_START_TIME";
          pstmt = conn.prepareStatement(sql);
          MemberService memService = MemberService.getInstance();
          LoginUserNo loginUser= LoginUserNo.getInstance();
          String userNo = loginUser.getLoggedInUserNo();
          pstmt.setString(1, userNo);
           rs = pstmt.executeQuery();
           
           while (rs.next()) {
               MemberVO member = new MemberVO();
               member.setMem_name(rs.getString("MEM_NAME"));

               ReservationVO reservation = new ReservationVO();
               reservation.setMember(member);
               reservation.setSeat_no(rs.getString("SEAT_NO"));
               reservation.setRse_start_time(rs.getString("RSE_START_TIME"));
               reservation.setRse_end_time(rs.getString("RSE_END_TIME"));
               reservation.setRse_no(rs.getString("RSE_NO"));

               reservations.add(reservation);           
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil02.close(rs);
            JDBCUtil02.close(pstmt);   
            JDBCUtil02.close(conn);
        } 
       return reservations;
    }
 //좌석 등록
   //reservation.setMem_no(userNo);
   public int insertReservation(ReservationVO reservation, String seatNo) {
       Connection conn = null;
       PreparedStatement pstmt = null;       

       try {
           conn = JDBCUtil02.getConnection();
           String sql = "INSERT INTO RESERVATION (RSE_NO, RSE_START_TIME, RSE_END_TIME, MEM_NO, SEAT_NO) " +
                        "VALUES ('R'||TO_CHAR(SYSDATE, 'YYMMDD')||?||reservation_seq.nextval, SYSDATE, SYSDATE + INTERVAL '3' HOUR, ?, ?)";
           pstmt = conn.prepareStatement(sql);           
           pstmt.setString(1, seatNo);
           pstmt.setString(2, reservation.getMem_no());
           pstmt.setString(3, seatNo);
           int res=pstmt.executeUpdate();
           return res;
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           JDBCUtil02.close(pstmt);
           JDBCUtil02.close(conn);
       }
       return 0;
   }
   //좌석등록 시 중복체크 : 한 날짜에 한 번씩만 예약 가능
   public boolean checkReservationDuplicate(String userNo) {
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       try {
           conn = JDBCUtil02.getConnection();
           String sql = "SELECT COUNT(*) FROM RESERVATION WHERE MEM_NO = ? AND TRUNC(RSE_START_TIME) = TRUNC(SYSDATE)";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, userNo);
           rs = pstmt.executeQuery();

           if (rs.next()) {
               int count = rs.getInt(1);
               return count > 0;
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           JDBCUtil02.close(rs);
           JDBCUtil02.close(pstmt);
           JDBCUtil02.close(conn);
       }

       return false;
   }
         

   
     
    
}



