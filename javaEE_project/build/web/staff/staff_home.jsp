<%-- 
    Document   : staff_home
    Created on : 2023年3月29日, 上午08:26:24
    Author     : kaming
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World! staff <%= session.getAttribute("loginId") %></h1>
        <form action="listBooking" method="GET">
            <input type="hidden" value="staff" name="role"/>
            <input type="hidden" value="listNonApprove" name="action"/>
            <input type="submit" value="See Booking" />
        </form>
    </body>
</html>
