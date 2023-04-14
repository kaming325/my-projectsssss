/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Booking;
import ict.bean.Venue;
import ict.db.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "ListBooking", urlPatterns = {"/mem/listBooking", "/staff/listBooking"})
public class ListBooking extends HttpServlet {

    private String url;
    private String username;
    private String password;
    private DatabaseConnection db;

    @Override
    public void init() {
        this.url = "jdbc:mysql://localhost:3306/venue_management";//this.getServletContext().getInitParameter("dbUrl")
        this.username = "root";;//this.getServletContext().getInitParameter("dbUser")
        this.password = ""; //this.getServletContext().getInitParameter("dbPassword");
        this.db = new DatabaseConnection(url, username, password);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */

        String userId = (String) request.getSession().getAttribute("loginId");
        String role = request.getParameter("role");
        String action = request.getParameter("action");
        if (role.equalsIgnoreCase("member")) {
            ArrayList<Booking> bookings = db.getBookingsById(userId);
            ArrayList<Venue> venues = db.getAllVenue();

            out.println("<h2> Your bookings </h2>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th> " + "Venue" + " </th>");
            out.println("<th> " + "Booked Date" + " </th>");
            out.println("<th> " + "Booked Session" + " </th>");
            out.println("</tr>");
            for (Booking b : bookings) {
                String venue_name = "";
                for (Venue v : venues) {
                    if (v.getVenueId().matches(b.getVenueId())) {
                        venue_name = v.getVenue_name();
                    }
                }
                out.println("<tr>");
                out.println("<td> " + venue_name + " </td>");
                out.println("<td> " + b.getBkdate() + " </td>");
                out.println("<td> " + b.getTsession() + " </td>");
                if (b.getBooking_status().matches("0")) {
                    out.println("<td> " + "<a  href='/SKY_project/mem/guestListForm.jsp?bookingId=" + b.getBookingId() + "' >Create Guest List</a>" + " </td>");
                } else {
                    out.println("<td>Guest List Added</td>");
                }
                out.println("<td> " + "<a href='/SKY_project/mem/deleteBooking?bookingId=" + b.getBookingId() + "' "
                        + "onclick='return confirm(\"Are you sure?\")'>Cancel Booking</a>" + " </td>");
                out.println("</tr>");
            }
            out.println("</table><br/>");
            out.println("<a href='/SKY_project/mem/mem_home.jsp'>back to home page</a>");

        } else if (role.equalsIgnoreCase("staff") && action.equalsIgnoreCase("listNonApprove") ) {
            ArrayList<Booking> all_bookings_inCharged_by_this_staff = new ArrayList();
            ArrayList<Venue> all_venues_inCharged_by_this_staff = db.getAllVenue();
            ArrayList<Venue> venues_notInCharged_by_this_staff = new ArrayList();
            for (Venue v : all_venues_inCharged_by_this_staff) {
                if (!v.getVenue_pic().matches(userId)) {
                    venues_notInCharged_by_this_staff.add(v);
                }
            }
            all_venues_inCharged_by_this_staff.removeAll(venues_notInCharged_by_this_staff);

            for (Venue v : all_venues_inCharged_by_this_staff) {
                ArrayList<Booking> bookings = db.getBookingsByVenueId(v.getVenueId());
                all_bookings_inCharged_by_this_staff.addAll(bookings);
            }

            out.println("<h2> Non-Approved Bookings  </h2>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th> " + "Venue" + " </th>");
            out.println("<th> " + "Booked Date" + " </th>");
            out.println("<th> " + "Booked Session" + " </th>");
            out.println("</tr>");
            for (Booking b : all_bookings_inCharged_by_this_staff) {
                if (b.getBooking_status().matches("1")) {
                    String venue_name = "";
                    for (Venue v : all_venues_inCharged_by_this_staff) {
                        if (v.getVenueId().matches(b.getVenueId())) {
                            venue_name = v.getVenue_name();
                        }
                    }
                    out.println("<tr>");
                    out.println("<td> " + venue_name + " </td>");
                    out.println("<td> " + b.getBkdate() + " </td>");
                    out.println("<td> " + b.getTsession() + " </td>");
                    out.println("<td> " + "<a href='/SKY_project/staff/non_approveBooking_detail.jsp?"
                            + "bookingId="+b.getBookingId()+"&"
                            + "venueId="+b.getVenueId()+"&"
                            + "memberId="+b.getMemberId()+"&"
                            + "bkdate="+b.getBkdate()+"&"
                            + "tsession="+b.getTsession()+"&"
                            + "booking_status="+b.getBooking_status()+"&"
                            + "venueName="+venue_name+"'> " 
                            + "show details</a></td>");
                    out.println("</tr>");

                }
            }
            out.println("</table>");
            out.println("<a href='/SKY_project/staff/staff_home.jsp'>back to home page</a>");
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
