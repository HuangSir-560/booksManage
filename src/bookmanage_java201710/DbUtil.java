package bookmanage_java201710;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	//private String dbUrl="jdbc:mysql://localhost:3306/db_book";//链接字符串
	//private String dbUserName="root";//用户名
	//private String dbPassword="gao";//密码
	//private String jdbcName="com.mysql.jdbc.Driver";//驱动名称
	
	private String jdbcName= "com.microsoft.sqlserver.jdbc.SQLServerDriver";// 驱动名称,SQL数据库引擎
	//private String dbUrl="jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;DatabaseName=books";//链接字符串 
	private String dbUrl="jdbc:sqlserver://localhost;DatabaseName=books";//链接字符串
	//状态
	
//	private String dbUserName="sa";//用于连接的登录名
//	private String dbPassword="sa";//密码	
	private String dbUserName="sa";//用于连接的登录名
	private String dbPassword="123456";//密码	
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);//使用反射加载驱动
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);//获取连接对象
		return con;		
	}
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception {
		if (con!=null){
			con.close();
		}		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();

		try {
			dbUtil.getCon();
			System.out.println("连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("连接失败");
		}
		
	}
}
