/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Member;
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
@WebServlet(name = "MemberUpdateInfo", urlPatterns = {"/mem/memberUpdateInfo"})
public class MemberUpdateInfo extends HttpServlet{
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("memberID_fromDB");
        String name = request.getParameter("memberName_fromDB");
        String pwd = request.getParameter("oldPwd_fromDB");
        String old_pwd = request.getParameter("oldPwd");
        String new_pwd_1 = request.getParameter("newPwd_1");
        String new_pwd_2 = request.getParameter("newPwd_2");
        String new_email = request.getParameter("memberEmail");
        String old_email = request.getParameter("oldEmail_fromDB");
        RequestDispatcher rd;
        boolean isUpdated = false;
        Member member = new Member();
        
        if("".equals(new_pwd_1) && "".equals(new_pwd_2)){
            member = new Member(id, name, pwd, new_email);
            isUpdated = db.memberInfoUpdate(member);
        }else{
            if(!new_pwd_2.equals(new_pwd_1)){
                request.setAttribute("pwdMatch", "false");
                rd = request.getRequestDispatcher("/mem/mem_home.jsp");
                rd.forward(request, response);
            }
            if(!old_pwd.equals(pwd)){
                request.setAttribute("pwdCorrect", "false");
                rd = request.getRequestDispatcher("/mem/mem_home.jsp");
                rd.forward(request, response);
            }
            //member = new Member(id, name, new_pwd_1, new_email);
            //isUpdated = db.memberInfoUpdate(member);
            if(new_pwd_2.equals(new_pwd_1) && old_pwd.equals(pwd)){
                member = new Member(id, name, new_pwd_1, new_email);
                isUpdated = db.memberInfoUpdate(member);
            }
        }
        
        if(isUpdated){
            request.setAttribute("loginId", member.getMemberID());
            request.setAttribute("loginName", member.getMemberName());
            request.setAttribute("loginPwd", member.getMemberPassword());
            request.setAttribute("loginEmail", member.getMemberEmail());
            request.setAttribute("isUpdated", "true");
        }else{
            request.setAttribute("loginId", id);
            request.setAttribute("loginName", name);
            request.setAttribute("loginPwd", pwd);
            request.setAttribute("loginEmail", old_email);
            request.setAttribute("isUpdated", "false");
        }
        rd = request.getRequestDispatcher("/mem/mem_home.jsp");
        rd.forward(request, response); 
    }
}
