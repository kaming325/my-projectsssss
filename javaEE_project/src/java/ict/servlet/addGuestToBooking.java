/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ming
 */
@WebServlet(name = "addGuestToBooking", urlPatterns = {"/mem/addGuest2Booking"})
public class addGuestToBooking extends HttpServlet {
    
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
            String action = request.getParameter("action");
            String bookingId = request.getParameter("bookingId");
            String[] guestName = request.getParameterValues("guestName");
            String[] guestEmail = request.getParameterValues("guestEmail");
            out.println(action + "<br/>");
            out.println(bookingId + "<br/>");
            for(int i=0; i<guestName.length; i++){
                out.println("Guest Name: " + guestName[i] + " ");
                out.println("Guest email: " + guestEmail[i] +"<br/>");
            }
            if(db.addGuestToBooking(guestName, guestEmail, bookingId)){
                out.println("add Success!!<br/>");
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
