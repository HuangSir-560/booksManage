package bookmanage_java201710;

import java.awt.*;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

public class Form_return extends JInternalFrame {

	private JDesktopPane Desktop;
	private DbUtil dbUtil = new DbUtil();
	private JPanel contentPane;
	private JTable table;

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

	public String bookStringNum;

	public Form_return() {

		setEnabled(false);
		setClosable(true);
		setTitle("����");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btn_searchborrowed = new JButton("�������\u67E5\u8BE2\u4F60\u5DF2\u501F\u56FE\u4E66");
		btn_searchborrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ��ѯ�����ѽ���
				searchborrowedActtionPerformed(arg0);
			}
		});

		JLabel lblNewLabel_2 = new JLabel("\u4F60\u5DF2\u501F\u4E66\u76EE\u5982\u4E0B\uFF1A");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNewButton = new JButton("�黹ѡ�е�ͼ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnAction(e);
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 441,
														Short.MAX_VALUE)
												.addContainerGap())
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel_2)
												.addContainerGap(355, Short.MAX_VALUE))))
						.addGroup(
								gl_contentPane.createSequentialGroup().addGap(143)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 155,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(163, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(btn_searchborrowed).addContainerGap(286, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(btn_searchborrowed)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_2).addGap(10)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addGap(33)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(72, Short.MAX_VALUE)));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u4E66\u53F7", "\u4E66\u540D", "\u4F5C\u8005", "\u8FD8\u4E66\u65E5\u671F" }));
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table); // �ñ��б�����ʾ����

		// �������ѡ��
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int a = 0;
				a = table.getSelectedRow();
				String bookNameString = table.getValueAt(a, 0).toString().trim();
				System.out.println(a);
				System.out.println(table.getValueAt(a, 0));
//				if (bookNameString.indexOf(" ") != -1) {
//					bookNameString = bookNameString.substring(0, bookNameString.indexOf(" "));
//				}
				bookStringNum = bookNameString;
//				System.out.println(bookStringNum);
			}
		});

		contentPane.setLayout(gl_contentPane);
	}

	public Form_return(JDesktopPane table1) {
		this();
		this.Desktop = table1;
		// ���ô��������ʾ
		this.setLocation((table1.getWidth() - this.getWidth()) / 2, (table1.getHeight() - this.getHeight()) / 2);
	}

	protected void searchborrowedActtionPerformed(ActionEvent e) {

		String unamString = Form_login.un;

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0); // ���ó�0��

		/// �������ݿ�
		Connection con = null;
		try {// ���ݿ��ѯ������䵽JTABLE
			con = dbUtil.getCon();

			// �������д���ݿ������䣬��ִ�С�
			Statement stmt = con.createStatement();// ����SQL�������
			String sql = "select ���,����,����,convert(char(10),��������,120) as ��������  from borrow_info where ����֤��='"
					+ unamString + "'"; // �����ѯ���
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

	public void returnAction(ActionEvent event) {

		int unameCardNumber = Integer.parseInt(Form_login.un);

		/// �������ݿ�
		Connection con = null;
		try {// ���ݿ��ѯ������䵽JTABLE
			con = dbUtil.getCon();

			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0); // ���ó�0��
			// �������д���ݿ������䣬��ִ�С�
			Statement stmt = con.createStatement();// ����SQL�������
			String sql = "delete from ���� where ����֤��=" + unameCardNumber + " AND ���= '" + bookStringNum + "'"; // �����ѯ���
			System.out.println(sql);
			PreparedStatement pstm = con.prepareStatement(sql);
//			pstm.executeUpdate();
			JOptionPane.showMessageDialog(null, "����ɹ���");
//			int i = 0;
//			while (rs.next()) {
//				i = i + 1;
//				Vector v = new Vector();
//				v.add(rs.getString("���"));
//				v.add(rs.getString("����"));
//				v.add(rs.getString("����"));
//				v.add(rs.getString("��������"));
//				dtm.addRow(v);
//			}
//			if (i == 0) {
//				JOptionPane.showMessageDialog(null, "����֤����Ч��ý���֤�ŵĶ�����δ���飡");
//			}
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
