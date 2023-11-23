package ddit.project03.sec01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ddit.project03.sec01.util.JDBCUtil02;
import ddit.project03.sec01.vo.ReservationVO;

public class SeatDao {//현재좌석현황(SELECT)
		private static SeatDao instance = null;		
		private SeatDao() {}		
		public static SeatDao getInstance() {
			if(instance==null) instance = new SeatDao();
			return instance;
		}
		//현재 좌석 현황 조회		
		public void currSeatStatus(int[][] seats) {
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;

		    try {
		    	conn = JDBCUtil02.getConnection();
		        String updateSql = "UPDATE SEAT SET SEAT_STATUS = (CASE WHEN EXISTS (SELECT 1 FROM RESERVATION " +
		                "WHERE SEAT.RSE_NO = RESERVATION.RSE_NO AND SEAT.SEAT_NO = RESERVATION.SEAT_NO " +
		                "AND RSE_START_TIME <= SYSDATE AND RSE_END_TIME >= SYSDATE) THEN 'X' ELSE 'O' END)";
		        pstmt = conn.prepareStatement(updateSql);
		        pstmt.executeUpdate();

		        String sql = "SELECT SEAT_ROW, SEAT_COLU, SEAT_STATUS FROM SEAT";
		        pstmt = conn.prepareStatement(sql);
		        rs = pstmt.executeQuery();

		        while (rs.next()) {
		            String row = rs.getString("SEAT_ROW"); // SEAT_ROW 값을 문자열로 가져옴
		            int column = rs.getInt("SEAT_COLU") - 1;
		            String status = rs.getString("SEAT_STATUS");

		            int rowIndex = row.charAt(0) - 'A'; // 문자열을 인덱스로 변환

		            if (status.equals("O")) {
		                seats[rowIndex][column] = 1;
		            } else {
		                seats[rowIndex][column] = 0;
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        JDBCUtil02.close(rs);
		        JDBCUtil02.close(pstmt);
		        JDBCUtil02.close(conn);
		    }
		}
		public int checkout(ReservationVO reservation) {
			Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	        	conn = JDBCUtil02.getConnection();
	            String updateSql = "UPDATE SEAT " +
	                               "SET SEAT_STATUS = 'O', RSE_NO = NULL " +
	                               "WHERE RSE_NO IN ( " +
	                               "    SELECT RSE_NO " +
	                               "    FROM RESERVATION " +
	                               "    WHERE MEM_NO = ? " +
	                               ")";
	            pstmt = conn.prepareStatement(updateSql);
	            int res;
	            pstmt.setString(1, reservation.getMem_no()); // MEM_NO를 설정합니다.
	            res = pstmt.executeUpdate();
		        return res;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtil02.close(pstmt);
	            JDBCUtil02.close(conn);
	        }
	        return 0;
	    }		
		}

