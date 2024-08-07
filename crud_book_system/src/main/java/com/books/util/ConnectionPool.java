package com.books.util;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class ConnectionPool {
	// DBPool 클래스를 한 번만 사용하려고 중첩 클래스 적용
	// 커넥션 풀 : DB 와의 연결을 관리하는 도구
	// 여러 개의 연결을 미리 만들어 놓고 필요할 때마다 제공해준다.
	public static class DBPool {
		private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		private static final String DB_URL = "jdbc:mysql://localhost:3306/books_db";
		private static final String DB_USER = "root";
		private static final String DB_PASSWORD = "1234";
		// 아파치 라이브러리에서 제공하는 DBCP(DataBase Connection Pooling) 클래스 사용
		static final BasicDataSource dbcp = new BasicDataSource();
		static {
			try {
				dbcp.setDriverClassName(JDBC_DRIVER);
				dbcp.setUrl(DB_URL);
				dbcp.setUsername(DB_USER);
				dbcp.setPassword(DB_PASSWORD);
				
				// 연결 수 관리
				dbcp.setInitialSize(10); // 초기 연결 수 10개 설정
				dbcp.setMaxTotal(50); // 최대 연결 수 50개 설정
				dbcp.setMaxIdle(20); // 최대 유휴 연결 수 20개 설정
				dbcp.setMinIdle(5); // 최소 유휴 연결 수 5개 설정				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static Connection getDBPool() throws SQLException {
			return dbcp.getConnection();
		}
	}
}
