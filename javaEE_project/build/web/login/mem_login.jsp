<%-- 
    Document   : login
    Created on : 2023年3月29日, 上午10:56:00
    Author     : kaming
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <jsp:include page="/css/style.jsp" />
    </head>
    <body>
        <div class="container">
            <form class="form" action="loginHandler" method="POST">
                <h2>Member Login</h2>
                <input type="hidden" name="role" value="member" />
                <input type="text" placeholder="Username" name="username" required />
                <input type="password" placeholder="Password" name="password" required />
                <input type="submit" value="Sign In" />
                <%
                    String loginFail = (String) request.getAttribute("loginFail");
                    if (loginFail != null && loginFail.matches("true")) {
                        out.println("<div style='color:red;'");
                        out.println("<p>");
                        out.println("incorrect username or password, try again");
                        out.println("</p>");
                        out.println("</div>");
                    }
                %>
                <p>
                    Not a member? <a href="../register/mem_register.jsp">Sign Up</a>
                </p>
                <p> Staff Login: <a href="./staff_login.jsp">Click here</a>  </p>
            </form>
        </div>
    </body>
</html>