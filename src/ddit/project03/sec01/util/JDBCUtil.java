package ddit.project03.sec01.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {
	private static JDBCUtil instance=null;
	private JDBCUtil() {}
	
	public static JDBCUtil getInstance() {
		if(instance==null)instance=new JDBCUtil();
		return instance;
	}
	
	private final String URL="jdbc:oracle:thin:@localhost:1521:xe";//노트북 연결
	private final String USER="team3_202304S";
	private final String PASSWD="java";
	
	private Connection conn=null;
	private PreparedStatement psmt=null;
	private ResultSet rs=null;
	
	public int insert(String sql, List<Object> param) {//입력
		int result=0;
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			psmt=conn.prepareStatement(sql);
			for(int i=0; i<param.size(); i++) {
				psmt.setObject(i+1, param.get(i));
			}
			result=psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(psmt!=null) try {psmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}			
		}
		return result;
	}
	
	public int insert(String sql) {
		int result=0;
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			psmt=conn.prepareStatement(sql);
			result=psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(psmt!=null) try {psmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}			
		}
		return result;
	}
	
	public int update(String sql, List<Object> param) {//업데이트
		int result=0;
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			psmt=conn.prepareStatement(sql);
			for(int i=0; i<param.size(); i++) {
				psmt.setObject(i+1, param.get(i));
			}
			result=psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(psmt!=null) try {psmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}			
		}
		return result;
	}
	
	public int update(String sql) {
		int result=0;
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			psmt=conn.prepareStatement(sql);
			result=psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(psmt!=null) try {psmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}			
		}
		return result;
	}
	
	public int delete(String sql, List<Object> param) {//삭제
		int result=0;
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			psmt=conn.prepareStatement(sql);
			for(int i=0; i<param.size(); i++) {
				psmt.setObject(i+1, param.get(i));
			}
			result=psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(psmt!=null) try {psmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}			
		}
		return result;
	}
	
	public int delete(String sql) {
		int result=0;
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			psmt=conn.prepareStatement(sql);
			result=psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(psmt!=null) try {psmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}			
		}
		return result;
	}
	
	public List<Map<String, Object>> selectList(String sql){//select 여러개
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int columnCount=rsmd.getColumnCount();
			while(rs.next()) {
				Map<String,Object>row=new HashMap<String, Object>();
				for(int i=1; i<=columnCount; i++) {
					String key=rsmd.getColumnName(i);
					Object value=rs.getObject(i);
					row.put(key,value);
				}
				list.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(psmt!=null) try {psmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}			
		}
		return list;
	}
	
	public List<Map<String, Object>> selectList(String sql, List<Object> param) {
	    List<Map<String, Object>> list = null;
	    try {
	        conn = DriverManager.getConnection(URL, USER, PASSWD);
	        psmt = conn.prepareStatement(sql);
	        if (param != null) {
	            for (int i = 0; i < param.size(); i++) {
	                psmt.setObject(i + 1, param.get(i));
	            }
	        }
	        rs = psmt.executeQuery();
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnCount = rsmd.getColumnCount();
	        list = new ArrayList<>();
	        while (rs.next()) {
	            Map<String, Object> row = new HashMap<>();
	            for (int i = 1; i <= columnCount; i++) {
	                String key = rsmd.getColumnName(i);
	                Object value = rs.getObject(i);
	                row.put(key, value);
	            }
	            list.add(row);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (Exception e) {
	            }
	        }
	        if (psmt != null) {
	            try {
	                psmt.close();
	            } catch (Exception e) {
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (Exception e) {
	            }
	        }
	    }
	    return list;
	}
	
	
	public Map<String, Object> selectOne(String sql) {//셀렉트 하나
		Map<String, Object> map = null;
		
		try {
			conn=DriverManager.getConnection(URL, USER, PASSWD);
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if(rs.next()) {
				map=new HashMap<String, Object>();
				for(int i=1; i<=columnCount; i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(psmt!=null) try {psmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}
		}
		return map;
	}
	
	public Map<String, Object> selectOne(String sql, List<Object> param) {
		Map<String, Object> map = null;
		
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			psmt=conn.prepareStatement(sql);
			for(int i=0; i<param.size(); i++) {
				psmt.setObject(i+1, param.get(i));
			}
			rs=psmt.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int columnCount=rsmd.getColumnCount();
			
			if(rs.next()) {
				map=new HashMap<String, Object>();
				for(int i=1; i<=columnCount; i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(psmt!=null) try {psmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}		
		}return map;
		
	}	
	

	public Map<String, Object> inquiry(String sql) {//조회용
		Map<String, Object> map = null;
		
		try {
			conn=DriverManager.getConnection(URL, USER, PASSWD);
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if(rs.next()) {
				map=new HashMap<String, Object>();
				for(int i=1; i<=columnCount; i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
					System.out.println(rsmd.getColumnName(i));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(psmt!=null) try {psmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}
		}
		return map;
	}
	
	public Map<String, Object> inquiry(String sql, List<Object> param) {
		Map<String, Object> map = null;
		
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWD);
			psmt=conn.prepareStatement(sql);
			for(int i=0; i<param.size(); i++) {
				psmt.setObject(i+1, param.get(i));
			}
			rs=psmt.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int columnCount=rsmd.getColumnCount();
			
			if(rs.next()) {
				map=new HashMap<String, Object>();
				for(int i=1; i<=columnCount; i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
					System.out.println(rsmd.getColumnName(i));
			}
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		if(rs!=null) try {rs.close();}catch(Exception e) {}
		if(psmt!=null) try {psmt.close();}catch(Exception e) {}
		if(conn!=null) try {conn.close();}catch(Exception e) {}		
	}return map;

	}	
	
	
}


	






