package bookmanage_java201710;

import java.awt.*;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

public class Form_borrow extends JInternalFrame {
	private JDesktopPane Desktop;
	private DbUtil dbUtil = new DbUtil();
	private JPanel contentPane;
	private JTextField txt_readerno;
	private JTable table;
	private JComboBox combox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_borrow frame = new Form_borrow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Form_borrow() {

		setEnabled(false);
		setClosable(true);
		setTitle("来\u501F\u4E66");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("这\u8F93\u5165\u4F60\u7684\u501F\u4E66\u8BC1\u53F7\uFF1A");

		txt_readerno = new JTextField();
		txt_readerno.setColumns(10);

		JButton btn_searchborrowed = new JButton("\u67E5\u8BE2\u4F60\u5DF2\u501F\u56FE\u4E66");
		btn_searchborrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 查询读者已借书
				searchborrowedActtionPerformed(arg0);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("\u9009\u62E9\u4F60\u8981\u501F\u7684\u56FE\u4E66\uFF1A");

		JLabel lblNewLabel_2 = new JLabel("\u4F60\u5DF2\u501F\u4E66\u76EE\u5982\u4E0B\uFF1A");

		JButton btn_borrow = new JButton("\u8C03\u7528\u5B58\u50A8\u8FC7\u7A0B\u501F\u4E66");
		btn_borrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrowebook(arg0);
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JButton btn_search_notborrowed = new JButton("\u67E5\u8BE2\u672A\u501F\u51FA\u56FE\u4E66");
		btn_search_notborrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 查询未借出图书
				searchnotborrowedActtionPerformed(arg0);

			}
		});

		combox = new JComboBox();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_contentPane.createSequentialGroup()
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 441,
														Short.MAX_VALUE)
												.addContainerGap())
								.addGroup(
										gl_contentPane.createSequentialGroup()
												.addComponent(combox, GroupLayout.PREFERRED_SIZE, 257,
														GroupLayout.PREFERRED_SIZE)
												.addGap(17).addComponent(btn_borrow).addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txt_readerno, GroupLayout.PREFERRED_SIZE, 125,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
										.addComponent(btn_searchborrowed).addGap(29))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel_2)
										.addContainerGap(355, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btn_search_notborrowed).addContainerGap(222, Short.MAX_VALUE)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(txt_readerno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_searchborrowed))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_2).addGap(10)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(btn_search_notborrowed))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btn_borrow)
								.addComponent(combox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(76, Short.MAX_VALUE)));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u4E66\u53F7", "\u4E66\u540D", "\u4F5C\u8005", "\u8FD8\u4E66\u65E5\u671F" }));
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table); // 让表列标题显示出来
		contentPane.setLayout(gl_contentPane);
	}

	public Form_borrow(JDesktopPane table1) {
		this();
		this.Desktop = table1;
		// 设置窗体居中显示
		this.setLocation((table1.getWidth() - this.getWidth()) / 2, (table1.getHeight() - this.getHeight()) / 2);
	}

	protected void borrowebook(ActionEvent e) {
		// 查询读者号对应的读者借书清单

		String readerno = txt_readerno.getText().trim();
		// JOptionPane.showMessageDialog(null, readerno);
		if (readerno == null || readerno.length() == 0) {
			JOptionPane.showMessageDialog(null, "借书证号无效！");
		} else {

			if (combox.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "错误，空对象，请单击“查询未借出图书”按钮");
			} else {
				String bname = combox.getSelectedItem().toString().trim();
				// JOptionPane.showMessageDialog(null, bname);

				String bno;
				int k;
				int i = -1;

				i = bname.indexOf(";");
				bno = bname.substring(0, i - 1);
				// JOptionPane.showMessageDialog(null,Integer.toString(i));
				// JOptionPane.showMessageDialog(null, bno);

				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0); // 设置成0行

				/// 连接数据库
				Connection con = null;
				try {// 数据库查询，并填充到JTABLE
					con = dbUtil.getCon();

					// 下面可以写执行存储过程语句，并执行。
					CallableStatement cstmt = con.prepareCall("{call if_borrow (?,?,?)}");

					cstmt.setString(1, readerno); // 设置第一个输入参数
					cstmt.setString(2, bno); // 设置第一个输入参数
					cstmt.registerOutParameter("succ", java.sql.Types.INTEGER); // 设置输出参数

					// ResultSet rs=cstmt.executeQuery();
					// //当存储过程结果是行集时，用executeQuery
					cstmt.execute(); // 当存储过程结果不是行集时 ，用execute
					k = cstmt.getInt("succ"); // 将第输出参数的结果给k.

					if (k == 1) { // 已经成功插入
						JOptionPane.showMessageDialog(null, "借书成功！");

					} else {
						JOptionPane.showMessageDialog(null, "该读者借书数已到达上限，不能再借！");
					}
					// cstmt.close();// 关闭命令对象连接
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						dbUtil.closeCon(con);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}

	protected void searchnotborrowedActtionPerformed(ActionEvent e) {
		// 查询该读者未借书，更新combox
		combox.removeAllItems(); // 相当于刷新combox
		/// 连接数据库
		Connection con = null;
		try {// 数据库查询，并填充到JTABLE
			con = dbUtil.getCon();
			// JOptionPane.showMessageDialog(null, "数据连接成功！");

			// 下面可以写数据库访问语句，并执行。
			Statement stmt = con.createStatement();// 创建SQL命令对象
			String sql = "select 书号+' ; '+书名+'  '+作者+'  '+出版社+'  '+出版年  as 'info' from 图书  where 状态='未借' order by 书号"; // 定义查询语句
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if (!(rs.next())) {
				JOptionPane.showMessageDialog(null, "没有未借出图书了！");
			} else {
				while (rs.next()) {
					combox.addItem(rs.getString("info"));
				}
				// combox.setVisible(isEnabled());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	protected void searchborrowedActtionPerformed(ActionEvent e) {
		// 查询读者号对应的读者借书清单
		String readerno = txt_readerno.getText();

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0); // 设置成0行

		/// 连接数据库
		Connection con = null;
		try {// 数据库查询，并填充到JTABLE
			con = dbUtil.getCon();

			// 下面可以写数据库访问语句，并执行。
			Statement stmt = con.createStatement();// 创建SQL命令对象
			String sql = "select 书号,书名,作者,convert(char(10),还书日期,120) as 还书日期  from borrow_info where 借书证号='" + readerno
					+ "'"; // 定义查询语句
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			int i = 0;
			while (rs.next()) {
				i = i + 1;
				Vector v = new Vector();
				v.add(rs.getString("书号"));
				v.add(rs.getString("书名"));
				v.add(rs.getString("作者"));
				v.add(rs.getString("还书日期"));
				dtm.addRow(v);
			}
			if (i == 0) {
				JOptionPane.showMessageDialog(null, "借阅证号无效或该借阅证号的读者尚未借书！");
			}
			con.close();// 关闭命令对象连接
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
