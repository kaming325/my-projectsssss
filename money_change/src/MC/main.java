package MC;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;

public class main {
	private static Scanner input = new Scanner(System.in);
	private static dbConn cn;

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		
		System.out.println("It is a money change program");
		
		cn = new dbConn();
		cn.setUp();
		
		
		page1();
		
	}
	
	private static void page1() throws SQLException {
		
		try {
			int selection;
			System.out.println("------------------------------");
			System.out.println("Home Page");
			
			do {
				System.out.println("what do you want to do? ");
			System.out.println("New order - 0; Get today payment record - 1; "
						+ "Close - 2");
				System.out.print("Selection: ");
				selection = input.nextInt();
				
				if(selection == 0) {
					//System.out.println("diu");
					newOrder();
				}
				
				else if(selection == 1) {
					//System.out.println("on9");
					getRecord();
					page1();
				}
				
				else if(selection == 2) {
					//System.out.println("on9");
					cn.closeConn();
					System.out.print("bye");
					System.exit(0);
					
				}
				
				else {
					System.out.println("Invalid number");
				}
				
			}while(selection != 0 && selection != 1 && selection != 2);
			
		}catch (InputMismatchException e) {
			System.out.println("Wrong input! Enter again please");
			input.next();
			page1();
		}
	}

	private static void newOrder() throws SQLException {
		try {
			System.out.println("------------------------------");
			System.out.println("New order");
			System.out.print("Total amount: ");
			int totalAmt = input.nextInt();
			while(totalAmt<0) {
				System.out.println("Invalid amount!");
				System.out.print("Total amount: ");
				totalAmt = input.nextInt();
			}
			
			
			System.out.print("Paid amount: ");
			int paidAmt = input.nextInt();
			
			while(paidAmt <totalAmt) {
				System.out.println("Not Enough!");
				System.out.print("Paid amount: ");
				paidAmt = input.nextInt();
			}
			
			
			int change = paidAmt - totalAmt;
			System.out.println("Payment success!");
			System.out.println("Change: " + change);
			
			
			
			System.out.println("Save and next order - 0; Discard and next order - 1; "
					+ "back to home page - 2");
			
			int selection;
			do {
				System.out.print("Selection: ");
				selection = input.nextInt();
				
				//save and insert
				if(selection == 0) {
					//System.out.println("diu");
					order order = new order(totalAmt, paidAmt, change);
					order.insert2db(cn);
					newOrder();
				}
				
				else if(selection == 1) {
					//System.out.println("on9");
					newOrder();
				}
				
				else if(selection == 2) {
					//System.out.println("on9");
					page1();
				}
				
				else {
					System.out.println("Invalid number");
				}
			}while(selection != 0 && selection != 1 && selection != 2);
			
			
			
			
		}catch (InputMismatchException e) {
			System.out.println("Wrong input! Enter again please");
			input.next();
			newOrder();
		}
		
	}
	
	private static void getRecord() throws SQLException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = new Date();
		String formattedDate = formatter.format(date);
		
		System.out.print(cn.printToday(formattedDate));
		
	}
}
