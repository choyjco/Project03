package ddit.project03.sec01.vo;

public class BorrowVO {
	private String borr_no;
	private String borr_date;
	private String ret_due_date; 
	private String mem_no;
	private String book_no;
	
	public BorrowVO() {}
	public BorrowVO(String borr_no, String borr_date, String ret_due_date, String mem_no, String book_no) {
		this.borr_no=borr_no;
		this.borr_date=borr_date;
		this.ret_due_date=ret_due_date;
		this.mem_no=mem_no;
		this.book_no=book_no;
	}

	public String getBorr_no() {
		return borr_no;
	}

	public void setBorr_no(String borr_no) {
		this.borr_no = borr_no;
	}

	public String getBorr_date() {
		return borr_date;
	}

	public void setBorr_date(String borr_date) {
		this.borr_date = borr_date;
	}

	public String getRet_due_date() {
		return ret_due_date;
	}

	public void setRet_due_date(String ret_due_date) {
		this.ret_due_date = ret_due_date;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getBook_no() {
		return book_no;
	}

	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}
	
}
