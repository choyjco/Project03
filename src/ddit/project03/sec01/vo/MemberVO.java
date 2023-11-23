package ddit.project03.sec01.vo;

public class MemberVO {
   
   private String mem_no;
   private String mem_passwd;
   private String mem_name;
   private String mem_telno;
   private String mem_cla;
   
   public MemberVO() {}
   
   public MemberVO(String mem_no, String mem_passwd, String mem_name, String mem_telno, String mem_cla) {
      this.mem_no=mem_no;
      this.mem_passwd=mem_passwd;
      this.mem_name=mem_name;
      this.mem_telno=mem_telno;
      this.mem_cla=mem_cla;
   }
   public String getMem_no() {
      return mem_no;
   }
   public void setMem_no(String mem_no) {
      this.mem_no = mem_no;
   }
   public String getMem_passwd() {
      return mem_passwd;
   }
   public void setMem_passwd(String mem_passwd) {
      this.mem_passwd = mem_passwd;
   }
   public String getMem_name() {
      return mem_name;
   }
   public void setMem_name(String mem_name) {
      this.mem_name = mem_name;
   }
   public String getMem_telno() {
      return mem_telno;
   }
   public void setMem_telno(String mem_telno) {
      this.mem_telno = mem_telno;
   }
   public String getMem_cla() {
      return mem_cla;
   }
   public void setMem_cla(String mem_cla) {
      this.mem_cla = mem_cla;
   }


	
}
   