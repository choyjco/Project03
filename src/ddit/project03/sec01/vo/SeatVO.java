package ddit.project03.sec01.vo;

public class SeatVO {

	private String seat_no;
	private String seat_row;
	private int colu;
	private String seat_status;
	private String res_no;

	public SeatVO() {
	}

	public SeatVO(String seat_no, String seat_row, int colu, String seat_status, String res_no) {
		this.seat_no = seat_no;
		this.seat_row = seat_row;
		this.colu = colu;
		this.seat_status = seat_status;
		this.res_no = res_no;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}

	public String getSeat_row() {
		return seat_row;
	}

	public void setSeat_row(String seat_row) {
		this.seat_row = seat_row;
	}

	public int getColu() {
		return colu;
	}

	public void setColu(int colu) {
		this.colu = colu;
	}

	public String getSeat_status() {
		return seat_status;
	}

	public void setSeat_status(String seat_status) {
		this.seat_status = seat_status;
	}

	public String getRes_no() {
		return res_no;
	}

	public void setRes_no(String res_no) {
		this.res_no = res_no;
	}

}
