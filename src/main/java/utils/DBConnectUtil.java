package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectUtil {
	private static String url ="jdbc:sqlserver://DESKTOP-Q285043\\SERVER;databaseName=CMC;user=sa;password=123456;encrypt=false";
	private static String url1 ="jdbc:sqlserver://DESKTOP-Q285043\\CLIENT1;databaseName=CMC;user=sa;password=123456;encrypt=false";
	private static String url2 ="jdbc:sqlserver://DESKTOP-Q285043\\CLIENT2;databaseName=CMC;user=sa;password=123456;encrypt=false";
	private static String url3 ="jdbc:sqlserver://DESKTOP-Q285043\\CLIENT2;databaseName=CMC;user=sa;password=123456;encrypt=false";
	
//	private static String user="sa";
//	private static String password="123456";
	private static Connection conn = null;
	private static Connection conn1 = null;
	private static Connection conn2 = null;
	private static Connection conn3  = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	public static Connection getConnectionClient1() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn1 = DriverManager.getConnection(url1);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return conn1;
	}
	
	public static Connection getConnectionClient2() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn2 = DriverManager.getConnection(url2);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return conn2;
	}
	
	public static Connection getConnectionClient3() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn3 = DriverManager.getConnection(url2);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return conn3;
	}
	
	public static void main(String[] args) {
		System.out.println("Connect sever: "+DBConnectUtil.getConnection());
		System.out.println("Connect client 1: " + DBConnectUtil.getConnectionClient1());
		System.out.println("Connect client 2: " + DBConnectUtil.getConnectionClient2());
		System.out.println("Connect client 3: " + DBConnectUtil.getConnectionClient3());
	}
}
