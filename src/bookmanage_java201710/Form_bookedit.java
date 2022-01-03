package bookmanage_java201710;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import com.sun.xml.internal.bind.v2.model.annotation.FieldLocatable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Form_bookedit extends JInternalFrame {
	private JDesktopPane table=null;
	private JTable bookTable;
	private DbUtil dbUtil= new DbUtil();
	private JTextField txt_bookname_search;
	private JTextField txt_bookno;
	private JTextField txt_bookname;
	//private JTextArea txt_author=null ;
	private JTextArea txt_author;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_bookedit frame = new Form_bookedit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Form_bookedit(JDesktopPane table) {
		this();
		this.table=table;	
		
		//设置窗体居中显示
		this.setLocation((table.getWidth()-this.getWidth())/2,(table.getHeight()-this.getHeight())/2);
		
		fillTable("");
	}
	
	private void fillTable(String bookname) {
		// TODO Auto-generated method stub
		DefaultTableModel dtm= (DefaultTableModel)bookTable.getModel();
		dtm.setRowCount(0);//清空原来的记录
				
		///连接数据库	
		Connection con=null;
		try{//数据库查询，并填充到JTABLE
			con=dbUtil.getCon();
					
			//下面可以写数据库访问语句，并执行。			
	       Statement stmt = con.createStatement();// 创建SQL命令对象		
	       String sql = "select 书号,书名,作者  from 图书 where 书名 like '%"+bookname.trim() +"%'";  // 定义查询语句
	      	PreparedStatement pstm=con.prepareStatement(sql);
			ResultSet rs=pstm.executeQuery();			
		//	JOptionPane.showMessageDialog(null, "查询成功！");
			
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("书号"));
				v.add(rs.getString("书名"));
				v.add(rs.getString("作者"));
				dtm.addRow(v);	
			}	       
		     		       
			// 数据库查询结束,最后关闭连接
			stmt.close();// 关闭命令对象连接
			}
		 catch(Exception e1)
		    {
			e1.printStackTrace();
		     }
//		   finally
//		   {
//			try {
//				dbUtil.closeCon(con);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		   }
	}

	/**
	 * Create the frame.
	 */
	public Form_bookedit() {
		setClosable(true);
		setTitle("\u56FE\u4E66\u4FE1\u606F\u4FEE\u6539");
		setBounds(100, 100, 499, 442);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("\u4E66\u540D\uFF1A");
		
		txt_bookname_search = new JTextField();
		txt_bookname_search.setColumns(10);
		
		JButton btn_search = new JButton("\u67E5\u8BE2");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchActionPerformed(e);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txt_bookname_search, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(btn_search))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane))))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(txt_bookname_search, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btn_search)
							.addGap(17)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
		);
		
		JLabel label = new JLabel("\u4E66\u53F7\uFF1A");
		
		txt_bookno = new JTextField();
		txt_bookno.setEditable(false);
		txt_bookno.setColumns(10);
		
		JLabel label_1 = new JLabel("\u4E66\u540D\uFF1A");
		
		txt_bookname = new JTextField();
		txt_bookname.setColumns(10);
		
		JLabel label_2 = new JLabel("\u4F5C\u8005\uFF1A");
		
		txt_author = new JTextArea();
		
		JButton btn_modify = new JButton("\u4FEE\u6539");
		btn_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyActionPerformed(e);
			}
		});
		
		JButton btn_delete = new JButton("\u5220\u9664");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelActionPerformed(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txt_bookno, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txt_author)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txt_bookname, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(32)
							.addComponent(btn_modify)
							.addGap(18)
							.addComponent(btn_delete)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(txt_bookno, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(txt_bookname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(txt_author, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_modify)
						.addComponent(btn_delete))
					.addGap(20))
		);
		panel.setLayout(gl_panel);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MousePressed(e);
			}
		});
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u4E66\u53F7", "\u4E66\u540D", "\u4F5C\u8005"
			}
		));
		scrollPane.setViewportView(bookTable);
		getContentPane().setLayout(groupLayout);
		
		// 设置文本域边框
		txt_author.setBorder(new LineBorder(new java.awt.Color(127, 157, 185), 1, false));
	}

	private void DelActionPerformed(ActionEvent evt) {
		if (txt_bookno.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "请选择一条要删除的记录！");
			return;
		}		
		Connection conn=null;
		//
		int count=0;
		String bookno=txt_bookno.getText().toString();		
		String sql="delete from 图书  where 书号=?";	
		//
		try {
			conn=dbUtil.getCon();
			PreparedStatement pstm=conn.prepareStatement(sql);
			pstm.setString(1,bookno);		
			//删除选中图书			
			count=pstm.executeUpdate();	
			if (count==0){
				JOptionPane.showMessageDialog(null, "删除失败！");
			}
			else{
				JOptionPane.showMessageDialog(null, "删除成功！");
				fillTable("");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (conn!=null)
					dbUtil.closeCon(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void ModifyActionPerformed(ActionEvent evt) {
		if (txt_bookno.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "请选择一条要修改的记录！");
			return;
		}
		
		Connection conn=null;	
		String sql="update 图书 set 书名=?,作者=? where 书号=? ";
		
		try {
			conn=dbUtil.getCon();
			
			PreparedStatement pstm=conn.prepareStatement(sql);
			pstm.setString(1, txt_bookname.getText());
			pstm.setString(2, txt_author.getText());
			pstm.setString(3, txt_bookno.getText());			
			
			int count=pstm.executeUpdate();
			if (count==0){
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
			else{
				JOptionPane.showMessageDialog(null, "修改成功！");
				fillTable("");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (conn!=null)
					dbUtil.closeCon(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	protected void MousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int currRow=bookTable.getSelectedRow();
		txt_bookno.setText(bookTable.getValueAt(currRow, 0).toString());
		txt_bookname.setText(bookTable.getValueAt(currRow, 1).toString());
		txt_author.setText(bookTable.getValueAt(currRow, 2).toString());
	}

	private void SearchActionPerformed(ActionEvent e) {
		// 根据书名进行查找
		fillTable(txt_bookname_search.getText().trim()); 	      
	}
}
