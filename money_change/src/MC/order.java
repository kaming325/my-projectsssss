package MC;

import java.sql.SQLException;

import java.sql.Statement;

public class order {
	private int totalAmt;
	private int paidAmt;
	private int change;
	private dbConn dbcon;
	private Statement command;

	public order(int totalAmt, int paidAmt, int change) {
		this.totalAmt = totalAmt;
		this.paidAmt = paidAmt;
		this.change = change;
	}
	
	public void insert2db(dbConn dbcon) {
		this.dbcon = dbcon;
		
		try {
			command = (Statement) dbcon.myConn.createStatement();
			
			if(dbcon.hasRow()) {
				command.executeUpdate("insert into money_record (totalAmt, paid, changeAmt, date_time) values "
						+ " (" + totalAmt + ", "+ paidAmt +", "+ change +", current_timestamp())");
				
			}else {
				command.executeUpdate("insert into money_record values "
						+ " (1, " + totalAmt + ", "+ paidAmt +", "+ change +", "
								+ "current_timestamp())");
			}
			
			command.clearBatch();
			command.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
