/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import ict.bean.Venue;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author ming
 */
public class VenueTag extends SimpleTagSupport {
    private ArrayList<Venue> venues;
    
    public void setVenues(ArrayList venues){
        this.venues = venues;
    }
    
    @Override
    public void doTag(){
        try{
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            for(Venue v: venues){
//                    out.print("<ul>");
//                    out.print("<li>");out.print("Custid: " + customer.getCustid());out.print("</li>");
//                    out.print("<li>");out.print("Name: " + customer.getName());out.print("</li>");
//                    out.print("<li>");out.print("Tel: " + customer.getTel());out.print("</li>");
//                    out.print("<li>");out.print("Age: " + customer.getAge());out.print("</li>");
//                    out.print("</ul>");
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
