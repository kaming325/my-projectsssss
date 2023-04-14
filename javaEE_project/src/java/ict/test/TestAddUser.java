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
public class TestAddUser {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/venue_management";
        String username = "root";
        String password = "";
        new DatabaseConnection(url, username, password).addUser("12345", "senior manager", "1", "210211001@gmail.com");
    }
}
