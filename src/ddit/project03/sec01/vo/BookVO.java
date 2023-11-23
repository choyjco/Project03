package ddit.project03.sec01.vo;

public class BookVO {
	private String book_no;
	private String book_name;
	private String book_writer;
	private String book_com;
	private int book_year;
	private String book_status;
	private int cla_code;

	public BookVO() {}
	public BookVO(String book_no, String book_name,String book_writer, String book_com, int book_year,String book_status,int cla_code) {
		this.book_no= book_no;
		this.book_name=book_name;
		this.book_writer=book_writer;
		this.book_com=book_com;
		this.book_year=book_year;
		this.book_status=book_status;
		this.cla_code=cla_code;
	}
	public String getBook_no() {
		return book_no;
	}
	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_writer() {
		return book_writer;
	}
	public void setBook_writer(String book_writer) {
		this.book_writer = book_writer;
	}
	public String getBook_com() {
		return book_com;
	}
	public void setBook_com(String book_com) {
		this.book_com = book_com;
	}
	public int getBook_year() {
		return book_year;
	}
	public void setBook_year(int book_year) {
		this.book_year = book_year;
	}
	public String getBook_status() {
		return book_status;
	}
	public void setBook_status(String book_status) {
		this.book_status = book_status;
	}
	public int getCla_code() {
		return cla_code;
	}
	public void setCla_code(int cla_code) {
		this.cla_code = cla_code;
	}

}
