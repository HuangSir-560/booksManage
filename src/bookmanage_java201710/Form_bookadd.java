package bookmanage_java201710;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JInternalFrame;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.sql.*;

import java.awt.event.ActionEvent;
//import javax.swing.JFrame;

public class Form_bookadd extends JInternalFrame {
	private JDesktopPane Desktop;
	private JPanel contentPane;
	private JTextField txtBookNo;
	private JTextField txtBookName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_bookadd frame = new Form_bookadd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param table
	 */
	public Form_bookadd() {

		setEnabled(false);
		setClosable(true);

		setTitle("\u6DFB\u52A0\u56FE\u4E66");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("\u4E66\u53F7\uFF1A");
		JLabel lblNewLabel_1 = new JLabel("\u4E66\u540D\uFF1A");

		txtBookNo = new JTextField();
		txtBookNo.setColumns(10);

		txtBookName = new JTextField();
		txtBookName.setColumns(10);

		// ��Ӱ�ť�Ĵ���
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddActionPerformed(e);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(39)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtBookNo,
														GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel_1)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtBookName))))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(136).addComponent(btnAdd)))
				.addContainerGap(178, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(37)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(
						txtBookNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
						.addComponent(txtBookName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(29).addComponent(btnAdd).addContainerGap(86, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	public Form_bookadd(JDesktopPane table) {
		this();
		this.Desktop = table;
		// ���ô��������ʾ
		this.setLocation((table.getWidth() - this.getWidth()) / 2, (table.getHeight() - this.getHeight()) / 2);
	}

	private void AddActionPerformed(ActionEvent evt) {

		String bookName = txtBookName.getText();
		String bookNo = txtBookNo.getText();

		// 1.��֤������Ч��
		if (bookName.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "ͼ�����Ʋ���Ϊ�գ�");
			//// JOptionPane.showMessageDialog(this, "�û�������Ϊ�գ�", "������Ϣ",
			//// JOptionPane.WARNING_MESSAGE);

			return;
		}
		if (bookNo.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "��Ų���Ϊ�գ�");
			return;
		}
		// ����SQLSERVER���ݿ�--��ʼ
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
		String connectDB = "jdbc:sqlserver://localhost;DatabaseName=books";
		try {
			Class.forName(JDriver);// �������ݿ����棬���ظ����ַ���������
		} catch (ClassNotFoundException e) {
			// System.out.println("�������ݿ�����ʧ��");
			JOptionPane.showMessageDialog(null, "�������ݿ�����ʧ�ܣ�");
			System.exit(0);
		}

		int a = 0;
		try {
			String user = "sa";
			String password = "123456";
			Connection con = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
			// JOptionPane.showMessageDialog(null, "�������ݿ�ɹ���");

			// �������д���ݿ������䣬��ִ�С�
			// ����һ��ͼ���¼
			Statement stmt = con.createStatement();// ����SQL�������
			String sql = "insert into ͼ��(���,����) values ('" + bookNo + "','" + bookName + "')"; // �����ѯ���
//			a = stmt.executeUpdate(sql);// ִ��SQL�������

			if (stmt.executeUpdate(sql) != 1) {
				System.out.println("����");
				System.exit(0);
			}
			JOptionPane.showMessageDialog(null, "���ݲ���ɹ���");

			// ���ر�����
			stmt.close();// �ر������������
			con.close();// �ر����ݿ�����
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݿ����Ӵ���");
			System.exit(0);
		}
		// ���ݿ���ʵ��˽���
		JOptionPane.showMessageDialog(null, "���ͼ��ɹ���");
	}
}
