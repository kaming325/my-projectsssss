/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.bean.Venue;
import ict.db.DatabaseConnection;
import java.util.ArrayList;

/**
 *
 * @author kaming
 */
public class TestGetVenue {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/venue_management";
        String username = "root";
        String password = "";
        ArrayList<Venue> venues = new DatabaseConnection(url, username, password).getAllVenue();
        
        for(Venue v: venues){
            System.out.println(v.getVenue_name());
        }
    }
}
