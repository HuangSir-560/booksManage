package bookmanage_java201710;

import java.sql.*;
public class test_connect_to_sqlserver {

	/*	������֮ǰ������Ӻ�SQL SERVER ��JDBC����	*/
	public static void main(String[] args) {
		/*	���ӵ�STUDENTS���ݿ�  */
		
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
		String connectDB ="jdbc:sqlserver://localhost;DatabaseName=books"; 
		//String connectDB ="jdbc:sqlserver://win7_32;DatabaseName=teaching"; ��ȷ
		//String connectDB ="jdbc:sqlserver://127.0.0.1;DatabaseName=teaching"; ��ȷ
					
		try {
			Class.forName(JDriver);// �������ݿ����棬���ظ����ַ���������
		    }
		catch (ClassNotFoundException e) {
			// e.printStackTrace();
			System.out.println("�������ݿ�����ʧ��");
			System.exit(0);
		   }
		System.out.println("���ݿ������ɹ�");

		try {
			String user = "sa";
			String password = "123456";
			Connection con = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
			System.out.println("�������ݿ�ɹ�");	
			con.close();// �ر����ݿ�����
			} 
	   catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݿ����Ӵ���");
			System.exit(0);
		}
	} 		
}


