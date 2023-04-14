/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.DatabaseConnection;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kenkwan
 */

@WebServlet(name = "RegisterHandler", urlPatterns = {"/register/registerHandler"})
public class RegisterHandler extends HttpServlet{
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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String role = request.getParameter("role");
        String uname = request.getParameter("username");
        String first_pwd = request.getParameter("first_password");
        String second_pwd = request.getParameter("second_password");
        String uemail = request.getParameter("email");
        boolean new_member = false;
        boolean isExistUserName = db.isExistUserName(uname);
        boolean isExistEmail = db.isExistEmail(uemail);
        RequestDispatcher rd;
        //HttpSession session = request.getSession(true);
        
        if(!first_pwd.matches(second_pwd)){
            request.setAttribute("wrongPassword", "true");
            String registerPage = "/register/mem_register.jsp";
            rd = request.getRequestDispatcher(registerPage);
            rd.forward(request, response);
        }else if(isExistUserName){
            request.setAttribute("existingUserName", "true");
            String registerPage = "/register/mem_register.jsp";
            rd = request.getRequestDispatcher(registerPage);
            rd.forward(request, response);
        }else if(isExistEmail){
            request.setAttribute("existingEmail", "true");
            String registerPage = "/register/mem_register.jsp";
            rd = request.getRequestDispatcher(registerPage);
            rd.forward(request, response);
        }else{
            new_member = db.newMember(uname, first_pwd, uemail);
        }
        
        /*
        if(first_pwd.matches(second_pwd))
            new_member = db.newMember(uname, first_pwd, uemail);
        else{
            request.setAttribute("wrongPassword", "true");
            String registerPage = "/register/mem_register.jsp";
            rd = request.getRequestDispatcher(registerPage);
            rd.forward(request, response);
        }
        */
        if(new_member){
            request.setAttribute("registerSucess", "true");
            String registerPage = "/register/mem_register.jsp";
            rd = request.getRequestDispatcher(registerPage);
            rd.forward(request, response);
        }else{
            request.setAttribute("registerFail", "true");
            String registerPage = "/register/mem_register.jsp";
            rd = request.getRequestDispatcher(registerPage);
            rd.forward(request, response);
        }
    }
}
