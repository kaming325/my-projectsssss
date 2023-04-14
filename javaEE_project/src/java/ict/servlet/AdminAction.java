/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Member;
import ict.bean.Staff;
import ict.db.DatabaseConnection;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "AdminAction", urlPatterns = {"/staff/adminAction"})
public class AdminAction extends HttpServlet{
     private String url;
    private String username;
    private String password;
    private DatabaseConnection db;
    
    @Override
    public void init() {
        this.url = "jdbc:mysql://localhost:3306/venue_management";
        this.username = "root";
        this.password = ""; 
        this.db = new DatabaseConnection(url, username, password);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        RequestDispatcher rd;
        
        if(action.equals("editStatus")){
            String id = request.getParameter("id");
            String status = request.getParameter("status");
            boolean editStatus = db.editStatus(id, status);
            if(editStatus && id.contains("M")){
                request.setAttribute("editMemberStatus", "true");
                rd = request.getRequestDispatcher("/staff/admin_home.jsp");
                rd.forward(request, response);
            }else if(!editStatus && id.contains("M")){
                request.setAttribute("editMemberStatus", "false");
                rd = request.getRequestDispatcher("/staff/admin_home.jsp");
                rd.forward(request, response);
            }
            if(editStatus && id.contains("S")){
                request.setAttribute("editStaffStatus", "true");
                rd = request.getRequestDispatcher("/staff/admin_home.jsp");
                rd.forward(request, response);
            }else if(!editStatus && id.contains("S")){
                request.setAttribute("editStaffStatus", "false");
                rd = request.getRequestDispatcher("/staff/admin_home.jsp");
                rd.forward(request, response);
            }
            //rd = this.getServletContext().getRequestDispatcher("/staff/admin_home.jsp");
            //rd = request.getRequestDispatcher("/staff/admin_home.jsp");
            //rd.forward(request, response);
        }
        
        if(action.equals("newStaff")){
            String name = request.getParameter("newStaffName");
            String pwd = request.getParameter("staffPassword");

            boolean new_staff = db.newStaff(name, pwd);
            if(new_staff){
                ArrayList<Staff> newStaff = db.getStaffList(name);
                request.setAttribute("newStaff", newStaff);
                rd = this.getServletContext().getRequestDispatcher("/staff/admin_home.jsp");
                rd.forward(request, response);
            }else{
                request.setAttribute("createFail", "true");
                String registerPage = "/staff/admin_home.jsp";
                rd = request.getRequestDispatcher(registerPage);
                rd.forward(request, response);
            }
        }
        
        if(action.equals("deleteStaff")){
            String id = request.getParameter("newStaffID");
            boolean removeStaff = db.removeStaff(id);
            if(removeStaff){
                request.setAttribute("removeID", id);
                rd = this.getServletContext().getRequestDispatcher("/staff/admin_home.jsp");
                rd.forward(request, response);
            }else{
                request.setAttribute("removeStaff", "false");
                String page = "/staff/admin_home.jsp";
                rd = request.getRequestDispatcher(page);
                rd.forward(request, response);
            }
        }
        
        if(action.equals("showMember")){
            String keyWord = request.getParameter("memberKeyWord");
            ArrayList<Member> member = db.getMemberList(keyWord);
            request.setAttribute("member", member);
            rd = this.getServletContext().getRequestDispatcher("/staff/admin_home.jsp");
            rd.forward(request, response);
        }
        
        if(action.equals("showStaff")){
            String keyWord = request.getParameter("staffKeyWord");
            ArrayList<Staff> staff = db.getStaffList(keyWord);
            request.setAttribute("staff", staff);
            rd = this.getServletContext().getRequestDispatcher("/staff/admin_home.jsp");
            rd.forward(request, response);
        }
    }
}
