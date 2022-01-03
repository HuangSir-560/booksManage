package bookmanage_java201710;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;



public class Form_login extends JFrame {
	private DbUtil dbUtil =new DbUtil();
	private JPanel contentPane;
	private JTextField txt_username;
	private JTextField txt_password;
	private JComboBox usertype;

	public static String un;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_login frame = new Form_login();
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
	/**
	 * 
	 */
	public Form_login() {
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 402, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setBounds(131, 24, 114, 21);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 18));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setBounds(65, 135, 54, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setBounds(65, 175, 54, 15);
		contentPane.add(lblNewLabel_2);

		txt_username = new JTextField();
		txt_username.setBounds(147, 132, 176, 21);
		contentPane.add(txt_username);
		txt_username.setColumns(10);

		txt_password = new JTextField();
		txt_password.setBounds(147, 172, 176, 21);
		contentPane.add(txt_password);
		txt_password.setColumns(10);

		JButton btn_login = new JButton("\u767B\u5F55");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ����û��������룬���е�¼��֤
				login(arg0);
			}
		});
		btn_login.setBounds(165, 222, 93, 23);
		contentPane.add(btn_login);

		JLabel lblNewLabel_3 = new JLabel("\u7528\u6237\u7C7B\u522B\uFF1A");
		lblNewLabel_3.setBounds(65, 92, 72, 15);
		contentPane.add(lblNewLabel_3);

		usertype = new JComboBox();
		usertype.addItem("����");
		usertype.addItem("����Ա");
		usertype.setBounds(147, 89, 176, 21);
		contentPane.add(usertype);
	}

	protected void login(ActionEvent e) {
		// ����û��������룬���е�¼��֤
		String c;
		un=txt_username.getText().trim();
		String pw=txt_password.getText().trim();
		Connection con=null;
		
		c=usertype.getSelectedItem().toString();
		
		if (c=="����"){
			//����Ƕ���
			try{			
			con=dbUtil.getCon();			
			//Statement stmt =con.createStatement();
		
			java.sql.Statement stmt=con.createStatement();
			String sql = "select * from ���� where ����֤��='"+ un +"' and ����='"+ pw +"'"; // �����ѯ���
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {	
				//�������������				
				Form_reader frmMain=new Form_reader();
				frmMain.setVisible(true);					
			}
			else{
				JOptionPane.showMessageDialog(null, "����֤����Ч���������");
				return;
			}				
			}
			catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		else{//����ǹ���Ա
			try{			
				con=dbUtil.getCon();			
				//Statement stmt =con.createStatement();
			
				java.sql.Statement stmt=con.createStatement();
				String sql = "select * from ����Ա where ����='"+ un +"' and ����='"+ pw +"'"; // �����ѯ���
				PreparedStatement pstm = con.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				if (rs.next()) {	
					//�������Ա������				
					Form_admin frmMain=new Form_admin();
					frmMain.setVisible(true);					
				}
				else{
					JOptionPane.showMessageDialog(null, "����Ա������Ч���������");
					return;
				}				
				}
				catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						dbUtil.closeCon(con);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}		
			
			
			
		}
	}

}
