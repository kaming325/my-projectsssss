<%-- 
    Document   : mem_home
    Created on : 2023年3月29日, 上午08:25:52
    Author     : kaming
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                display: none;
                overflow: hidden;
                background-color: #f1f1f1;
            }
        </style>

    </head>
    <body>
        <h1>Hello World! member <%= session.getAttribute("loginId")%></h1>
        <button type="button" class="collapsible">Member information</button>

        <div class="content">
            <br />
            <form action="memberUpdateInfo" method="post">
                <%
                    String pwdMatch = (String) request.getAttribute("pwdMatch");
                    String pwdCorrect = (String) request.getAttribute("pwdCorrect");
                    if (pwdCorrect != null && pwdCorrect.matches("false")) {
                        out.println("<div style='color:red;'>");
                        out.println("The old password is not correct.");
                        out.println("</div><br />");
                    }
                    if (pwdMatch != null && pwdMatch.matches("false")) {
                        out.println("<div style='color:red;'>");
                        out.println("The new password are not match.");
                        out.println("</div><br />");
                    }

                    String isUpdated = (String) request.getAttribute("isUpdated");
                    if (isUpdated != null && isUpdated.matches("true")) {
                        out.println("<div style='color:blue;'>");
                        out.println("The member info is updated.");
                        out.println("</div><br />");
                        // If member information is  updated, get request attribute from MemberUpdateInfo
                        out.println("<input type='hidden' name='memberID_fromDB' value='" + request.getAttribute("loginId") + "'>");
                        out.println("<input type='hidden' name='memberName_fromDB' value='" + request.getAttribute("loginName") + "'>");
                        out.println("<input type='hidden' name='oldPwd_fromDB' value='" + request.getAttribute("loginPwd") + "'>");
                        out.println("<input type='hidden' name='oldEmail_fromDB' value='" + request.getAttribute("loginEmail") + "'>");

                        out.println("<table>");
                        out.println("<tr> <td> Member ID </td> <td>" + request.getAttribute("loginId") + "</td> </tr>");
                        out.println("<tr> <td> Member Name </td> <td>" + request.getAttribute("loginName") + "</td> </tr>");
                        out.println("<tr> <td colspan='2' style='text-align: center; color: green'>Update following information (Optional)</td> </tr>");
                        out.println("<tr> <td> Member Old Password </td> <td> <input type='password' name='oldPwd'> </td> </tr>");
                        out.println("<tr> <td> Member New Password </td> <td> <input type='password' name='newPwd_1'> </td> </tr>");
                        out.println("<tr> <td> Repeat New Password </td> <td> <input type='password' name='newPwd_2'> </td> </tr>");
                        out.println("<tr> <td> Member Email </td> <td> <input type='email' name='memberEmail' value='" + request.getAttribute("loginEmail") + "' required> </td> </tr>");
                        out.println("<tr> <td colspan='2'> <input type='submit' value='Update'> </td> </tr>");
                        out.println("</table>");

                    } else if (isUpdated != null && isUpdated.matches("false")) {
                        out.println("<div style='color:red;'>");
                        out.println("The member info is not updated yet.");
                        out.println("</div><br />");
                    } else {
                        // If member information is not updated, get session attribute from login handler
                        out.println("<input type='hidden' name='memberID_fromDB' value='" + session.getAttribute("loginId") + "'>");
                        out.println("<input type='hidden' name='memberName_fromDB' value='" + session.getAttribute("loginName") + "'>");
                        out.println("<input type='hidden' name='oldPwd_fromDB' value='" + session.getAttribute("loginPwd") + "'>");
                        out.println("<input type='hidden' name='oldEmail_fromDB' value='" + session.getAttribute("loginEmail") + "'>");

                        out.println("<table>");
                        out.println("<tr> <td> Member ID </td> <td>" + session.getAttribute("loginId") + "</td> </tr>");
                        out.println("<tr> <td> Member Name </td> <td>" + session.getAttribute("loginName") + "</td> </tr>");
                        out.println("<tr> <td colspan='2' style='text-align: center; color: green'>Update following information (Optional)</td> </tr>");
                        out.println("<tr> <td> Member Old Password </td> <td> <input type='password' name='oldPwd'> </td> </tr>");
                        out.println("<tr> <td> Member New Password </td> <td> <input type='password' name='newPwd_1'> </td> </tr>");
                        out.println("<tr> <td> Repeat New Password </td> <td> <input type='password' name='newPwd_2'> </td> </tr>");
                        out.println("<tr> <td> Member Email </td> <td> <input type='email' name='memberEmail' value='" + session.getAttribute("loginEmail") + "' required> </td> </tr>");
                        out.println("<tr> <td colspan='2'> <input type='submit' value='Update'> </td> </tr>");
                        out.println("</table>");
                    }

                %>

            </form>
            <br />
        </div>
        <form action="listBooking" method="GET">
            <input type="hidden" value="member" name="role"/>
            <input class="collapsible" type="submit" value="See Booking" />
        </form>
        <form action="addBooking" method="GET">
            <input type="hidden" value="enterBooking" name="action"/>
            <input class="collapsible" type="submit" value="Add Booking" />
        </form>
        <script>
            var coll = document.getElementsByClassName("collapsible");
            for (var i = 0; i < coll.length; i++) {
                coll[i].addEventListener("click", function () {
                    this.classList.toggle("active");
                    var content = this.nextElementSibling;
                    if (content.style.display === "block")
                        content.style.display = "none";
                    else
                        content.style.display = "block";
                });
            }
        </script>
    </body>
</html>
