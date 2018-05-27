/**
 * @author Thato Puoetsile
 */
import java.awt.*;
import javax.swing.*;

public class HomeScreen extends JFrame{

	public static void main(String[] args) {
		
	}
	
	public HomeScreen() {
		
	}
	
	public void initUI() {
		ImageIcon img = new ImageIcon("C:\\\\Users\\\\tpuoetsile\\\\Pictures\\\\DTEF Logo.jpg");
		setSize(img.getIconWidth(),img.getIconHeight());
		setTitle("Records Management Unit Database");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
	}
}
