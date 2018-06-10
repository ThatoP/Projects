/**
 * @author Thato Puoetsile
 * @aboutAuthor B. Eng (Information and Communication Engineering), ITIL
 * @user Records Management Unit
 */
//import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
//import com.mysql.cj.xdevapi.Statement;

public class DBConnection {
	
	static Connection con = null;
	static PreparedStatement stmt = null;
	static Statement myStmt = null;
	static ResultSet res = null;
	
	public static void main(String[] args) {
		DBConnection dbcon = new DBConnection();
		dbcon.createConnection();
		//System.out.println("Database Connection Successful!!");
	}
	
	public static void createConnection() {
		try{
			//Get a connection to the database
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmunit?useSSL=false","root","RMUnit");
		}
		catch (ClassNotFoundException ex){
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,ex);
		}
		catch (SQLException ex) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	public static void save (String[] data) {
		createConnection(); //Get a connection to the database
		try {
			String str = data[0];
			myStmt = con.createStatement(); //Creating a statement
			String dbop = "INSERT INTO rmunit.inmail VALUES ('" + data[0] + "','" + data[1] + "', '" + data[2] + "', '" + data[3] + "', '"
					+ data[4] + "', '"+ data[5] +"', '" + data[6] + "', '" + data[7] + "')";
			//INSERT INTO RMUNIT VALUES ('RefNum','DateOnLetter','OrigDept','Subject','DateRec','ActionOfficer','DateMarked','Days');
			myStmt.execute(dbop);
			JOptionPane.showMessageDialog(null, "This record has been saved successfully!", "RECORD SAVED", 1);
			myStmt.close();
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "This record has not been saved!", "WARNING", 0);
		}
	}
	
	public static String[] fetchRecord (String str) {
		createConnection();
		String[] data = new String[8];
		try {
			// Prepare the statement
			stmt = con.prepareStatement("SELECT * FROM rmunit.inmail WHERE RefNumber=?");
			
			// Set parameters
			stmt.setString(1, str);
			
			// Execute SQL query
			res = stmt.executeQuery();
			
			//Passing the results to the display
			if (res.first()) {
				res.beforeFirst(); //moving the pointer back to the start of the row of results
				while (res.next()) {
					data[0] = res.getString("RefNumber");
					data[1] = res.getString("DateOnLetter");
					data[2] = res.getString("OriginDept");
					data[3] = res.getString("Subject");
					data[4] = res.getString("DateRec");
					data[5] = res.getString("ActionOfficer");
					data[6] = res.getString("Datemarked");
					data[7] = res.getString("Days");
					MailProcessingForm.save.setEnabled(true);
				}
			}else
				JOptionPane.showMessageDialog(null, "The record could not be found.\n Please confirm the Reference Number.", "WARNING",0);
			stmt.close();
		}
		catch (SQLException ex) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Oops!! Something went wrong!.", "WARNING", 0);
		}
		return data;
	}
	
	public static void delete (String str) {
		createConnection();
		
		try {
			//creating the statement
			myStmt = con.createStatement();
			
			//Setting the parameters
			String dbop = "DELETE FROM rmunit.inmail WHERE Refnumber='"+str+"'";
			
			//Executing the statement
			myStmt.execute(dbop);
			myStmt.close();
			
		}catch (SQLException ex) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Oops!! Something went wrong!.", "WARNING", 0);
		}
	}
}
