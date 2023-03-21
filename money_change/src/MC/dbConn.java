package MC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConn {
	private final String username = "root";
	private final String password = "";
	public Connection myConn;
	private Statement myState;
	private ResultSet myRs;
	
	public dbConn() {
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hehehaha", this.username, this.password);
			
			
		}catch(Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		
		
		
	}
	
	public void setUp() {
		
		try {
			myState = myConn.createStatement();
			
			myState.executeUpdate("create table if not exists money_record("
				+ "ID integer(255) not null auto_increment, "
				+ "totalAmt int(5) not null, "
				+ "paid int(5) not null, "
				+ "changeAmt int(5) not null, "
				+ "date_time timestamp not null, "
				+ "primary key(ID)"
				+ ")");
			
			myState.close();
			
			System.out.println("database Connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("connection failed.");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void closeConn() throws SQLException {
		myConn.close();
	}
	
	public boolean hasRow() throws SQLException {
		
			myState = myConn.createStatement();
			
			myRs = myState.executeQuery("SELECT COUNT(*) AS recordCount FROM money_record");
			
			myRs.next();
			
			if(myRs.getInt("recordCount") > 0 ) {
				myState.clearBatch();
				//System.out.print("diu");
				return true;
				
			}else {
				myState.clearBatch();
				//System.out.print("on9");
				return false;
			}
			
		
	}

	public String printToday(String date) throws SQLException {
		
		
		myState = myConn.createStatement();
		
		myRs = myState.executeQuery("SELECT * FROM money_record "
				+ "WHERE date_time LIKE '%"+date+"%' ");
		
		String result = "";
		while(myRs.next()) {
			result += "ID: " + myRs.getInt("ID") + "\tAmount: " + myRs.getInt("totalAmt") + 
					"\tPaid: " + myRs.getInt("paid") + "\tChange: " + myRs.getInt("changeAmt") + "\n";
		}
		myState.clearBatch();
		myRs.close();
		
		myRs = myState.executeQuery("SELECT sum(totalAmt), sum(paid), sum(changeAmt) FROM hehehaha.money_record "
				+ "WHERE date_time LIKE '%"+date+"%' ");
		
		if(myRs.next()) {
			result += "Total Amount: " + myRs.getInt("sum(totalAmt)") + 
					"\tTotal Paid: " + myRs.getInt("sum(paid)") +
					"\tTotal Change: " + myRs.getInt("sum(changeAmt)") + "\n";
		}
		
		
		return result;
	}
	
	














}
