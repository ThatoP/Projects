/**
 * @author Thato Puoetsile
 * @aboutAuthor B.Eng (Information and Communication Engineering), ITIL
 * @user Records Management Unit
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class Login extends JFrame{
	
	static JTextField utxt;
	static JPasswordField ptxt;
	static JFrame frame;
	
	public Login() {
		init();
	}
	
	/*
	 * @passKeyPressed method to use the press of the Enter key to log user in
	 * 
	 */
	public void passKeyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			String uname = utxt.getText();
			char[] pass = ptxt.getPassword();
			String pword = String.valueOf(pass);
			if(DBConnection.checkLogin(uname,pword)) {
				frame.dispose();
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new MailProcessingForm();
					}
				});
			}
			else {
				utxt.setText(null);
				ptxt.setText(null);
			}
		}
	}
	
	public static void init() {
		frame = new JFrame();
		frame.setSize(375,450);
		frame.setTitle("RMU - Log In");
		frame.setIconImage(new ImageIcon("C:\\Users\\tpuoetsile\\Pictures\\dtef.jpg").getImage());
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setResizable(false);
		
		JLabel lbl1 = new JLabel("Username:");
		lbl1.setBounds(100, 50, 150, 30);
		lbl1.setFont(new Font("Arial", Font.BOLD,14));
		frame.getContentPane().add(lbl1);
		
		utxt = new JTextField();
		utxt.setBounds(100, 90, 150, 50);
		utxt.setFont(new Font("Arial", Font.PLAIN,14));
		frame.getContentPane().add(utxt);
		
		JLabel lbl2 = new JLabel("Password:");
		lbl2.setBounds(100, 160, 150, 30);
		lbl2.setFont(new Font("Arial", Font.BOLD,14));
		frame.getContentPane().add(lbl2);
		
		ptxt = new JPasswordField();
		ptxt.setBounds(100, 190, 150, 50);
		frame.getContentPane().add(ptxt);
		
		JButton login = new JButton("Log In");
		login.setBounds(125,300, 100, 30);
		login.setFont(new Font("Arial",Font.BOLD,14));
		frame.add(login);
		
		//to verify the user and the user's password
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = utxt.getText();
				char[] pass = ptxt.getPassword();
				String pword = String.valueOf(pass);
				if(DBConnection.checkLogin(uname,pword)) {
					frame.dispose();
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							new MailProcessingForm();
						}
					});
				}
				else {
					utxt.setText(null);
					ptxt.setText(null);
				}
			}
		});
		frame.setVisible(true);
	}
}
