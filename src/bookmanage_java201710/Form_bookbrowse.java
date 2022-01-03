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
				//JOptionPane.showMessageDialog(null, "单击了调用存储过程按钮！");
				SearchBySpActtionPerformed(arg0);	
			}
		});
		panel.add(btnNewButton);
		
	//下面这一段是控制表格显示的，设置列数、列名	
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
		//设置窗体居中显示
		this.setLocation((table1.getWidth()-this.getWidth())/2,(table1.getHeight()-this.getHeight())/2);
	}
	
	protected void SearchBySpActtionPerformed(ActionEvent e) {
		//调用存储过程实现查询		
		String bookName=txtbookname.getText();
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0); // 设置成0行

	///连接数据库	
		Connection con=null;
		try{//数据库查询，并填充到JTABLE
			con=dbUtil.getCon();
			
		//下面可以写数据库访问语句，并执行。			 
			CallableStatement cstmt = con.prepareCall("{call searchbook (?)}");
			cstmt.setString(1, bookName);  //设置第一个输入参数
			//cstmt.setString(1, textField.getText());  //设置第一个输入参数
			//cstmt.registerOutParameter("average", java.sql.Types.DECIMAL);  //设置输出输入参数
			ResultSet rs=cstmt.executeQuery(); //当存储过程结果是行集时 ，用executeQuery
			// cstmt.execute();                //当存储过程结果不是行集时 ，用execute
			// txtResult.setText(cstmt.getString("average"));	//将第二个参数的结果给文本框txtResult.		    
  		//	
		/*	
		建议：先检索结果，再检索 OUT 参数 
		由于某些 DBMS 的限制，为了实现最大的可移植性，
		建议先检索由执行 CallableStatement 对象所产生的结果，
		然后再用 CallableStatement.getXXX 方法来检索 OUT 参数。 
		
		如果 CallableStatement 对象返回多个 ResultSet 对象（通过调用 execute 方法），
		在检索 OUT 参数前应先检索所有的结果。
		这种情况下，为确保对所有的结果都进行了访问，
		必须对 Statement 方法 getResultSet、getUpdateCount 和 getMoreResults 进行调用，
		直到不再有结果为止。 				
		*/   
  
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("书号"));
				v.add(rs.getString("书名"));
				v.add(rs.getString("作者"));
				v.add(rs.getString("状态"));
				v.add(rs.getString("书库"));
				dtm.addRow(v);	
			}	       
		     		       
			// 数据库查询结束,最后关闭连接
			// cstmt.close();// 关闭命令对象连接
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
		dtm.setRowCount(0); // 设置成0行

	///连接数据库	
		Connection con=null;
		try{//数据库查询，并填充到JTABLE
			con=dbUtil.getCon();
		JOptionPane.showMessageDialog(null, "数据连接成功！");
			
			//下面可以写数据库访问语句，并执行。			
	       Statement stmt = con.createStatement();// 创建SQL命令对象		
	       String sql = "select 书号,书名,作者,状态,书库  from 图书 where 书名 like '%"+bookName +"%'";  // 定义查询语句
	      	PreparedStatement pstm=con.prepareStatement(sql);
			ResultSet rs=pstm.executeQuery();			
		//	JOptionPane.showMessageDialog(null, "查询成功！");
			
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("书号"));
				v.add(rs.getString("书名"));
				v.add(rs.getString("作者"));
				v.add(rs.getString("状态"));
				v.add(rs.getString("书库"));
				dtm.addRow(v);	
			}	       
		     		       
			// 数据库查询结束,最后关闭连接
			stmt.close();// 关闭命令对象连接
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
