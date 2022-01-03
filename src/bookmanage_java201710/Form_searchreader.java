package bookmanage_java201710;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Form_searchreader extends JInternalFrame {
	private JDesktopPane Desktop;
	private DbUtil dbUtil=new DbUtil();
	private JPanel contentPane;
	private JTextField txt_borrowno;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_searchreader frame = new Form_searchreader();
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
	public Form_searchreader() {
		setBounds(100, 100, 450, 300);
		
		setEnabled(false);
		setClosable(true);	
		setTitle("\u67E5\u8BE2\u8BFB\u8005");
		
		setBounds(100, 100, 481, 339);
	    contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8F93\u5165\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblNewLabel = new JLabel("\u501F\u4E66\u8BC1\u53F7\uFF1A");
		panel.add(lblNewLabel);
		
		txt_borrowno = new JTextField();
	  	panel.add(txt_borrowno);
		txt_borrowno.setColumns(20);
		
		JSeparator separator = new JSeparator();
		panel.add(separator);
		
		JButton btn_search = new JButton("\u67E5\u8BE2");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchActtionPerformed(e);
			}
		});
		panel.add(btn_search);
		
	JScrollPane scrollPane = new JScrollPane(table);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
		);
		
	//������һ���ǿ��Ʊ����ʾ�ģ���������������	
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u501F\u4E66\u8BC1\u53F7", "\u8BFB\u8005\u7C7B\u522B", "\u8BFB\u8005\u59D3\u540D", "\u6700\u591A\u501F\u4E66\u6570", "\u5DF2\u501F\u4E66\u6570"
			}
		));
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane); 
		
       // getContentPane().add(scrollPane,BorderLayout.CENTER);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        /////////////////			
	}
	
	public Form_searchreader(JDesktopPane table1) {
		this();
		this.Desktop=table1;
		//���ô��������ʾ
		this.setLocation((table1.getWidth()-this.getWidth())/2,(table1.getHeight()-this.getHeight())/2);
	}
	
	protected void SearchActtionPerformed(ActionEvent e) {
		String borrowno=txt_borrowno.getText();
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0); // ���ó�0��

	///�������ݿ�	
		Connection con=null;
		try{//���ݿ��ѯ������䵽JTABLE
			con=dbUtil.getCon();
		//JOptionPane.showMessageDialog(null, "�������ӳɹ���");
			
			//�������д���ݿ������䣬��ִ�С�			
	       Statement stmt = con.createStatement();// ����SQL�������		
	       String sql = "select ����֤��,���,����,��������,�ѽ����� from ���� where ����֤�� like '%"+borrowno +"%'";  // �����ѯ���
	      	PreparedStatement pstm=con.prepareStatement(sql);
			ResultSet rs=pstm.executeQuery();			
		//	JOptionPane.showMessageDialog(null, "��ѯ�ɹ���");
			
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("����֤��"));
				v.add(rs.getString("���"));
				v.add(rs.getString("����"));
				v.add(rs.getString("��������"));
				v.add(rs.getString("�ѽ�����"));
				dtm.addRow(v);	
			}	       
		     		       
			// ���ݿ��ѯ����,���ر�����
			stmt.close();// �ر������������
			}
		 catch(Exception e1)
		    {
			e1.printStackTrace();
		     }
		   finally
		   {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   }
	//	*/
	}
}