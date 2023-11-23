package ddit.project03.sec01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ddit.project03.sec01.util.JDBCUtil02;
import ddit.project03.sec01.vo.MemberVO;

public class MemberDao {
   private static MemberDao instance= null;
   private MemberDao() {}
   
public static MemberDao getInstance() {
   if(instance==null) instance= new MemberDao();
   return instance;
}

    // 회원 로그인 -- select다
   public MemberVO login(MemberVO memVO) {
      Connection conn= null;
      PreparedStatement pstmt= null;
      ResultSet rs= null; // db에서 가져온 값을 저장하는 공간?
     try {
      conn=JDBCUtil02.getConnection();
      StringBuilder sql= new StringBuilder();
      sql.append("select mem_no, mem_passwd, mem_name\n");
      sql.append(" from member \n");
      sql.append("where mem_no = ? and mem_passwd = ?");
      pstmt= conn.prepareStatement(sql.toString());
      pstmt.setString(1, memVO.getMem_no());
      pstmt.setString(2, memVO.getMem_passwd());
      rs=pstmt.executeQuery();
      
     if(rs.next()) {
         memVO.setMem_no(rs.getString("MEM_NO"));
         memVO.setMem_passwd(rs.getString("MEM_PASSWD"));
         memVO.setMem_name(rs.getString("MEM_NAME"));
     }else {
        memVO.setMem_no(null);
        memVO.setMem_passwd(null);
        memVO.setMem_name(null);
     }
   }catch(SQLException e) {
      e.printStackTrace();
   }finally {
      JDBCUtil02.close(rs);
      JDBCUtil02.close(pstmt);
      JDBCUtil02.close(conn);
   }
   return memVO;
   }
   
   
   // 패스워드 찾기
   public MemberVO passwdFind(MemberVO memVO) {
      Connection conn= null;
         PreparedStatement pstmt= null;
         ResultSet rs= null;
        try {
         conn=JDBCUtil02.getConnection();
         StringBuilder sql= new StringBuilder();
         sql.append("select mem_no, mem_passwd, mem_telno \n");
         sql.append(" from member \n");
         sql.append("where mem_no=? and mem_telno =?");
         pstmt=conn.prepareStatement(sql.toString());
         pstmt.setString(1, memVO.getMem_no());
         pstmt.setString(2, memVO.getMem_telno());
         rs=pstmt.executeQuery();
         
        if (rs.next()) {
            memVO.setMem_no(rs.getString("MEM_NO"));
            memVO.setMem_passwd(rs.getString("MEM_PASSWD"));
            memVO.setMem_telno(rs.getString("MEM_TELNO"));
         }else {
           memVO.setMem_no(null);
            memVO.setMem_passwd(null);
            memVO.setMem_telno(null);
         }
       }catch(SQLException e) {
          e.printStackTrace();
       }finally {
          JDBCUtil02.close(rs);
          JDBCUtil02.close(pstmt);
          JDBCUtil02.close(conn);
       }
       return memVO;
       }
   
   
   // 회원 등록
   public int insert(MemberVO memVO) {
      Connection conn = null;
      PreparedStatement pstmt= null;
   try {
     conn=JDBCUtil02.getConnection();
      StringBuilder sql= new StringBuilder();
      sql.append("insert into member(mem_no,mem_passwd,mem_name,mem_telno,mem_cla) \n");
      sql.append("values(?,?,?,?,?)");
      
      pstmt=conn.prepareStatement(sql.toString());
      pstmt.setNString(1, memVO.getMem_cla()+memVO.getMem_no());
      pstmt.setNString(2, memVO.getMem_passwd());
      pstmt.setNString(3, memVO.getMem_name());
      pstmt.setNString(4, memVO.getMem_telno());
      pstmt.setNString(5, memVO.getMem_cla());
      int res= pstmt.executeUpdate();
      return res;
   }catch(SQLException e) {
      e.printStackTrace();
   }finally {  
   JDBCUtil02.close(pstmt);
   JDBCUtil02.close(conn);
   }
   return 0;
      }
   
   // 비밀번호 수정
   public int passwdUpdate(MemberVO memVO) {
      Connection conn = null;
      PreparedStatement pstmt= null;
   try {
     conn=JDBCUtil02.getConnection();
      StringBuilder sql= new StringBuilder();
      sql.append("update member \n");
      sql.append("set mem_passwd=? \n");
      sql.append("where mem_no=?");
      
      pstmt=conn.prepareStatement(sql.toString());
      pstmt.setString(1, memVO.getMem_passwd());
      pstmt.setString(2, memVO.getMem_no());   
      int res= pstmt.executeUpdate();
      return res;
   }catch(SQLException e) {
      e.printStackTrace();
   }finally { 
   JDBCUtil02.close(pstmt);
   JDBCUtil02.close(conn);
   } 
   return 0;
      }
   
   // 전화번호 수정
   public int telUpdate(MemberVO memVO) {
      Connection conn = null;
      PreparedStatement pstmt= null;
   try {
     conn=JDBCUtil02.getConnection();
      StringBuilder sql= new StringBuilder();
      sql.append("update member \n");
      sql.append("set mem_telno=? \n");
      sql.append("WHERE mem_no = ?");
      
      pstmt=conn.prepareStatement(sql.toString());
      pstmt.setString(1, memVO.getMem_telno());
      pstmt.setString(2, memVO.getMem_no());   
      int res= pstmt.executeUpdate();
      return res;
   }catch(SQLException e) {
      e.printStackTrace();
   }finally  { 
      JDBCUtil02.close(pstmt);
      JDBCUtil02.close(conn);
      } 
   return 0;
      }
      
   // 회원 삭제
   public int delete(MemberVO memVO) {
      Connection conn = null;
      PreparedStatement pstmt= null;
   try {
     conn=JDBCUtil02.getConnection();
      StringBuilder sql= new StringBuilder();
      sql.append("delete from member \n");
      sql.append("where mem_no=?");
      
      pstmt=conn.prepareStatement(sql.toString());
      pstmt.setNString(1, memVO.getMem_no());
      int res= pstmt.executeUpdate();
      return res;
   }catch(SQLException e) {
      e.printStackTrace();
   }finally {
      JDBCUtil02.close(pstmt);
      JDBCUtil02.close(conn);
   }
   return 0;
      }
   
   // 회원 중복 검사
   
   public boolean checkMemberDuplicate(String mem_no) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = JDBCUtil02.getConnection();
	        String sql = "SELECT COUNT(*) FROM member WHERE mem_no = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, mem_no);
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
   
   
   
   