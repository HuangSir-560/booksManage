package bookmanage_java201710;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 * ʹ��JDBC����MySQL���ݿ�
 * 
 * @author pan_junbiao
 */
public class test_connect_mysql
{
	public static void main(String[] args)
	{
		try
		{
			// �������ݿ�������
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("���ݿ��������سɹ�");
 
			// ��ȡ���ݿ����Ӷ���
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library?serverTimezone=Hongkong&useUnicode=true&characterEncoding=utf8&useSSL=false",
					"root", "12345");
			System.out.println("���ݿ����ӳɹ�");
 
		} catch (ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		} catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
}