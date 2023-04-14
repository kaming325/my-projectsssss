<%-- 
    Document   : non_approveBooking_detail
    Created on : 2023年4月5日, 下午10:32:36
    Author     : ming
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.db.DatabaseConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Details</title>
    </head>
    <body>
        <jsp:useBean id="bookingBean" class="ict.bean.Booking" scope="request"/>
        <jsp:setProperty name="bookingBean" property="*"/>
        <%
            String venue_name = request.getParameter("venueName");
            DatabaseConnection db = new DatabaseConnection("jdbc:mysql://localhost:3306/venue_management", "root", "");
            ArrayList<String[]> guestList = db.getGuestListByBookingId(bookingBean.getBookingId());
        %>
        <h2>Details of BookingId: <%= bookingBean.getBookingId() %></h2>
        <p>Venue: <%= venue_name %> </p>
        <p>Booked Date: <%= bookingBean.getBkdate() %> </p>
        <p>Booked Session: <%= bookingBean.getTsession() %> </p> <br/>
        Guest List: <br/>
        <table border="1">
            <tr>
                <th>Guest Name</th>
                <th>Email Address</th>
            </tr>
            <%
                for(String[] guest: guestList){
                    out.println("<tr>");
                    out.println("<td>"+guest[0]+"</td>");
                    out.println("<td>"+guest[1]+"</td>");
                    out.println("</tr>");
                }
            %>
        </table>
    </body>
</html>
