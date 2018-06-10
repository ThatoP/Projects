/**
 * @author Thato Puoetsile
 * @aboutAuthor B.Eng (Information and Communication Engineering)
 * @user Records Management Unit
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.awt.*;
import javax.swing.*;


public class MailProcessingForm extends JFrame{
	
	int w,h;
	public JTextField refText, letterDateText, origDeptText, subjText;
	public JTextField dateRecText, actionText, dateMarkedText, daysText;
	public static JButton save;
	
	public MailProcessingForm() {
		initGui();
	}
	
	public MailProcessingForm(String[] data) {
		initGui();
		refText.setText(data[0]);
		letterDateText.setText(data[1]);
		origDeptText.setText(data[2]);
		subjText.setText(data[3]);
		dateRecText.setText(data[4]);
		actionText.setText(data[5]);
		dateMarkedText.setText(data[6]);
		daysText.setText(data[7]);
	}
	
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
		title.setBounds((w/2)-400, 50, 1000, 100);
		frame.getContentPane().add(title);
		String[] data = new String[8];
		
		//row 1
		JLabel ref = new JLabel("Reference Number");
		ref.setFont(new Font("Arial",Font.BOLD,18));
		ref.setBounds(500,150,200,50); 
		frame.getContentPane().add(ref);
		
		refText = new JTextField();
		refText.setFont(new Font("Arial",Font.PLAIN,18));
		refText.setBounds(750,150,300,50);
		frame.getContentPane().add(refText);
		
		//row 2
		JLabel letterDate = new JLabel("Date On Letter");
		letterDate.setFont(new Font("Arial",Font.BOLD,18));
		letterDate.setBounds(500,230,200,50); 
		frame.getContentPane().add(letterDate);
		
		letterDateText = new JTextField();
		letterDateText.setFont(new Font("Arial",Font.PLAIN,18));
		letterDateText.setBounds(750,230,300,50); 
		frame.getContentPane().add(letterDateText);
		
		//row 3
		JLabel origDept = new JLabel("Originating Department");
		origDept.setFont(new Font("Arial",Font.BOLD,18));
		origDept.setBounds(500,310,250,50);
		frame.getContentPane().add(origDept);
		
		origDeptText = new JTextField();
		origDeptText.setFont(new Font("Arial",Font.PLAIN,18));
		origDeptText.setBounds(750,310,300,50);
		frame.getContentPane().add(origDeptText);
		
		//row 4
		JLabel subject = new JLabel("Subject");
		subject.setFont(new Font("Arial",Font.BOLD,18));
		subject.setBounds(500,390,200,50);
		frame.getContentPane().add(subject);
		
		subjText = new JTextField();
		subjText.setFont(new Font("Arial",Font.PLAIN,18));
		subjText.setBounds(750,390,450,50);
		frame.getContentPane().add(subjText);
		
		//row 5
		JLabel dateRec = new JLabel("Date Received");
		dateRec.setFont(new Font("Arial",Font.BOLD,18));
		dateRec.setBounds(500,470,200,50);
		frame.getContentPane().add(dateRec);
		
		dateRecText = new JTextField();
		dateRecText.setFont(new Font("Arial",Font.PLAIN,18));
		dateRecText.setBounds(750,470,300,50);
		frame.getContentPane().add(dateRecText);
		
		//row 6
		JLabel action = new JLabel("Action Officer");
		action.setFont(new Font("Arial",Font.BOLD,18));
		action.setBounds(500,550,200,50);
		frame.getContentPane().add(action);
		
		actionText= new JTextField();
		actionText.setFont(new Font("Arial",Font.PLAIN,18));
		actionText.setBounds(750,550,300,50);
		frame.getContentPane().add(actionText);
		
		//row 7
		// Field should allow to be input at a later date, and so it follows that Days taken field should be input later
		JLabel dateMarked = new JLabel("Date Marked");
		dateMarked.setFont(new Font("Arial",Font.BOLD,18));
		dateMarked.setBounds(500,630,200,50);
		frame.getContentPane().add(dateMarked);
		
		dateMarkedText= new JTextField();
		dateMarkedText.setFont(new Font("Arial",Font.PLAIN,18));
		dateMarkedText.setBounds(750,630,300,50);
		frame.getContentPane().add(dateMarkedText);
		
		//row 8
		JLabel days = new JLabel("Days Taken To Mark");
		days.setFont(new Font("Arial",Font.BOLD,18));
		days.setBounds(500,710,200,50);
		frame.getContentPane().add(days);
		
		daysText= new JTextField();
		daysText.setFont(new Font("Arial",Font.PLAIN,18));
		daysText.setBounds(750,710,300,50);
		frame.getContentPane().add(daysText);
		
		//save button and its action listener
		save = new JButton("Add Record");
		save.setFont(new Font("Arial",Font.BOLD,14));
		save.setBounds(100, 850, 150, 50);
		frame.getContentPane().add(save);
		//for now, the save button requires that date fields never be null
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data[0] = refText.getText();
				data[1] = letterDateText.getText();
				data[2] = origDeptText.getText();
				data[3] = subjText.getText();
				data[4] = dateRecText.getText();
				data[5] = actionText.getText();
				data[6] = dateMarkedText.getText();
				data[7] = daysText.getText();
				
//				for(int i=0; i<8; i++) {
//					if (data[i] == null) {
//						if (i!=1 || i!=4 || i!=6)
//							data[i] = "";
//					}
//				}
				DBConnection.save(data);
				
				refText.setText(null);
				letterDateText.setText(null);
				origDeptText.setText(null);
				subjText.setText(null);
				dateRecText.setText(null);
				actionText.setText(null);
				dateMarkedText.setText(null);
				daysText.setText(null);
			}
		});
		
		//find button searches for a record in the database
		JButton find = new JButton("Find Record");
		find.setFont(new Font("Arial",Font.BOLD,14));
		find.setBounds(300, 850, 150, 50);
		frame.getContentPane().add(find);
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ref = refText.getText();
				String[] data = DBConnection.fetchRecord(ref);
				if (data != null) {
					//save.setEnabled(false);
					refText.setText(data[0]);
					letterDateText.setText(data[1]);
					origDeptText.setText(data[2]);
					subjText.setText(data[3]);
					dateRecText.setText(data[4]);
					actionText.setText(data[5]);
					dateMarkedText.setText(data[6]);
					daysText.setText(data[7]);
				} 
			}
		});
		
		//a button to clear all the information the the textfields hold
		JButton clear = new JButton("Clear Text");
		clear.setFont(new Font("Arial",Font.BOLD,14));
		clear.setBounds(500, 850, 150, 50);
		frame.getContentPane().add(clear);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refText.setText(null);
				letterDateText.setText(null);
				origDeptText.setText(null);
				subjText.setText(null);
				dateRecText.setText(null);
				actionText.setText(null);
				dateMarkedText.setText(null);
				daysText.setText(null);
			}
		});
		
		//The Delete button deletes a record from the database
		
		JButton del = new JButton("Delete Record");
		del.setFont(new Font("Arial",Font.BOLD,14));
		del.setBounds(700, 850, 150, 50);
		frame.getContentPane().add(del);
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ref = refText.getText();
				if(ref != null) {
					int n = JOptionPane.showConfirmDialog(null,"The information displayed will be deleted from the database.\n "
							+ "Do you want to delete this record?", "Warning!",JOptionPane.YES_NO_OPTION, 
							JOptionPane.WARNING_MESSAGE);
					if (n== JOptionPane.YES_OPTION) {
						DBConnection.delete(ref);
						String[] data = DBConnection.fetchRecord(ref);
						if(data == null) {
							JOptionPane.showMessageDialog(null, "This record has been deleted permanently", "DELETE SUCCESSFUL", 1);
						}
					}
				}
			}
		});
		
		//Home button and its action listener
		JButton home = new JButton("Home");
		home.setFont(new Font("Arial",Font.BOLD,14));
		home.setBounds(1250, 850, 150, 50);
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
		
		JMenuBar jmb = new JMenuBar();
		frame.setJMenuBar(jmb);
		
		JMenu menu = new JMenu("Menu");
		jmb.add(menu);
		
		JMenuItem option = new JMenuItem();
		menu.add(option);
		
		JMenuItem refresh = new JMenuItem();
		menu.add(refresh);
		
	}
}
