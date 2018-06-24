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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmunit?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL",
					"root","RMUnit");
			//System.out.println("Database connection has been created!");
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
			String dbop = "INSERT INTO rmunit.inmail (RefNumber, DateOnLetter, OriginDept, Subject, DateRec, ActionOfficer, DateMarked,"
					+ "Days, ActDate,DaysToAct)VALUES (?,?,?,?,?,?,?,?,?,?)";
			
			//Creating a statement
			stmt = con.prepareStatement(dbop); 
			
			// Set parameters
			stmt.setString(1, data[0]);
			stmt.setNull(2, java.sql.Types.DATE);
			stmt.setString(3, data[2]);
			stmt.setString(4, data[3]);
			stmt.setNull(5, java.sql.Types.DATE);
			stmt.setString(6, data[5]);
			stmt.setNull(7, java.sql.Types.DATE);
			stmt.setString(8, data[7]);
			stmt.setNull(9, java.sql.Types.DATE);
			stmt.setString(10, data[9]);
			
			// Executing the String
			res = stmt.executeQuery();
			
			
			//INSERT INTO RMUNIT VALUES ('RefNum','DateOnLetter','OrigDept','Subject','DateRec','ActionOfficer','DateMarked','Days');
			//String dbop = "INSERT INTO rmunit.inmail VALUES ('" + data[0] + "','" + data[1] + "', '" + data[2] + "', '" + data[3] + "', '"
			//		+ data[4] + "', '"+ data[5] +"', '" + data[6] + "', '" + data[7] + "','"+ data[8] +"', '"+ data[9] +"')";
			//System.out.println(dbop);
			//myStmt.execute(dbop);
			
			while (res.next()) {
			      for (int i = 1; i <= 10; i++) {
			        System.out.print(res.getString(i) + "  ,");
			      }
			      System.out.println();
			    }

			JOptionPane.showMessageDialog(null, "This record has been saved successfully!", "RECORD SAVED", 1);
			myStmt.close();
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "This record has not been saved!", "WARNING", 0);
		}
	}
	
	public static String[] fetchRecord (String str) {
		createConnection();
		String[] data = new String[10];
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
					data[8] = res.getString("ActDate");
					data[9] = res.getString("DaysToAct");
					
					//MailProcessingForm.save.setEnabled(true);
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
			
			if(str.equals(null)) {
				JOptionPane.showMessageDialog(null, "Reference number is invalid!!", "WARNING", 0);
				myStmt.close();
			}else {
				myStmt.execute(dbop);
				myStmt.close();
			}
			
			//Executing the statement
			//myStmt.execute(dbop);
			
			//closing the connection
			//myStmt.close();
			
		}catch (SQLException ex) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Oops!! Something went wrong!.", "WARNING", 0);
		}
	}
}
