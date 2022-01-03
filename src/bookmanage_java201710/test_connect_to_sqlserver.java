package bookmanage_java201710;

import java.sql.*;
public class test_connect_to_sqlserver {

	/*	本程序之前，先添加好SQL SERVER 的JDBC引用	*/
	public static void main(String[] args) {
		/*	连接到STUDENTS数据库  */
		
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String connectDB ="jdbc:sqlserver://localhost;DatabaseName=books"; 
		//String connectDB ="jdbc:sqlserver://win7_32;DatabaseName=teaching"; 正确
		//String connectDB ="jdbc:sqlserver://127.0.0.1;DatabaseName=teaching"; 正确
					
		try {
			Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类
		    }
		catch (ClassNotFoundException e) {
			// e.printStackTrace();
			System.out.println("加载数据库引擎失败");
			System.exit(0);
		   }
		System.out.println("数据库驱动成功");

		try {
			String user = "sa";
			String password = "123456";
			Connection con = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
			System.out.println("连接数据库成功");	
			con.close();// 关闭数据库连接
			} 
	   catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接错误");
			System.exit(0);
		}
	} 		
}


