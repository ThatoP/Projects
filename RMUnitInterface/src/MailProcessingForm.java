/**
 * @author Thato Puoetsile
 * @user Records Management Unit
 */

//import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import javax.swing.*;


public class MailProcessingForm extends JFrame{
	
	int w,h;
//	public static void main(String[] args) {
//		MailProcessingForm mail = new MailProcessingForm();
//		mail.createConnection();
//	}
	public MailProcessingForm() {
		initGui();
	}
	
//	void createConnection() {
//		try{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmunit","root","RMUnit");
//			System.out.println("Database Connection Successful!!");
//			//useSSL = false;
//			// A warning is issued about connecting without server's id is not recommended. shouldn't be a big deal
//		}
//		catch (ClassNotFoundException ex){
//			Logger.getLogger(MailProcessingForm.class.getName()).log(Level.SEVERE,null,ex);
//		}
//		catch (SQLException ex) {
//			Logger.getLogger(MailProcessingForm.class.getName()).log(Level.SEVERE,null,ex);
//		}
//	}
	
	public void initGui() {
		w = 1500;
		h = 1000;
		JFrame frame = new JFrame();
		frame.setSize(w, h);
		frame.setTitle("Records Management Unit Database - Mail Processing Form");
		frame.setIconImage(new ImageIcon("C:\\Users\\tpuoetsile\\Pictures\\dtef.jpg").getImage());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);;
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel title = new JLabel("Department of Tertiary Education Financing");
		title.setFont(new Font("Arial",Font.BOLD,40));
		title.setBounds((w/2)-400, 0, 1000, 100);
		frame.getContentPane().add(title);
		
		//row 1
		JLabel ref = new JLabel("Reference Number");
		ref.setFont(new Font("Arial",Font.BOLD,18));
		ref.setBounds(500,100,200,50); 
		frame.getContentPane().add(ref);
		
		JTextField refText = new JTextField();
		refText.setFont(new Font("Arial",Font.PLAIN,18));
		refText.setBounds(750,100,300,50);
		frame.getContentPane().add(refText);
		
		//row 2
		JLabel letterDate = new JLabel("Date On Letter");
		letterDate.setFont(new Font("Arial",Font.BOLD,18));
		letterDate.setBounds(500,180,200,50); 
		frame.getContentPane().add(letterDate);
		
		JTextField letterDateText = new JTextField();
		letterDateText.setFont(new Font("Arial",Font.PLAIN,18));
		letterDateText.setBounds(750,180,300,50); 
		frame.getContentPane().add(letterDateText);
		
		//row 3
		JLabel origDept = new JLabel("Originating Department");
		origDept.setFont(new Font("Arial",Font.BOLD,18));
		origDept.setBounds(500,260,250,50);
		frame.getContentPane().add(origDept);
		
		JTextField origDeptText = new JTextField();
		origDeptText.setFont(new Font("Arial",Font.PLAIN,18));
		origDeptText.setBounds(750,260,300,50);
		frame.getContentPane().add(origDeptText);
		
		//row 4
		JLabel subject = new JLabel("Subject");
		subject.setFont(new Font("Arial",Font.BOLD,18));
		subject.setBounds(500,340,200,50);
		frame.getContentPane().add(subject);
		
		JTextField subjText = new JTextField();
		subjText.setFont(new Font("Arial",Font.PLAIN,18));
		subjText.setBounds(750,340,450,50);
		frame.getContentPane().add(subjText);
		
		//row 5
		JLabel dateRec = new JLabel("Date Received");
		dateRec.setFont(new Font("Arial",Font.BOLD,18));
		dateRec.setBounds(500,420,200,50);
		frame.getContentPane().add(dateRec);
		
		JTextField dateRecText = new JTextField();
		dateRecText.setFont(new Font("Arial",Font.PLAIN,18));
		dateRecText.setBounds(750,420,300,50);
		frame.getContentPane().add(dateRecText);
		
		//row 6
		JLabel action = new JLabel("Action Officer");
		action.setFont(new Font("Arial",Font.BOLD,18));
		action.setBounds(500,500,200,50);
		frame.getContentPane().add(action);
		
		JTextField actionText= new JTextField();
		actionText.setFont(new Font("Arial",Font.PLAIN,18));
		actionText.setBounds(750,500,300,50);
		frame.getContentPane().add(actionText);
		
		//row 7
		JLabel dateMarked = new JLabel("Date Marked");
		dateMarked.setFont(new Font("Arial",Font.BOLD,18));
		dateMarked.setBounds(500,580,200,50);
		frame.getContentPane().add(dateMarked);
		
		JTextField dateMarkedText= new JTextField();
		dateMarkedText.setFont(new Font("Arial",Font.PLAIN,18));
		dateMarkedText.setBounds(750,580,300,50);
		frame.getContentPane().add(dateMarkedText);
		
		//row 8
		JLabel days = new JLabel("Days Taken To Mark");
		days.setFont(new Font("Arial",Font.BOLD,18));
		days.setBounds(500,660,200,50);
		frame.getContentPane().add(days);
		
		JTextField daysText= new JTextField();
		daysText.setFont(new Font("Arial",Font.PLAIN,18));
		daysText.setBounds(750,660,300,50);
		frame.getContentPane().add(daysText);
		
		JSeparator sep = new JSeparator();
		frame.getContentPane().add(sep);
		
		JButton save = new JButton("Save");
		save.setFont(new Font("Arial",Font.BOLD,14));
		save.setBounds(400, 850, 150, 50);
		frame.getContentPane().add(save);
		
		JButton find = new JButton("Find");
		find.setFont(new Font("Arial",Font.BOLD,14));
		find.setBounds(700, 850, 150, 50);
		frame.getContentPane().add(find);
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton home = new JButton("Home");
		home.setFont(new Font("Arial",Font.BOLD,14));
		home.setBounds(1000, 850, 150, 50);
		frame.getContentPane().add(home);
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.dispose();
				int n = JOptionPane.showConfirmDialog(null,"The information you have entered may not have been saved. \n"  +
													"Do you want to leave this page anyway?", "Warning!",JOptionPane.YES_NO_OPTION, 
													JOptionPane.WARNING_MESSAGE);
				if(n== JOptionPane.YES_OPTION) {
					frame.dispose();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							HomeScreen home = new HomeScreen();
							home.setVisible(true);
						}
					});
				}
			}
		});
		
		//frame.pack();
	}
	
}
