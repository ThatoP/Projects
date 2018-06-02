/**
 * @author Thato Puoetsile
 * @aboutAuthor B.Eng (Information and Communication Engineering) 
 * @user Records Management Unit
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HomeScreen extends JFrame{

	int width, height;
	static HomeScreen home;
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				home = new HomeScreen();
			};
		});
	}
	
	public HomeScreen() {
		initUI();
	}
	
	public void initUI() {
		ImageIcon img = new ImageIcon("C:\\Users\\tpuoetsile\\Pictures\\DTEF Logo.jpg");
		width = img.getIconWidth();
		height = img.getIconHeight();
		setSize(width,height);
		setTitle("Records Management Unit Database - Home");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		this.setIconImage(new ImageIcon("C:\\Users\\tpuoetsile\\Pictures\\dtef.jpg").getImage()); 
		JLabel back = new JLabel(img);
		back.setLayout(null);
		add(back);
		
		//a button to usher user to a new Mail Processing form
		JButton newForm = new JButton("Mail Processing Form");
		newForm.setBounds((width/2)- 250, height-200, 200, 50);
		back.add(newForm);
		newForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.dispose();
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						MailProcessingForm form = new MailProcessingForm();
					}
				});
			}
		});
		
		//a button to close the application
		JButton closeApp = new JButton("Close Application");
		closeApp.setBounds((width/2)+50, height-200, 200, 50);
		back.add(closeApp);
		closeApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        System.exit(0);
			}
		});
	}
}
