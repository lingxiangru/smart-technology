package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBC {
	private static DataSource dataSource;
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	private static PreparedStatement pstmt;
	
	static {
		Properties prop = new Properties();
		try {
			prop.load(JDBC.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			dataSource = DruidDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return getConnection(true);
	}
	
	public static Connection getConnection(boolean flag) throws SQLException {
		Connection conn = threadLocal.get();
		if(conn==null||conn.isClosed()) {
			conn = dataSource.getConnection();
			conn.setAutoCommit(flag);
			threadLocal.set(conn);
		}else {
			conn.setAutoCommit(flag);
		}
		return conn;
	}
	
	public static ResultSet executeQuery(String sql,Object...objects) throws SQLException {
		Connection conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		if(objects.length>0) {
			for(int i=0;i<objects.length;i++) {
				pstmt.setObject(i+1, objects[i]);
			}
		}
		return pstmt.executeQuery();
	}
	
	public static synchronized int executeUpdate(String sql,Object...objects) throws SQLException {
		Connection conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		if(objects.length>0) {
			for(int i=0;i<objects.length;i++) {
				pstmt.setObject(i+1, objects[i]);
			}
		}
		return pstmt.executeUpdate();
	}
	
	public static void commit() {
		Connection conn = threadLocal.get();
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception("提交失败").printStackTrace();
			rollback();
		}finally {
			threadLocal.remove();
		}
	}
	
	public static void rollback() {
		Connection conn = threadLocal.get();
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception("回滚失败").printStackTrace();
		}finally {
			threadLocal.remove();
		}
	}
	
	public static void close() {
		Connection conn = threadLocal.get();
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				new Exception("连接关闭失败").printStackTrace();
			}finally {
				threadLocal.remove();
			}
		}
	}
	
}
