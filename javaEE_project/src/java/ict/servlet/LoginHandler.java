/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Member;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author kaming
 */
@WebServlet(name = "LoginHandler", urlPatterns = {"/login/loginHandler"})
public class LoginHandler extends HttpServlet {

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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        String action = request.getParameter("action");

        String role = request.getParameter("role");
        String uname = request.getParameter("username");
        String upass = request.getParameter("password");
        String id = db.validateUser(role, uname, upass);
        RequestDispatcher rd;
        ArrayList<Member> member = db.getMemberList(id);
        
        HttpSession session = request.getSession(true);

        if (id.matches("")) {
            request.setAttribute("loginFail", "true");
            String login_page = role.equalsIgnoreCase("staff")
                    ? "/login/staff_login.jsp" : "/login/mem_login.jsp";

            rd = request.getRequestDispatcher(login_page);
            rd.forward(request, response);
        } else {
//            String home_page = role.equalsIgnoreCase("staff")
//                    ? "/SKY_project/staff/staff_home.jsp" :
//                    "/SKY_project/mem/mem_home.jsp";
//            session.setAttribute("loginId", id);
//            response.sendRedirect(home_page);
            String home_page = role.equalsIgnoreCase("member") ? "/SKY_project/mem/mem_home.jsp"
                    : uname.equals("admin") ? "/SKY_project/staff/admin_home.jsp" : "/SKY_project/staff/staff_home.jsp";
            session.setAttribute("loginId", id);
            if(role.equalsIgnoreCase("member")){
                session.setAttribute("loginName", member.get(0).getMemberName());
                session.setAttribute("loginPwd", member.get(0).getMemberPassword());
                session.setAttribute("loginEmail", member.get(0).getMemberEmail());
            }
            
            response.sendRedirect(home_page);
        }

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
