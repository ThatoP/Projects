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
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

//import org.joda.time.*;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;


public class MailProcessingForm extends JFrame{
	
	int w,h;
	public JTextField refText, origDeptText, subjText;
	public JTextField actionText, daysText, days2actText;
	public static JDateChooser letterDateText, inActDate, dateRecText, dateMarkedText;
	public static JButton save;
	String ldt, ad,drt,dmt;
	
	public MailProcessingForm() {
		initGui();
	}
	
	public MailProcessingForm(String[] data) {
		initGui();
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
		String[] data = new String[10];
		
		//row 1
		JLabel ref = new JLabel("Reference Number");
		ref.setFont(new Font("Arial",Font.BOLD,18));
		ref.setBounds(500,150,200,30); 
		frame.getContentPane().add(ref);
		
		refText = new JTextField();
		refText.setFont(new Font("Arial",Font.PLAIN,16));
		refText.setBounds(750,150,300,30);
		frame.getContentPane().add(refText);
		
		//row 2
		JLabel letterDate = new JLabel("Date On Letter");
		letterDate.setFont(new Font("Arial",Font.BOLD,18));
		letterDate.setBounds(500,210,200,30); 
		frame.getContentPane().add(letterDate);
		
		letterDateText = new JDateChooser();
		letterDateText.setFont(new Font("Arial",Font.PLAIN,16));
		letterDateText.setBounds(750,210,300,30); 
		frame.getContentPane().add(letterDateText);
		letterDateText.setDateFormatString("yyyy-MM-dd");
		
		//row 3
		JLabel origDept = new JLabel("Originating Department");
		origDept.setFont(new Font("Arial",Font.BOLD,18));
		origDept.setBounds(500,270,250,30);
		frame.getContentPane().add(origDept);
		
		origDeptText = new JTextField();
		origDeptText.setFont(new Font("Arial",Font.PLAIN,16));
		origDeptText.setBounds(750,270,300,30);
		frame.getContentPane().add(origDeptText);
		
		//row 4
		JLabel subject = new JLabel("Subject");
		subject.setFont(new Font("Arial",Font.BOLD,18));
		subject.setBounds(500,330,200,30);
		frame.getContentPane().add(subject);
		
		subjText = new JTextField();
		subjText.setFont(new Font("Arial",Font.PLAIN,16));
		subjText.setBounds(750,330,450,30);
		frame.getContentPane().add(subjText);
		
		//row 5
		JLabel dateRec = new JLabel("Date Received");
		dateRec.setFont(new Font("Arial",Font.BOLD,18));
		dateRec.setBounds(500,390,200,30);
		frame.getContentPane().add(dateRec);
		
		dateRecText = new JDateChooser();
		dateRecText.setFont(new Font("Arial",Font.PLAIN,16));
		dateRecText.setBounds(750,390,300,30); 
		frame.getContentPane().add(dateRecText);
		dateRecText.setDateFormatString("yyyy-MM-dd");
		
		//row 6
		JLabel action = new JLabel("Action Officer");
		action.setFont(new Font("Arial",Font.BOLD,18));
		action.setBounds(500,450,200,30);
		frame.getContentPane().add(action);
		
		actionText= new JTextField();
		actionText.setFont(new Font("Arial",Font.PLAIN,16));
		actionText.setBounds(750,450,300,30);
		frame.getContentPane().add(actionText);
		
		//row 7
		// Field should allow to be input at a later date, and so it follows that Days taken field should be input later
		JLabel dateMarked = new JLabel("Date Marked");
		dateMarked.setFont(new Font("Arial",Font.BOLD,18));
		dateMarked.setBounds(500,510,200,30);
		frame.getContentPane().add(dateMarked);
		
		dateMarkedText = new JDateChooser();
		dateMarkedText.setFont(new Font("Arial",Font.PLAIN,16));
		dateMarkedText.setBounds(750,510,300,30); 
		frame.getContentPane().add(dateMarkedText);
		dateMarkedText.setDateFormatString("yyyy-MM-dd");
		Document doc = ((JTextField)dateMarkedText.getDateEditor().getUiComponent()).getDocument();
		doc.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent d) {
			}

			@Override
			public void insertUpdate(DocumentEvent d) {
				calculate();
			}

			@Override
			public void removeUpdate(DocumentEvent d) {
				daysText.setText(null);
			}
			
			public void calculate () {
				Instant start;
				Instant end;
				//if (((JTextField)dateRecText.getDateEditor().getUiComponent()).getText() != null && 
				//((JTextField)dateMarkedText.getDateEditor().getUiComponent()).getText() != null) {}
				if (((JTextField)dateMarkedText.getDateEditor().getUiComponent()).getText().charAt(0)=='0') {
					//((JTextField)dateMarkedText.getDateEditor().getUiComponent()).setText("1000-01-01");
					daysText.setText(null);
					System.out.println("Date marked hasn't been entered yet.");
				}else {
					start = dateRecText.getDate().toInstant();
					end = dateMarkedText.getDate().toInstant();
					Duration diff = Duration.between(start, end);
					daysText.setText(String.valueOf(diff.toDays()));
					daysText.setEditable(false);
				}
			}
			
		});
		
		//row 8
		JLabel days = new JLabel("Days Taken To Mark");
		days.setFont(new Font("Arial",Font.BOLD,18));
		days.setBounds(500,570,200,30);
		frame.getContentPane().add(days);
		
		daysText= new JTextField();
		daysText.setFont(new Font("Arial",Font.PLAIN,16));
		daysText.setBounds(750,570,300,30);
		frame.getContentPane().add(daysText);
		
		//row 9
		JLabel actDate = new JLabel("Action Date");
		actDate.setFont(new Font("Arial",Font.BOLD,18));
		actDate.setBounds(500,630,200,30); 
		frame.getContentPane().add(actDate);
		
		inActDate = new JDateChooser();
		inActDate.setFont(new Font("Arial",Font.PLAIN,16));
		inActDate.setBounds(750,630,300,30); 
		frame.getContentPane().add(inActDate);
		inActDate.setDateFormatString("yyyy-MM-dd");
		Document dc = ((JTextField)inActDate.getDateEditor().getUiComponent()).getDocument();
		dc.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				calc();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				days2actText.setText(null);
			}
			
			public void calc () {
				Instant start;
				Instant end;
				//if (((JTextField)dateMarkedText.getDateEditor().getUiComponent()).getText() != null && 
						//((JTextField)inActDate.getDateEditor().getUiComponent()).getText() != null) {
					
				if (((JTextField)inActDate.getDateEditor().getUiComponent()).getText().charAt(0)=='0') {
						//((JTextField)inActDate.getDateEditor().getUiComponent()).setText("1000-01-01");
						days2actText.setText(null);
						System.out.println("Action date field is empty!");
					}
					else {
						start = dateMarkedText.getDate().toInstant();
						end = inActDate.getDate().toInstant();
						Duration diff = Duration.between(start, end);
						days2actText.setText(String.valueOf(diff.toDays()));
						days2actText.setEditable(false);
					}
			}
			
		});
		
		//row 10
		JLabel days2act = new JLabel("Days Taken to Act");
		days2act.setFont(new Font("Arial",Font.BOLD,18));
		days2act.setBounds(500,690,200,30); 
		frame.getContentPane().add(days2act);
		
		days2actText= new JTextField();
		days2actText.setFont(new Font("Arial",Font.PLAIN,16));
		days2actText.setBounds(750,690,300,30);
		frame.getContentPane().add(days2actText);
		
		//save button and its action listener
		JButton save = new JButton("Add Record");
		save.setFont(new Font("Arial",Font.BOLD,14));
		save.setBounds(100, 800, 150, 50);
		frame.getContentPane().add(save);
		//for now, the save button requires that date fields never be null
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ldt = ((JTextField)letterDateText.getDateEditor().getUiComponent()).getText();
				drt = ((JTextField)dateRecText.getDateEditor().getUiComponent()).getText();
				dmt = ((JTextField)dateMarkedText.getDateEditor().getUiComponent()).getText();
				ad = ((JTextField)inActDate.getDateEditor().getUiComponent()).getText();
				
				data[0] = refText.getText();
				data[1] = ldt;
				data[2] = origDeptText.getText();
				data[3] = subjText.getText();
				data[4] = drt;
				data[5] = actionText.getText();
				data[6] = dmt;
				data[7] = daysText.getText();
				data[8] = ad;
				data[9] = days2actText.getText();
				
				DBConnection.saveRecord(data);
				
				refText.setText(null);
				letterDateText.setDate(null);
				origDeptText.setText(null);
				subjText.setText(null);
				dateRecText.setDate(null);
				actionText.setText(null);
				dateMarkedText.setDate(null);
				daysText.setText(null);
				inActDate.setDate(null);
				days2actText.setText(null);
				daysText.setEditable(true);
				days2actText.setEditable(true);
			}
		});
		
		//find button searches for a record in the database
		JButton find = new JButton("Find Record");
		find.setFont(new Font("Arial",Font.BOLD,14));
		find.setBounds(300, 800, 150, 50);
		frame.getContentPane().add(find);
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ref = refText.getText();
				String[] data = DBConnection.fetchRecord(ref);
				if (data != null) {
					refText.setText(data[0]);
					((JTextField)letterDateText.getDateEditor().getUiComponent()).setText(data[1]);
					origDeptText.setText(data[2]);
					subjText.setText(data[3]);
					((JTextField)dateRecText.getDateEditor().getUiComponent()).setText(data[4]);
					actionText.setText(data[5]);
					((JTextField)dateMarkedText.getDateEditor().getUiComponent()).setText(data[6]);
					daysText.setText(data[7]);
					((JTextField)inActDate.getDateEditor().getUiComponent()).setText(data[8]);
					days2actText.setText(data[9]);
				} 
			}
		});
		
		//a button to clear all the information the the textfields hold
		JButton clear = new JButton("Clear Text");
		clear.setFont(new Font("Arial",Font.BOLD,14));
		clear.setBounds(700, 800, 150, 50);
		frame.getContentPane().add(clear);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refText.setText(null);
				letterDateText.setDate(null);
				origDeptText.setText(null);
				subjText.setText(null);
				dateRecText.setDate(null);
				actionText.setText(null);
				dateMarkedText.setDate(null);
				daysText.setText(null);
				inActDate.setDate(null);
				days2actText.setText(null);
				daysText.setEditable(true);
				days2actText.setEditable(true);
			}
		});
		
		//The Delete button deletes a record from the database
		
		JButton del = new JButton("Delete Record");
		del.setFont(new Font("Arial",Font.BOLD,14));
		del.setBackground(Color.RED);
		del.setBounds(900, 800, 150, 50);
		frame.getContentPane().add(del);
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ref = refText.getText();
				if(ref != null) {
					int n = JOptionPane.showConfirmDialog(null,"The information displayed will be deleted from the database.\n "
							+ "Do you want to delete this record?", "Warning!",JOptionPane.YES_NO_OPTION, 
							JOptionPane.WARNING_MESSAGE);
					if (n== JOptionPane.YES_OPTION) {
						DBConnection.deleteRecord(ref);
						JOptionPane.showMessageDialog(null, "This record has been deleted permanently", "DELETE SUCCESSFUL", 1);
						refText.setText(null);
						letterDateText.setDate(null);
						origDeptText.setText(null);
						subjText.setText(null);
						dateRecText.setDate(null);
						actionText.setText(null);
						dateMarkedText.setDate(null);
						daysText.setText(null);
						inActDate.setDate(null);
						days2actText.setText(null);
						daysText.setEditable(true);
						days2actText.setEditable(true);
					}
				}
			}
		});
		
		//The Update button updates a record in the database
		JButton upd = new JButton("Update Record");
		upd.setFont(new Font("Arial",Font.BOLD,14));
		upd.setBounds(500, 800, 150, 50);
		frame.getContentPane().add(upd);
		upd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ldt = ((JTextField)letterDateText.getDateEditor().getUiComponent()).getText();
				drt = ((JTextField)dateRecText.getDateEditor().getUiComponent()).getText();
				dmt = ((JTextField)dateMarkedText.getDateEditor().getUiComponent()).getText();
				ad = ((JTextField)inActDate.getDateEditor().getUiComponent()).getText();
				
				data[0] = refText.getText();
				data[1] = ldt;
				data[2] = origDeptText.getText();
				data[3] = subjText.getText();
				data[4] = drt;
				data[5] = actionText.getText();
				data[6] = dmt;
				data[7] = daysText.getText();
				data[8] = ad;
				data[9] = days2actText.getText();
				
				DBConnection.updateRecord(data);
				
				refText.setText(null);
				letterDateText.setDate(null);
				origDeptText.setText(null);
				subjText.setText(null);
				dateRecText.setDate(null);
				actionText.setText(null);
				dateMarkedText.setDate(null);
				daysText.setText(null);
				inActDate.setDate(null);
				days2actText.setText(null);
				daysText.setEditable(true);
				days2actText.setEditable(true);

			}
		});
		
		//Home button and its action listener
		JButton home = new JButton("Home");
		home.setFont(new Font("Arial",Font.BOLD,14));
		home.setBounds(1250, 800, 150, 50);
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
		
		JMenuItem view = new JMenuItem("View DB");
		menu.add(view);
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						showTable();
					}
				});
			}
		});
		JMenuItem srch = new JMenuItem("Search By");
		menu.add(srch);
		
		JMenuItem reps = new JMenuItem("Reports");
		menu.add(reps);
	}
	
	public static void showTable() {
		
		//Setting up the frame that is to contain the table
		JFrame fr = new JFrame();
		fr.setSize(1500,800);
		fr.setTitle("Records Management Unit - Complete Database");
		fr.setIconImage(new ImageIcon("C:\\Users\\tpuoetsile\\Pictures\\dtef.jpg").getImage());
		fr.setVisible(true);
		fr.setLocationRelativeTo(null);
		fr.getContentPane().setBackground(Color.WHITE);
		
		//Add table created in DBConnection class to JScrollPanel and then to the JFrame
		fr.add(new JScrollPane(DBConnection.getDBTable()));	
	}
}
