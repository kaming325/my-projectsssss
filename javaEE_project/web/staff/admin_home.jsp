<%-- 
    Document   : admin_home
    Created on : 2023/4/4, 下午 07:57:01
    Author     : kenkwan
--%>
<%@page import="ict.db.DatabaseConnection"%>
<%@page import="ict.bean.*" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <style>
            .collapsible {
                background-color: #777;
                color: white;
                cursor: pointer;
                padding: 18px;
                width: 100%;
                border: none;
                text-align: left;
                outline: none;
                font-size: 15px;
            }
            .active, .collapsible:hover {
                background-color: #555;
            }
            .content {
                padding: 0 18px;
                display: block;
                overflow: hidden;
                background-color: #f1f1f1;
            }
        </style>
    </head>
    <body>
        <h1>Admin Page</h1>
        <hr />
        
        <button type="button" class="collapsible">Create Staff Account</button>
        <div class="content">
            <br />
            <form action="adminAction" method="post"> <!-- createStaffHandler -->
                <input type="hidden" name="action" value="newStaff">
                <table>
                    <tr>
                        <td>Staff Name:</td>
                        <td><input type="text" name="newStaffName"></td>
                    </tr>
                    <tr>
                        <td>Staff Password:</td>
                        <td><input type="text" name="staffPassword"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Create"></td>
                    </tr>
                </table>
            </form>
            <%
                    ArrayList<Staff> newStaff = (ArrayList<Staff>) request.getAttribute("newStaff");
                    if(newStaff != null && newStaff.size() > 0){
                        out.println("<br/><div style='color:blue;'>");
                        out.println("Create a new staff account is created. Details as follow:");
                        out.println("</div><br />");
                        out.println("<table border='1'> <tr> <th>Staff ID</th> <th>Staff Name</th> <th>Staff Password</th> <th>Staff Role</th> <th>Staff Status</th> <th>Action</th> </tr>");
                        for(int i=0; i<newStaff.size(); i++){
                            Staff s = newStaff.get(i);
                            out.println("<tr>");
                            out.println("<td>" + s.getStaffID() + "</td>");
                            out.println("<td>" + s.getStaffName() + "</td>");
                            out.println("<td>" + s.getStaffPassword() + "</td>");
                            out.println("<td>" + s.getStaffRole() + "</td>");
                            out.println("<td>" + s.getStaffStatus() + "</td>");
                            out.println("<td><form action='adminAction' method='post'> <input type='hidden' value='" + s.getStaffID() + "' name='newStaffID'> <input type='hidden' name='action' value='deleteStaff'> <input type='submit' value='Cancel'> </form> </td>"); //<!-- deleteStaffHandler -->
                            out.println("</tr>");
                        }
                        out.println("</table>");
                    }
                    String createFail = (String) request.getAttribute("createFail");
                    if(createFail != null && createFail.matches("true")){
                        out.println("<br/><div style='color:red;'>");
                        out.println("Create a new staff is failed.");
                        out.println("</div>");
                    }
                    String removeID = (String) request.getAttribute("removeID");
                    if(removeID != null) out.println("<br/><div style='color:blue;'>The new staff: " + removeID + " is removed.</div>");
                    String removeStaff = (String) request.getAttribute("removeStaff");
                    if(removeStaff != null && removeStaff.matches("false")) out.println("<br/><div style='color:red;'>The new staff does not removed.</div>");
                %>
            <br />
        </div> 
        <hr />
        
        <button type="button" class="collapsible">Find Member</button>
        <div class="content">
            <br />
            <form action="adminAction" method="post"> <!-- showMember -->
                <input type='hidden' name='action' value='showMember'>
                <input type="text" name="memberKeyWord" style="width: 200px;" placeholder="Member ID/Name/Email or blank">
                <input type="submit" value="List member(s)" />
            </form>
            <br />
            <%
                ArrayList<Member> member = (ArrayList<Member>) request.getAttribute("member");
                if(member != null && member.size() > 0){
                    out.println("<table border='1'> <tr> <th>Member ID</th> <th>Member Name</th> <th>Member Password</th> <th>Member Email</th> <th>Member Status</th> <th>Action</th> </tr>");
                    for(int i=0; i<member.size(); i++){
                        Member mem = member.get(i); 
                        out.println("<tr> <form action='adminAction' method='post'> <input type='hidden' value='" + mem.getMemberID() + "' name='id'> <input type='hidden' name='action' value='editStatus'>"); //<!-- editStatus -->
                        out.println("<td>" + mem.getMemberID() + "</td>");
                        out.println("<td>" + mem.getMemberName() + "</td>");
                        out.println("<td>" + mem.getMemberPassword() + "</td>");
                        out.println("<td>" + mem.getMemberEmail() + "</td>");
                        out.println("<td> <input type='text' value='" + mem.getMemberStatus() + "' name='status'></td>");
                        out.println("<td> <input type='submit' value='Edit'> </td>");
                        out.println("</form> </tr>");
                    }
                    out.println("</table><br />");
                }else if(member != null && member.size() == 0){
                    out.println("<div style='color:red'>No result.</div>");
                }
                String editMemberStatus = (String) request.getAttribute("editMemberStatus");
                if(editMemberStatus != null && editMemberStatus.matches("true")){
                    out.println("<br/><div style='color:blue;'>");
                    out.println("Status update successful.");
                    out.println("</div>");
                }else if(editMemberStatus != null && editMemberStatus.matches("false")){
                    out.println("<br/><div style='color:red;'>");
                    out.println("Status update failed. Make sure status input '0' or '1'");
                    out.println("</div>");
                }
            %>
            <br />
        </div>
        
        <hr />
        
        <button type="button" class="collapsible">Find Staff</button>
        <div class="content">
            <br />
            <form action="adminAction" method="post"> <!-- showStaff -->
                <input type='hidden' name='action' value='showStaff'>
                <input type="text" name="staffKeyWord" style="width: 200px;" placeholder="Staff ID/Name/Role or blank">
                <input type="submit" value="List staff" />
            </form>
            <br />
            <%
                ArrayList<Staff> staff = (ArrayList<Staff>) request.getAttribute("staff");
                if(staff != null && staff.size() > 0){
                    out.println("<table border='1'> <tr> <th>Staff ID</th> <th>Staff Name</th> <th>Staff Password</th> <th>Staff Role</th> <th>Staff Status</th> <th>Action</th> </tr>");
                    for(int i=0; i<staff.size(); i++){
                        Staff s = staff.get(i);
                        out.println("<tr> <form action='adminAction' method='post'> <input type='hidden' value='" + s.getStaffID() + "' name='id'> <input type='hidden' name='action' value='editStatus'>");
                        out.println("<td>" + s.getStaffID() + "</td>");
                        out.println("<td>" + s.getStaffName() + "</td>");
                        out.println("<td>" + s.getStaffPassword() + "</td>");
                        out.println("<td>" + s.getStaffRole() + "</td>");
                        out.println("<td> <input type='text' value='" + s.getStaffStatus() + "' name='status'></td>");
                        out.println("<td> <input type='submit' value='Edit'> </td>");
                        out.println("</form> </tr>");
                    }
                    out.println("</table><br />");
                }else if(staff != null && staff.size() == 0){
                    out.println("<div style='color:red'>No result.</div>");
                }
                String editStaffStatus = (String) request.getAttribute("editStaffStatus");
                if(editStaffStatus != null && editStaffStatus.matches("true")){
                    out.println("<br/><div style='color:blue;'>");
                    out.println("Status update successful.");
                    out.println("</div>");
                }else if(editStaffStatus != null && editStaffStatus.matches("false")){
                    out.println("<br/><div style='color:red;'>");
                    out.println("Status update failed. Make sure status input '0' or '1'");
                    out.println("</div>");
                }
            %>
            <br />
        </div>
        
        <br />
        <a href="../login/staff_login.jsp"><button>Logout</button></a>
        
        <script>
            var coll = document.getElementsByClassName("collapsible");
            for (var i = 0; i < coll.length; i++) {
                coll[i].addEventListener("click", function() {
                    this.classList.toggle("active");
                    var content = this.nextElementSibling;
                    if(content.style.display === "none")
                        content.style.display = "block";
                    else
                        content.style.display = "none";
                });
            }
        </script>
    </body>
</html>
