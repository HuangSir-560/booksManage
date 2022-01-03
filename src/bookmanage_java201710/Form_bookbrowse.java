package bookmanage_java201710;

import java.awt.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.sql.CallableStatement;

public class Form_bookbrowse extends JInternalFrame {
	private JDesktopPane Desktop;
	private DbUtil dbUtil=new DbUtil();
	private JPanel contentPane;
	private JTextField txtbookname;
	private JTable table;

	/**
	 * Launch the application.
	 */ 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_bookbrowse frame = new Form_bookbrowse();
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
	public Form_bookbrowse() {
		
		
		setEnabled(false);
		setClosable(true);	
		setTitle("\u67E5\u627E\u56FE\u4E66");
		
		setBounds(100, 100, 481, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8F93\u5165\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblNewLabel = new JLabel("\u4E66\u540D\uFF1A");
		panel.add(lblNewLabel);
		
		txtbookname = new JTextField();
		panel.add(txtbookname);
		txtbookname.setColumns(15);
		
		JSeparator separator = new JSeparator();
		panel.add(separator);
		
		JButton btn_search = new JButton("\u641C\u7D22");
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
		
		JButton btnNewButton = new JButton("\u8C03\u7528\u5B58\u50A8\u8FC7\u7A0B\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "�����˵��ô洢���̰�ť��");
				SearchBySpActtionPerformed(arg0);	
			}
		});
		panel.add(btnNewButton);
		
	//������һ���ǿ��Ʊ����ʾ�ģ���������������	
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u4E66\u53F7", "\u4E66\u540D", "\u4F5C\u8005", "\u72B6\u6001", "\u4E66\u5E93"
			}
		));
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);         
       // getContentPane().add(scrollPane,BorderLayout.CENTER);
        /////////////////			
	}
	
	public Form_bookbrowse(JDesktopPane table1) {
		this();
		this.Desktop=table1;
		//���ô��������ʾ
		this.setLocation((table1.getWidth()-this.getWidth())/2,(table1.getHeight()-this.getHeight())/2);
	}
	
	protected void SearchBySpActtionPerformed(ActionEvent e) {
		//���ô洢����ʵ�ֲ�ѯ		
		String bookName=txtbookname.getText();
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0); // ���ó�0��

	///�������ݿ�	
		Connection con=null;
		try{//���ݿ��ѯ������䵽JTABLE
			con=dbUtil.getCon();
			
		//�������д���ݿ������䣬��ִ�С�			 
			CallableStatement cstmt = con.prepareCall("{call searchbook (?)}");
			cstmt.setString(1, bookName);  //���õ�һ���������
			//cstmt.setString(1, textField.getText());  //���õ�һ���������
			//cstmt.registerOutParameter("average", java.sql.Types.DECIMAL);  //��������������
			ResultSet rs=cstmt.executeQuery(); //���洢���̽�����м�ʱ ����executeQuery
			// cstmt.execute();                //���洢���̽�������м�ʱ ����execute
			// txtResult.setText(cstmt.getString("average"));	//���ڶ��������Ľ�����ı���txtResult.		    
  		//	
		/*	
		���飺�ȼ���������ټ��� OUT ���� 
		����ĳЩ DBMS �����ƣ�Ϊ��ʵ�����Ŀ���ֲ�ԣ�
		�����ȼ�����ִ�� CallableStatement �����������Ľ����
		Ȼ������ CallableStatement.getXXX ���������� OUT ������ 
		
		��� CallableStatement ���󷵻ض�� ResultSet ����ͨ������ execute ��������
		�ڼ��� OUT ����ǰӦ�ȼ������еĽ����
		��������£�Ϊȷ�������еĽ���������˷��ʣ�
		����� Statement ���� getResultSet��getUpdateCount �� getMoreResults ���е��ã�
		ֱ�������н��Ϊֹ�� 				
		*/   
  
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("���"));
				v.add(rs.getString("����"));
				v.add(rs.getString("����"));
				v.add(rs.getString("״̬"));
				v.add(rs.getString("���"));
				dtm.addRow(v);	
			}	       
		     		       
			// ���ݿ��ѯ����,���ر�����
			// cstmt.close();// �ر������������
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
	
	protected void SearchActtionPerformed(ActionEvent e) {
		String bookName=txtbookname.getText();
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0); // ���ó�0��

	///�������ݿ�	
		Connection con=null;
		try{//���ݿ��ѯ������䵽JTABLE
			con=dbUtil.getCon();
		JOptionPane.showMessageDialog(null, "�������ӳɹ���");
			
			//�������д���ݿ������䣬��ִ�С�			
	       Statement stmt = con.createStatement();// ����SQL�������		
	       String sql = "select ���,����,����,״̬,���  from ͼ�� where ���� like '%"+bookName +"%'";  // �����ѯ���
	      	PreparedStatement pstm=con.prepareStatement(sql);
			ResultSet rs=pstm.executeQuery();			
		//	JOptionPane.showMessageDialog(null, "��ѯ�ɹ���");
			
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("���"));
				v.add(rs.getString("����"));
				v.add(rs.getString("����"));
				v.add(rs.getString("״̬"));
				v.add(rs.getString("���"));
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
