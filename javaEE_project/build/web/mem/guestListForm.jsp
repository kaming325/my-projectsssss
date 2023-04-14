<%-- 
    Document   : guestListForm
    Created on : 2023年4月5日, 下午03:24:01
    Author     : ming
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guest List Form</title>
        <script>
            function addColumn() {
                var form = document.getElementById("guestListFrom");
                var column = document.getElementById("column");
                var column2 = column.cloneNode(true);
                column2.style.visibility = 'visible';
                //alert(form.innerHTML);
                form.appendChild(column2);

            }
            function delColumn() {
                var form = document.getElementById("guestListFrom");
                form.removeChild(form.lastChild);

            }

            function init() {
                var column = document.getElementById("column");
                column.style.visibility = 'hidden';
                addColumn();
            }

        </script>
    </head>
    <body onload="init()">
        <%
            String bookingId = request.getParameter("bookingId");
        %>
        <h1>add Booking Form</h1>

        <div id="column">
            Name: <input type="text" name="guestName"/> Email address: <input type="email" name="guestEmail"/>
        </div>

        <form id="guestListFrom" action="addGuest2Booking" method="GET">
            <input type="button" onclick="addColumn()" value="+"/>
            <input type="button" onclick="delColumn()" value="-"/>
            <input type="submit" value="Submit" />
            <input type="hidden" value="addGuest" name="action"/>
            <input type="hidden" value="<%= bookingId%>" name="bookingId"/>
        </form>

    </body>
</html>
