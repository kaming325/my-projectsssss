/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.DatabaseConnection;

/**
 *
 * @author kaming
 */
public class TestAddBooking {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/venue_management";
        String username = "root";
        String password = "";
        //new DatabaseConnection(url, username, password).addBooking("M0001", new String[]{"V000001", "V000002"}, new String[]{});
    }
}
