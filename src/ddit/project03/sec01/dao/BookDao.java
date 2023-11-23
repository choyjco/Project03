package ddit.project03.sec01.dao;

import java.util.List;
import java.util.Map;

import ddit.project03.sec01.util.JDBCUtil;

public class BookDao {
	private static BookDao instance = null;

	private BookDao() {
	}

	public static BookDao getInstance() {
		if (instance == null)
			instance = new BookDao();
		return instance;
	}

	static JDBCUtil jdbc = JDBCUtil.getInstance();

	public static int AddNewBook(List<Object> param) {
		String sql = "INSERT INTO BOOK "
				+ " (BOOK_NO, BOOK_NAME, BOOK_WRITER, BOOK_COM, BOOK_YEAR, BORR_STATUS, CLA_CODE) " + "VALUES"
				+ "(?, ?, ?, ?, ?, ?, ?) ";
		return jdbc.insert(sql, param);
	}

	public static Map<String, Object> isOverapNO(String bookNo) { // 중복확인
		String sql = "SELECT * FROM BOOK WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.selectOne(sql);
	}

	public static int delete(String bookNo) {
		String sql = " DELETE FROM BOOK WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.update(sql);
	}

	public int updateBookNo(String inputbookNo, String bookNo) {
		String sql = "UPDATE BOOK SET BOOK_NO='" + inputbookNo + "' WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.update(sql);
	}

	public int updateBookName(String inputbookName, String bookNo) {
		String sql = " UPDATE BOOK SET BOOK_NAME='" + inputbookName + "' WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.update(sql);
	}

	public int updateWriter(String inputwriter, String bookNo) {
		String sql = " UPDATE BOOK SET BOOK_WRITER='" + inputwriter + "'  WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.update(sql);
	}

	public int updateCom(String inputcom, String bookNo) {
		String sql = " UPDATE BOOK SET BOOK_COM='" + inputcom + "'  WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.update(sql);
	}

	public int updateYear(int inputYear, String bookNo) {
		String sql = " UPDATE BOOK SET BOOK_YEAR='" + inputYear + "'  WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.update(sql);
	}

	public int updateBorrStatus(String inputborrstatus, String bookNo) {
		String sql = " UPDATE BOOK SET BORR_STATUS='" + inputborrstatus + "'  WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.update(sql);
	}

	public int updateClaCode(int inputclacode, String bookNo) {
		String sql = " UPDATE BOOK SET CLA_CODE='" + inputclacode + "'  WHERE BOOK_NO='" + bookNo + "'";
		return jdbc.update(sql);
	}

	public static List<Map<String, Object>> findBookName(String bookName) {
		String sql = "SELECT BOOK_NO, BOOK_NAME, BOOK_WRITER,BOOK_COM, BOOK_YEAR, BORR_STATUS FROM BOOK WHERE BOOK_NAME LIKE '%"
				+ bookName + "%'";
		return jdbc.selectList(sql);

	}

	public static List<Map<String, Object>> findBookWriter(String bookWriter) {
		String sql = "SELECT BOOK_NO, BOOK_NAME, BOOK_WRITER,BOOK_COM, BOOK_YEAR, BORR_STATUS FROM BOOK WHERE BOOK_WRITER LIKE '%"
				+ bookWriter + "%'";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> bookList(String bookNo) {
		String sql = "SELECT * FROM BOOK WHERE BOOK_NO LIKE '" + bookNo + "'";
		return jdbc.selectList(sql);
	}
}