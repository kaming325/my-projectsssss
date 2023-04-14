/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author kenkwan
 */
public class Staff implements Serializable{
    private String staffID, staffName, staffPassword, staffRole, staffStatus;
    
    public Staff(){}
    public Staff(String id, String name, String pwd, String role, String status){
        staffID = id;
        staffName = name;
        staffPassword = pwd;
        staffRole = role;
        staffStatus = status;
    }
    
    public void setStaffID(String id){ staffID = id; }
    public void setStaffName(String name){ staffName = name; }
    public void setStaffPassword(String pwd){ staffPassword = pwd; }
    public void setStaffRole(String role){ staffRole = role; }
    public void setStaffStatus(String status){ staffStatus = status; }
    
    public String getStaffID(){ return staffID; }
    public String getStaffName(){ return staffName; }
    public String getStaffPassword(){ return staffPassword; }
    public String getStaffRole(){ return staffRole; }
    public String getStaffStatus(){ return staffStatus; }
}
