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
public class Booking implements Serializable {
    private String bookingId, venueId, memberId, bkdate, tsession, booking_status;
    
    public Booking(){}
    
    public void setBookingId(String bookingId){
        this.bookingId = bookingId;
    }
    public String getBookingId(){
        return this.bookingId;
    }
    
    public void setVenueId(String venueId){
        this.venueId = venueId;
    }
    public String getVenueId(){
        return this.venueId;
    }
    
    public void setMemberId(String memberId){
        this.memberId = memberId;
    }
    public String getMemberId(){
        return this.memberId;
    }
    
    public void setBkdate(String bkdate){
        this.bkdate = bkdate;
    }
    public String getBkdate(){
        return this.bkdate;
    }
    
    public void setTsession(String tsession){
        this.tsession = tsession;
    }
    public String getTsession(){
        return this.tsession;
    }
    
    public void setBooking_status(String booking_status){
        this.booking_status = booking_status;
    }
    public String getBooking_status(){
        return this.booking_status;
    }
    
}
