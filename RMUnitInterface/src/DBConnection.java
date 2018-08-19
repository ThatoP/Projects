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
import java.awt.Font;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
//import com.mysql.cj.xdevapi.Statement;

public class DBConnection {
	
	static Connection con = null;
	static PreparedStatement stmt = null;
	static Statement myStmt = null;
	static ResultSet res = null;
		
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
	
	public static void saveRecord (String[] data) {
		//Get a connection to the database
		createConnection(); 
		
		try {
			myStmt = con.createStatement();
			
			//INSERT INTO RMUNIT VALUES ('RefNum','DateOnLetter','OrigDept','Subject','DateRec','ActionOfficer','DateMarked','Days');
			String dbop = "INSERT INTO rmunit.inmail VALUES ('" + data[0] + "','" + data[1] + "', '" + data[2] + "', '" + data[3] + "', '"
					+ data[4] + "', '"+ data[5] +"', '" + data[6] + "', '" + data[7] + "','"+ data[8] +"', '"+ data[9] +"')";
			
			//Executing the statement
			myStmt.executeUpdate(dbop);

			JOptionPane.showMessageDialog(null, "This record has been saved successfully!", "RECORD SAVED", 1);
			myStmt.close();
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "This record has not been saved!", "WARNING", 0);
			ex.printStackTrace();
		}
	}
	
	public static void updateRecord(String[] data) {
		createConnection();
		//String[] inputData = new String[10];
		
		try {
			// Get Connection
			myStmt = con.createStatement();
			
			//Prepare statement
			String dbop = "UPDATE rmunit.inmail SET DateMarked = '"+data[6]+"',Days = '"+data[7]+"', ActDate = '"+data[8]+"', DaysToAct = '"+data[9]+"' WHERE RefNumber = '"+data[0]+"'";
			
			//Execute SQL query
			myStmt.executeUpdate(dbop);
			
			JOptionPane.showMessageDialog(null, "This record has been successfully updated!", "RECORD SAVED", 1);
			myStmt.close();
		}
		catch(SQLException ex) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Oops!! Unable to update record. /n Please try again.", "WARNING", 0);
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
	
	public static void deleteRecord (String str) {
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
			
		}catch (SQLException ex) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Oops!! Something went wrong!.", "WARNING", 0);
		}
	}
	
	public static JTable getDBTable() {
		createConnection();
		JTable table;
		
		try {
			//Prepare the statement
			stmt = con.prepareStatement("SELECT * FROM rmunit.inmail");
			
			//Set parameters
			
			//Execute query
			res = stmt.executeQuery();
			
			//Casting the result set into a JTable
			table = new JTable (DbUtils.resultSetToTableModel(res));
			table.setEnabled(false);
			table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
			
			stmt.close();
			return table;
			
		}catch (SQLException e) {
			e.getMessage();
			JOptionPane.showMessageDialog(null, "Oops!! Couldn't retrieve database table.", "WARNING", 0);
		}
		return null;
	}
}
