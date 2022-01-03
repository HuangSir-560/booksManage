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

		// 添加按钮的代码
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
		// 设置窗体居中显示
		this.setLocation((table.getWidth() - this.getWidth()) / 2, (table.getHeight() - this.getHeight()) / 2);
	}

	private void AddActionPerformed(ActionEvent evt) {

		String bookName = txtBookName.getText();
		String bookNo = txtBookNo.getText();

		// 1.验证数据有效性
		if (bookName.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			//// JOptionPane.showMessageDialog(this, "用户名不能为空！", "警告信息",
			//// JOptionPane.WARNING_MESSAGE);

			return;
		}
		if (bookNo.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "书号不能为空！");
			return;
		}
		// 连接SQLSERVER数据库--开始
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String connectDB = "jdbc:sqlserver://localhost;DatabaseName=books";
		try {
			Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类
		} catch (ClassNotFoundException e) {
			// System.out.println("加载数据库引擎失败");
			JOptionPane.showMessageDialog(null, "加载数据库引擎失败！");
			System.exit(0);
		}

		int a = 0;
		try {
			String user = "sa";
			String password = "123456";
			Connection con = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
			// JOptionPane.showMessageDialog(null, "连接数据库成功！");

			// 下面可以写数据库访问语句，并执行。
			// 插入一条图书记录
			Statement stmt = con.createStatement();// 创建SQL命令对象
			String sql = "insert into 图书(书号,书名) values ('" + bookNo + "','" + bookName + "')"; // 定义查询语句
//			a = stmt.executeUpdate(sql);// 执行SQL命令对象

			if (stmt.executeUpdate(sql) != 1) {
				System.out.println("错误");
				System.exit(0);
			}
			JOptionPane.showMessageDialog(null, "数据插入成功！");

			// 最后关闭连接
			stmt.close();// 关闭命令对象连接
			con.close();// 关闭数据库连接
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接错误");
			System.exit(0);
		}
		// 数据库访问到此结束
		JOptionPane.showMessageDialog(null, "添加图书成功！");
	}
}
