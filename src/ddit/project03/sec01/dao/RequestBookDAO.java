package ddit.project03.sec01.dao;

import java.util.List;
import java.util.Map;

import ddit.project03.sec01.util.JDBCUtil;
import ddit.project03.sec01.util.LoginUserNo;

public class RequestBookDAO {
	private static RequestBookDAO instance = null;

	private RequestBookDAO() {
	}

	public static RequestBookDAO getInstance() {
		if (instance == null)
			instance = new RequestBookDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();


	public int requestBookInsert(List<Object> param) {//희망도서 입력
	    String sql = "INSERT INTO REQUEST (REQ_NO, REQ_CON, REQ_DATE, REQ_STATUS, MEM_NO) " + 
                "VALUES ('H'||LPAD(req_sequence.NEXTVAL, 6, '0'), ?, TO_DATE(TO_CHAR(SYSDATE, 'YY-MM-DD HH24:MI'), 'YY-MM-DD HH24:MI') , '승인대기', ?)";
	    return jdbc.update(sql, param);
	}
	
	
	public Map<String, Object> inquiryNo(String req_no) {// 신청번호 조회
		String sql = "SELECT * FROM REQUEST WHERE REQ_NO='" + req_no + "'";
		return jdbc.selectOne(sql);
	}


	public int updateState(String req_status, String req_no) {//승인상태 업데이트
		String sql = "UPDATE REQUEST SET REQ_STATUS='"+req_status+"' WHERE REQ_NO='" + req_no + "'";
		return jdbc.update(sql);
	}
	

	public Map<String, Object> inquiryReqCon(String req_con) {//신청내용 조회
		String sql = "SELECT * FROM REQUEST WHERE REQ_CON='" + req_con + "'";
		return jdbc.selectOne(sql);
	}

	public List<Map<String, Object>> inquiryNo02(String reqCon) { //신청 리스트 추가
	    String sql = "SELECT REQ_NO, REQ_CON, REQ_STATUS FROM REQUEST ORDER BY REQ_STATUS DESC";
	    return jdbc.selectList(sql);
	}

	
	
	public List<Map<String,Object>> myRequestBookList(String reqCon){
		 String memNo = LoginUserNo.getInstance().getLoggedInUserNo();
	     System.out.println();
		String sql = "SELECT REQ_NO, REQ_CON, REQ_STATUS FROM REQUEST WHERE MEM_NO='"+memNo+"'";
		return jdbc.selectList(sql);
				
	}



}
		