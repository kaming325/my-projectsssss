/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.bean.Booking;
import ict.bean.Venue;
import ict.db.DatabaseConnection;
import java.util.ArrayList;

/**
 *
 * @author kaming
 */
public class TestGetBookingByDate {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/venue_management";
        String username = "root";
        String password = "";
//        ArrayList<Booking> bookings = new DatabaseConnection(url, username, password).getBookingByDate("2023-04-11");
//        
//        for(Booking booking: bookings){
//            System.out.println(booking.getVenueId());
//        }
    }
}
