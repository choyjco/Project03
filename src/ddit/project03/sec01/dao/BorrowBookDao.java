package ddit.project03.sec01.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ddit.project03.sec01.util.JDBCUtil;
import ddit.project03.sec01.util.JDBCUtil02;

public class BorrowBookDao {
	private static BorrowBookDao instance = null;

	private BorrowBookDao() {
	}

	public static BorrowBookDao getInstance() {
		if (instance == null)
			instance = new BorrowBookDao();
		return instance;
	}

	static JDBCUtil jdbc = JDBCUtil.getInstance();

	public int borrowBook(List<Object> param) {
		String sql = "INSERT INTO BORROW (BORR_NO, BORR_DATE, RET_DUE_DATE, RET_DATE, MEM_NO, BOOK_NO) "
				+ " VALUES ('B'||TO_CHAR(SYSDATE, 'YYMMDD')||trim(to_char(borrowBook_seq.nextval,'000')), SYSDATE, SYSDATE + INTERVAL '14' DAY, NULL, ?, ?)";
		return jdbc.insert(sql, param);
	}

	// 가장 마지막에 빌린(BORROW) 정보(1건)
	public Map<String, Object> lastStatusList() {
		String sql = "WITH T AS( " + "    SELECT ROW_NUMBER() OVER(ORDER BY B.BORR_DATE DESC) RNUM "
				+ "         , A.BOOK_NO, A.BOOK_NAME, A.BOOK_WRITER, A.BOOK_COM, A.BOOK_YEAR, A.BORR_STATUS, A.CLA_CODE "
				+ "         , B.BORR_NO, B.BORR_DATE, B.RET_DUE_DATE, B.RET_DATE, B.MEM_NO "
				+ "    FROM   BOOK A, BORROW B " + "    WHERE  A.BOOK_NO = B.BOOK_NO " + ") " + "SELECT * FROM T "
				+ "WHERE  T.RNUM = 1";
		return jdbc.selectOne(sql);
	}

	// 빌리면 N이 됨
	public int prepareStatementToN(String bookNo) {
		String sql = "UPDATE BOOK SET BORR_STATUS='" + 'N' + "' WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.update(sql);
	}

	public int prepareStatementToY(String bookNo) {
		String sql = "UPDATE BOOK SET BORR_STATUS='" + 'Y' + "' WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.update(sql);
	}

	public int returnBook(List<Object> param) {
		String sql = "UPDATE BORROW SET RET_DATE = SYSDATE WHERE BOOK_NO = ?";
		return jdbc.update(sql, param);

	}

	public Map<String, Object> notmuchNo(String bookNo) { // 중복확인
		String sql = "SELECT * FROM BORROW WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.selectOne(sql);
	}

	public List<Map<String, Object>> possibleNo(String bookNo) {// 중복확인(대출 되어있는지 아닌지) 대출가능 : Y
		String sql = "SELECT * FROM BOOK WHERE BOOK_NO='" + bookNo + "' AND BORR_STATUS='Y'";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> possibleNoY(String bookNo) {// 중복확인(대출 되어있는지 아닌지)
		String sql = "SELECT * FROM BOOK WHERE BOOK_NO='" + bookNo + "' AND BORR_STATUS='N'";
		return jdbc.selectList(sql);
	}

	public int updateRetStatus(String bookNo) {
		String sql = " UPDATE BORROW SET RET_STATUS='Y''" + "' WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.update(sql);
	}

	public List<Map<String, Object>> borrowListS(String memNo) {
		String sql = "SELECT M.MEM_NAME, BO.BORR_NO, B.BOOK_NAME, B.BOOK_NO, BO.BORR_DATE, BO.RET_DUE_DATE, BO.RET_DATE, B.BORR_STATUS "
				+ "FROM MEMBER M, BOOK B, BORROW BO "
				+ "WHERE M.MEM_NO = BO.MEM_NO AND B.BOOK_NO = BO.BOOK_NO AND M.MEM_NO ='" + memNo + "'";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> borrowListBorrNo(String borrNo) {
		String sql = "SELECT M.MEM_NAME, BO.BORR_NO, B.BOOK_NAME, B.BOOK_NO, BO.BORR_DATE, BO.RET_DUE_DATE, BO.RET_DATE, B.BORR_STATUS "
				+ "FROM MEMBER M, BOOK B, BORROW BO "
				+ "WHERE M.MEM_NO = BO.MEM_NO AND B.BOOK_NO = BO.BOOK_NO AND BO.BORR_NO LIKE '%" + borrNo + "%'";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> borrowListBorrDate(String borrDate) {
		String sql = "SELECT M.MEM_NAME, BO.BORR_NO, B.BOOK_NAME, B.BOOK_NO, BO.BORR_DATE, BO.RET_DUE_DATE, BO.RET_DATE, B.BORR_STATUS "
				+ "FROM MEMBER M, BOOK B, BORROW BO "
				+ "WHERE M.MEM_NO = BO.MEM_NO AND B.BOOK_NO = BO.BOOK_NO AND BO.BORR_DATE LIKE '%" + borrDate + "%'";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> borrowListBorrStatus(String borrStatus) {
		String sql = "SELECT M.MEM_NAME, BO.BORR_NO, B.BOOK_NAME, B.BOOK_NO, BO.BORR_DATE, BO.RET_DUE_DATE, BO.RET_DATE, B.BORR_STATUS "
				+ "FROM MEMBER M, BOOK B, BORROW BO "
				+ "WHERE M.MEM_NO = BO.MEM_NO AND B.BOOK_NO = BO.BOOK_NO AND B.BORR_STATUS LIKE '%" + borrStatus + "%'";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> borrowListMemNo(String memNo) {
		String sql = "SELECT M.MEM_NAME, BO.BORR_NO, B.BOOK_NAME, B.BOOK_NO, BO.BORR_DATE, BO.RET_DUE_DATE, BO.RET_DATE, B.BORR_STATUS "
				+ "FROM MEMBER M, BOOK B, BORROW BO "
				+ "WHERE M.MEM_NO = BO.MEM_NO AND B.BOOK_NO = BO.BOOK_NO AND M.MEM_NO LIKE '%" + memNo + "%'";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> borrowListBookNo(String bookNo) {
		String sql = "SELECT M.MEM_NAME, BO.BORR_NO, B.BOOK_NAME, B.BOOK_NO, BO.BORR_DATE, BO.RET_DUE_DATE, BO.RET_DATE, B.BORR_STATUS "
				+ "FROM MEMBER M, BOOK B, BORROW BO "
				+ "WHERE M.MEM_NO = BO.MEM_NO AND B.BOOK_NO = BO.BOOK_NO AND B.BOOK_NO LIKE '%" + bookNo + "%'";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> overdueList() {

		List<Map<String, Object>> overdueLists = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil02.getConnection();
			String sql = "SELECT M.MEM_NAME, BO.BORR_NO, B.BOOK_NAME, B.BOOK_NO, BO.BORR_DATE, BO.RET_DUE_DATE "
					+ "FROM MEMBER M, BOOK B, BORROW BO " + "WHERE M.MEM_NO = BO.MEM_NO AND B.BOOK_NO = BO.BOOK_NO "
					+ "AND BO.RET_DUE_DATE < TO_CHAR(SYSDATE, 'YYMMDD') AND B.BORR_STATUS = 'N'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> overdueList = new HashMap<>();
				overdueList.put("MEM_NAME", rs.getString("MEM_NAME"));
				overdueList.put("BORR_NO", rs.getString("BORR_NO"));
				overdueList.put("BOOK_NAME", rs.getString("BOOK_NAME"));
				overdueList.put("BOOK_NO", rs.getString("BOOK_NO"));
				overdueList.put("BORR_DATE", rs.getString("BORR_DATE"));
				overdueList.put("RET_DUE_DATE", rs.getString("RET_DUE_DATE"));

				overdueLists.add(overdueList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil02.close(rs);
			JDBCUtil02.close(pstmt);
			JDBCUtil02.close(conn);
		}

		return overdueLists;
	}

}
