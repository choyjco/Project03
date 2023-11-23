package ddit.project03.sec01.vo;

public class ReservationVO {// 나의 예약정보조회(SELECT, 회원)ㅇㅋ,예약(INSERT, 회원),예약취소(DELETE, 회원), 당일 전체 좌석 예약
							// 조회(SELECT, 관리자)ㅇㅋ

	private String rse_no;
	private String rse_start_time;
	private String rse_end_time;
	private String mem_no;
	private String seat_no;
	private MemberVO member;

	public ReservationVO() {
	}

	public ReservationVO(String rse_no, String rse_start_time, String rse_end_time, String mem_no, String seat_no,
			MemberVO member) {
		this.rse_no = rse_no;
		this.rse_start_time = rse_start_time;
		this.rse_end_time = rse_end_time;
		this.mem_no = mem_no;
		this.seat_no = seat_no;
		this.member = member;

	}

	public String getRse_no() {
		return rse_no;
	}

	public void setRse_no(String rse_no) {
		this.rse_no = rse_no;
	}

	public String getRse_start_time() {
		return rse_start_time;
	}

	public void setRse_start_time(String rse_start_time) {
		this.rse_start_time = rse_start_time;
	}

	public String getRse_end_time() {
		return rse_end_time;
	}

	public void setRse_end_time(String rse_end_time) {
		this.rse_end_time = rse_end_time;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}

}