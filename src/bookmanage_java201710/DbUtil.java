package bookmanage_java201710;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	//private String dbUrl="jdbc:mysql://localhost:3306/db_book";//�����ַ���
	//private String dbUserName="root";//�û���
	//private String dbPassword="gao";//����
	//private String jdbcName="com.mysql.jdbc.Driver";//��������
	
	private String jdbcName= "com.microsoft.sqlserver.jdbc.SQLServerDriver";// ��������,SQL���ݿ�����
	//private String dbUrl="jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;DatabaseName=books";//�����ַ��� 
	private String dbUrl="jdbc:sqlserver://localhost;DatabaseName=books";//�����ַ���
	//״̬
	
//	private String dbUserName="sa";//�������ӵĵ�¼��
//	private String dbPassword="sa";//����	
	private String dbUserName="sa";//�������ӵĵ�¼��
	private String dbPassword="123456";//����	
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);//ʹ�÷����������
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);//��ȡ���Ӷ���
		return con;		
	}
	
	/**
	 * �ر����ݿ�����
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
			System.out.println("���ӳɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("����ʧ��");
		}
		
	}
}
