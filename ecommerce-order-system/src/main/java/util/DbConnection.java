package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	public static void main(String[] args) {
		System.out.println(DbConnection.getDb());			
	}
	
	public static Connection getDb() {
		Connection conn=null;
	
	String url="jdbc:mysql://localhost:3306/ecommerce_order_system";
	String user="root";
	String password="1234";

	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url,user,password);
		System.out.println("資料庫連線成功");
		
	}catch (ClassNotFoundException e) {
		System.out.println("找不到 mysql jdbc Driver");
		e.printStackTrace();
		
	}catch (SQLException e) {
		System.out.println("資料庫連線失敗");
		e.printStackTrace();
	}
		return conn;
	}


}
