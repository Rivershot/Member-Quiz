package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	// OJDBC 드라이버
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	//DB 연결 정보
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";
	
	// private 생성자
	private JDBCTemplate() {}
	
	// DB연결 객체 (싱글톤 적용)
	private static Connection conn;
	
	//Connection 객체 반환 - 싱글톤 패턴이 적용된다
	public static Connection getConnection() {
		
		// 첫 DB 연결일 경우
		if( conn == null ) {
			
			try {
				
				Class.forName(DRIVER);
				
				conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				
				// Auto Commit 설정 끄기
				conn.setAutoCommit(false);
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		//DB 연결 객체 반환
		return conn;
	}
	

	//Connection 객체 닫기
	public static void close(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//Statement 객체 닫기
	public static void close(Statement st) {
		
		try {
			if(st != null && !st.isClosed()) st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	//PrepareStatement 객체 닫기
	public static void close(PreparedStatement ps) {
		try {
			if(ps != null && !ps.isClosed()) ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ResultSet 객체 닫기
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void commit(Connection conn) {
		try {
			if( conn != null && !conn.isClosed()) conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if( conn != null && !conn.isClosed()) conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}

