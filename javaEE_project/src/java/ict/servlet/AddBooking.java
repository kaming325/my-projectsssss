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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "AddBooking", urlPatterns = {"/mem/addBooking"})
public class AddBooking extends HttpServlet {
    
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        RequestDispatcher rd;
        
        if(action.equalsIgnoreCase("enterBooking")){
            ArrayList<Venue> venues = db.getAllVenue();
            ArrayList<Booking> bookings = db.getAllBooking();
            request.setAttribute("venueList", venues);
            request.setAttribute("bookingList", bookings);
            rd = getServletContext().getRequestDispatcher("/mem/bookingForm.jsp");
            rd.forward(request, response);
            
        } else if(action.equalsIgnoreCase("add")){
            String userId = (String) request.getSession().getAttribute("loginId");
            String[] times = request.getParameterValues("tsession");
            String[] vids = request.getParameterValues("venueId");
            String[] dates = request.getParameterValues("date");
            
            db.addBooking(userId, vids, times, dates);
            out.println("add Success!!<br/>");
            out.println("<a href='/SKY_project/mem/listBooking'>create guest list for your booking</a>");
            out.println("<a href='/SKY_project/mem/mem_home.jsp'>back to home page</a>");
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
