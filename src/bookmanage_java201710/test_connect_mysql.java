package bookmanage_java201710;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 * 使用JDBC连接MySQL数据库
 * 
 * @author pan_junbiao
 */
public class test_connect_mysql
{
	public static void main(String[] args)
	{
		try
		{
			// 加载数据库驱动类
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("数据库驱动加载成功");
 
			// 获取数据库连接对象
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library?serverTimezone=Hongkong&useUnicode=true&characterEncoding=utf8&useSSL=false",
					"root", "12345");
			System.out.println("数据库连接成功");
 
		} catch (ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		} catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
}