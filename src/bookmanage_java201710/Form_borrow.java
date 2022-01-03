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
		setTitle("��\u501F\u4E66");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("��\u8F93\u5165\u4F60\u7684\u501F\u4E66\u8BC1\u53F7\uFF1A");

		txt_readerno = new JTextField();
		txt_readerno.setColumns(10);

		JButton btn_searchborrowed = new JButton("\u67E5\u8BE2\u4F60\u5DF2\u501F\u56FE\u4E66");
		btn_searchborrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ��ѯ�����ѽ���
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
				// ��ѯδ���ͼ��
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
		scrollPane.setViewportView(table); // �ñ��б�����ʾ����
		contentPane.setLayout(gl_contentPane);
	}

	public Form_borrow(JDesktopPane table1) {
		this();
		this.Desktop = table1;
		// ���ô��������ʾ
		this.setLocation((table1.getWidth() - this.getWidth()) / 2, (table1.getHeight() - this.getHeight()) / 2);
	}

	protected void borrowebook(ActionEvent e) {
		// ��ѯ���ߺŶ�Ӧ�Ķ��߽����嵥

		String readerno = txt_readerno.getText().trim();
		// JOptionPane.showMessageDialog(null, readerno);
		if (readerno == null || readerno.length() == 0) {
			JOptionPane.showMessageDialog(null, "����֤����Ч��");
		} else {

			if (combox.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "���󣬿ն����뵥������ѯδ���ͼ�顱��ť");
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
				dtm.setRowCount(0); // ���ó�0��

				/// �������ݿ�
				Connection con = null;
				try {// ���ݿ��ѯ������䵽JTABLE
					con = dbUtil.getCon();

					// �������дִ�д洢������䣬��ִ�С�
					CallableStatement cstmt = con.prepareCall("{call if_borrow (?,?,?)}");

					cstmt.setString(1, readerno); // ���õ�һ���������
					cstmt.setString(2, bno); // ���õ�һ���������
					cstmt.registerOutParameter("succ", java.sql.Types.INTEGER); // �����������

					// ResultSet rs=cstmt.executeQuery();
					// //���洢���̽�����м�ʱ����executeQuery
					cstmt.execute(); // ���洢���̽�������м�ʱ ����execute
					k = cstmt.getInt("succ"); // ������������Ľ����k.

					if (k == 1) { // �Ѿ��ɹ�����
						JOptionPane.showMessageDialog(null, "����ɹ���");

					} else {
						JOptionPane.showMessageDialog(null, "�ö��߽������ѵ������ޣ������ٽ裡");
					}
					// cstmt.close();// �ر������������
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
		// ��ѯ�ö���δ���飬����combox
		combox.removeAllItems(); // �൱��ˢ��combox
		/// �������ݿ�
		Connection con = null;
		try {// ���ݿ��ѯ������䵽JTABLE
			con = dbUtil.getCon();
			// JOptionPane.showMessageDialog(null, "�������ӳɹ���");

			// �������д���ݿ������䣬��ִ�С�
			Statement stmt = con.createStatement();// ����SQL�������
			String sql = "select ���+' ; '+����+'  '+����+'  '+������+'  '+������  as 'info' from ͼ��  where ״̬='δ��' order by ���"; // �����ѯ���
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if (!(rs.next())) {
				JOptionPane.showMessageDialog(null, "û��δ���ͼ���ˣ�");
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
		// ��ѯ���ߺŶ�Ӧ�Ķ��߽����嵥
		String readerno = txt_readerno.getText();

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0); // ���ó�0��

		/// �������ݿ�
		Connection con = null;
		try {// ���ݿ��ѯ������䵽JTABLE
			con = dbUtil.getCon();

			// �������д���ݿ������䣬��ִ�С�
			Statement stmt = con.createStatement();// ����SQL�������
			String sql = "select ���,����,����,convert(char(10),��������,120) as ��������  from borrow_info where ����֤��='" + readerno
					+ "'"; // �����ѯ���
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			int i = 0;
			while (rs.next()) {
				i = i + 1;
				Vector v = new Vector();
				v.add(rs.getString("���"));
				v.add(rs.getString("����"));
				v.add(rs.getString("����"));
				v.add(rs.getString("��������"));
				dtm.addRow(v);
			}
			if (i == 0) {
				JOptionPane.showMessageDialog(null, "����֤����Ч��ý���֤�ŵĶ�����δ���飡");
			}
			con.close();// �ر������������
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
