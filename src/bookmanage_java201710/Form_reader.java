package bookmanage_java201710;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Form_reader extends JFrame {

	private JPanel contentPane;
	private JDesktopPane JDesktopPane1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_reader frame = new Form_reader();
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
	public Form_reader() {
		setTitle("\u8BFB\u8005\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("\u67E5\u8BE2\u4E0E\u501F\u9605");
		menuBar.add(menu);

		JMenuItem menuItem_bookbrowse = new JMenuItem("\u6D4F\u89C8\u56FE\u4E66");
		menuItem_bookbrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				brActionPerformed(e);
			}
		});

		menu.add(menuItem_bookbrowse);

		JMenuItem menuItem_bookboorrow = new JMenuItem("\u501F\u4E66");

		menuItem_bookboorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bbActionPerformed(e);
			}
		});

		menu.add(menuItem_bookboorrow);

		JMenuItem menuItem_bookruturn = new JMenuItem("还书");
		menuItem_bookruturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				breturnActionPerformed(e);
			}
		});
		menu.add(menuItem_bookruturn);

		JMenu menu_1 = new JMenu("\u7CFB\u7EDF");

		menuBar.add(menu_1);

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
		menu_1.add(menuItem_exit);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JDesktopPane1 = new JDesktopPane();
		contentPane.add(JDesktopPane1, BorderLayout.CENTER);
		JDesktopPane1.setLayout(null);
	}

	private void bbActionPerformed(ActionEvent e) {
		bookmanage_java201710.Form_borrow subwindow = new Form_borrow(JDesktopPane1);
		subwindow.setVisible(true);
		JDesktopPane1.add(subwindow);
	}

	private void breturnActionPerformed(ActionEvent e) {
		bookmanage_java201710.Form_return subwindow = new Form_return(JDesktopPane1);
		subwindow.setVisible(true);
		JDesktopPane1.add(subwindow);
	}

	private void brActionPerformed(ActionEvent e) {
		bookmanage_java201710.Form_bookbrowse subwindow = new Form_bookbrowse(JDesktopPane1);
		subwindow.setVisible(true);
		JDesktopPane1.add(subwindow);
	}
}
