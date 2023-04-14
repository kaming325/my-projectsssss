/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Venue implements Serializable {
    String venueId, venue_pic, venue_name, venue_type, imgPath, location, descri;
    int capacity, open_hr, close_hr;
    double hrfee;
    String v_status;
    
    public Venue(){}
    
    
    public void setVenueId(String venueId){
        this.venueId = venueId;
    }
    public String getVenueId(){
        return this.venueId;
    }
    
    public void setV_status(String v_status){
        this.v_status = v_status;
    }
    public String getV_status(){
        return this.v_status;
    }
    
    public void setVenue_name(String venue_name){
        this.venue_name = venue_name;
    }
    public String getVenue_name(){
        return this.venue_name;
    }
    
    public void setVenue_pic(String venue_pic){
        this.venue_pic = venue_pic;
    }
    public String getVenue_pic(){
        return this.venue_pic;
    }
    
    public void setVenue_type(String venue_type){
        this.venue_type = venue_type;
    }
    public String getVenue_type(){
        return this.venue_type;
    }
    
    public void setImgPath(String imgPath){
        this.imgPath = imgPath;
    }
    public String getImgPath(){
        return this.imgPath;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
    
    public void setDescri(String descri){
        this.descri = descri;
    }
    public String getDescri(){
        return this.descri;
    }
    
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public int getCapacity(){
        return this.capacity;
    }
    
    public void setHrfee(double hrfee){
        this.hrfee = hrfee;
    }
    public double setHrfee(){
        return this.hrfee;
    }
    
    public void setOpen_hr(int open_hr){
        this.open_hr = open_hr;
    }
    public int getOpen_hr(){
        return this.open_hr;
    }
    
    public void setClose_hr(int close_hr){
        this.close_hr = close_hr;
    }
    public int getClose_hr(){
        return this.close_hr;
    }
    
}
