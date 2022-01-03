package bookmanage_java201710;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import javax.swing.event.AncestorListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import java.awt.Frame;

public class Form_admin extends JFrame {

	private JPanel contentPane;
	private JDesktopPane JDesktopPane1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_admin frame = new Form_admin();
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
	public Form_admin() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("\u7BA1\u7406\u5458\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("\u56FE\u4E66\u6570\u636E\u7EF4\u62A4");
		menuBar.add(mnNewMenu);

		JMenuItem mitem_addbook = new JMenuItem("\u6DFB\u52A0\u65B0\u4E66");
		mitem_addbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bdActionPerformed(e);
			}
		});

		JMenuItem mitem_bookbrowse = new JMenuItem("\u67E5\u8BE2\u56FE\u4E66");
		mitem_bookbrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				brActionPerformed(e);
			}
		});

		mnNewMenu.add(mitem_bookbrowse);

		mnNewMenu.add(mitem_addbook);

		JMenuItem mitem_editbook = new JMenuItem("\u4FEE\u6539\u56FE\u4E66\u4FE1\u606F");
		mitem_editbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beActionPerformed(e);
			}
		});

		//
		mnNewMenu.add(mitem_editbook);

		JMenu menu_1 = new JMenu("\u8BFB\u8005\u4FE1\u606F\u7BA1\u7406");
		menuBar.add(menu_1);

		JMenuItem mitem_serchreader = new JMenuItem("\u67E5\u8BE2\u8BFB\u8005**");
		mitem_serchreader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srActionPerformed(e);
			}
		});
		menu_1.add(mitem_serchreader);

		JMenuItem mitem_addReader = new JMenuItem("添加新读者");

		menu_1.add(mitem_addReader);

		JMenu menu = new JMenu("\u7CFB\u7EDF");
		menuBar.add(menu);

		// 退出系统菜单项
		JMenuItem menuItem_exit = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		menuItem_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "确定退出吗？");
				if (result == 0) {
					dispose();
				}
			}
		});
		menu.add(menuItem_exit);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		// 注意：将contentPane设置为BorderLayout

		JDesktopPane1 = new JDesktopPane();
		contentPane.add(JDesktopPane1);
	}

	private void bdActionPerformed(ActionEvent e) {
		bookmanage_java201710.Form_bookadd bookadd = new Form_bookadd(JDesktopPane1);
		bookadd.setVisible(true);
		JDesktopPane1.add(bookadd);
	}

	private void brActionPerformed(ActionEvent e) {
		bookmanage_java201710.Form_bookbrowse subwindow = new Form_bookbrowse(JDesktopPane1);
		subwindow.setVisible(true);
		JDesktopPane1.add(subwindow);
	}

	private void srActionPerformed(ActionEvent e) {
		bookmanage_java201710.Form_searchreader subwindow = new Form_searchreader(JDesktopPane1);
		subwindow.setVisible(true);
		JDesktopPane1.add(subwindow);
	}

	private void beActionPerformed(ActionEvent e) {
		bookmanage_java201710.Form_bookedit subwindow = new Form_bookedit(JDesktopPane1);
		subwindow.setVisible(true);
		JDesktopPane1.add(subwindow);
	}
}
