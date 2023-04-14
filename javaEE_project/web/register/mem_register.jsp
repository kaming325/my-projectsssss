<%-- 
    Document   : mem_register
    Created on : 2023/4/2, 下午 06:23:10
    Author     : kenkwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Register</title>
        <jsp:include page="/css/style.jsp" />
    </head>
    <body>
        <div class="container">
            <form class="form" action="registerHandler" method="POST">
                <h2>Member Register</h2>
                <input type="hidden" name="role" value="member" />
                <input type="text" placeholder="Username" name="username" required />
                <input type="password" placeholder="Password" name="first_password" required />
                <input type="password" placeholder="Confirm Password" name="second_password" required />
                <input type="email" placeholder="Email" name="email" required />
                <input type="submit" value="Sign Up" />
                <%
                    String wrongPassword = (String) request.getAttribute("wrongPassword");
                    if (wrongPassword != null && wrongPassword.matches("true")) {
                        out.println("<br/><div class='msg' style='color:red;'>");
                        out.println("<p>");
                        out.println("First password and second password are not match.");
                        out.println("</p>");
                        out.println("</div>");
                    }
                %>

                <%
                    String registerFail = (String) request.getAttribute("registerFail");
                    if (registerFail != null && registerFail.matches("true")) {
                        out.println("<br/><div class='msg' style='color:red;'>");
                        out.println("<p>");
                        out.println("Sign up failed, please try again.");
                        out.println("</p>");
                        out.println("</div>");
                    }
                %>

                <%
                    String registerSucess = (String) request.getAttribute("registerSucess");
                    if (registerSucess != null && registerSucess.matches("true")) {
                        out.println("<br/><div class='msg' style='color:blue;'>");
                        out.println("<p>");
                        out.println("Welcome to be a member.");
                        out.println("</p>");
                        out.println("</div>");
                    }
                %>

                <%
                    String userName = (String) request.getAttribute("existingUserName");
                    if (userName != null && userName.matches("true")) {
                        out.println("<br/><div class='msg' style='color:red;'>");
                        out.println("<p>");
                        out.println("This username is already registered.");
                        out.println("</p>");
                        out.println("</div>");
                    }
                %>

                <%
                    String userEmail = (String) request.getAttribute("existingEmail");
                    if (userEmail != null && userEmail.matches("true")) {
                        out.println("<br/><div class='msg' style='color:red;'>");
                        out.println("<p>");
                        out.println("This email is already registered.");
                        out.println("</p>");
                        out.println("</div>");
                    }
                %>
                <p> <a href="../login/mem_login.jsp">Sign in instead</a>  </p>
            </form>
        </div>
    </body>
</html>
