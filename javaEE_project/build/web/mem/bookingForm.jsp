<%-- 
    Document   : bookingForm
    Created on : Mar 31, 2023, 2:51:36 PM
    Author     : User
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="ict.bean.Venue"%>
<%@page import="ict.bean.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Form</title>
        <script>
            var n = 0;
            var sessionJson;
            var unavailableSessionJson;
            const arrayRange = (start, stop, step) =>
                Array.from(
                        {length: (stop - start) / step + 1},
                        (value, index) => start + index * step
                );

            function init() {
                var column = document.getElementById("column");
                sessionJson = JSON.parse(document.getElementById("times").innerHTML);
                unavailableSessionJson = JSON.parse(document.getElementById("existingBooking").innerHTML);
                column.style.visibility = 'hidden';
                addColumn();

            }
            function addColumn() {
                if (n < 3) {
                    var form = document.getElementById("addBookingForm");
                    var column = document.getElementById("column");
                    var column2 = column.cloneNode(true);
                    column2.style.visibility = 'visible';
                    //alert(form.innerHTML);
                    form.appendChild(column2);
                    n += 1;
                } else {
                    alert('You shall not make more than 3 bookings at a same request.');
                }

            }

            function updateHr(durationInput) {
                var session = durationInput.parentElement.lastElementChild;
                var venue = durationInput.parentElement.querySelector('.venue');
                var date = durationInput.parentElement.querySelector('.date').value;
                var duration = parseInt(durationInput.value);

                while (session.firstChild) {
                    session.removeChild(session.firstChild);
                }
                var open = sessionJson[venue.selectedIndex]['open'];
                var close = sessionJson[venue.selectedIndex]['close'];
                var list = [];
                for (var i = open; i <= close; i++) {
                    list.push(i);
                }
                ulist = getUnavailable(date);

                aloop: 
                for (var i = 0; i < list.length; i++) {
                    if ((list[i] + duration) > close)
                        break;
                    const period = arrayRange(list[i], (list[i] + duration), 1);
                    
                    for (const usession of ulist) {
                        const myArray = usession.split("-");
                        var start = parseInt(myArray[0]);
                        var end = parseInt(myArray[1]);
                        const uperiod = arrayRange(start, end, 1);
                        console.log(uperiod);
                        if (period.some(e => uperiod.includes(e) )){
                            console.log("diu");
                            continue aloop;
                        }
                    }
                    
                    console.log(period);
                    var opt = document.createElement('option');
                    opt.value = list[i] + '-' + (list[i] + duration);
                    opt.innerHTML = list[i] + ' - ' + (list[i] + duration);
                    session.appendChild(opt);
                }

                session.disabled = '';
            }

            function getUnavailable(fdate) {
                var unavailableList = [];
                const jsSize = (Object.keys(unavailableSessionJson).length);
                for (var index = 0; index < jsSize; index++) {
                    const obj = unavailableSessionJson[index];
                    if (obj.date == fdate) {
                        unavailableList.push(obj['tsession'])
                    }
                }
                console.log('unavailableList: ' + unavailableList);
                return unavailableList;
            }

            function enableDU(dateInput) {
                dateInput.parentElement.querySelector('.duration').disabled = '';
                dateInput.parentElement.querySelector('.duration').selectedIndex = '0';
                dateInput.parentElement.lastElementChild.disabled = 'disabled';
                dateInput.parentElement.lastElementChild.value = '';
            }
            function disableDuSe(venueInput) {
                venueInput.parentElement.querySelector('.duration').disabled = '';
                venueInput.parentElement.querySelector('.duration').selectedIndex = '0';
                venueInput.parentElement.lastElementChild.disabled = 'disabled';
                venueInput.parentElement.lastElementChild.value = '';
            }
        </script>
    </head>
    <body onload="init()">
        <%
            ArrayList<Venue> venues = (ArrayList) request.getAttribute("venueList");
            ArrayList<Booking> bookings = (ArrayList) request.getAttribute("bookingList");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);
            String date30 = dtf.format(now.plusDays(30));
        %>
        <div id="column">
            Venue: 
            <select class="venue" name="venueId" onchange="disableDuSe(this)" >
                <%
                    for (Venue v : venues) {
                        out.println("<option value='" + v.getVenueId() + "'>");
                        out.println(v.getVenue_name());
                        out.println("</option>");
                    }
                %>
            </select>

            Date:
            <input class="date" name="date" type="date" name="bkdate" min="<%= date%>" max="<%= date30%>" onchange="enableDU(this)"  />

            Duration:
            <select class="duration" name="duration" disabled="disabled" onchange="updateHr(this)">
                <option value=''></option>
                <option value='1'>one hour</option>
                <option value='2'>two hours</option>
                <option value='3'>three hours</option>
            </select>

            Session: 
            <select name="tsession" disabled="disabled">
            </select>
        </div> <!-- end of column div -->

        <h1>add Booking Form</h1>
        <form id="addBookingForm" action="addBooking" method="GET">
            <input type="button" onclick="addColumn()" value="+"/>
            <input type="submit" value="Submit" />
            <input type="hidden" value="add" name="action"/>
        </form>

        <%
            out.println("<div id='times' style='visibility: hidden;'>");
            out.print("{");
            for (int x = 0; x < venues.size(); x++) {   // venue already got
                out.println("\"" + x + "\":");
                out.println("{ \"open\":");
                out.println(venues.get(x).getOpen_hr() + ", ");
                out.println(" \"close\":");
                out.println(venues.get(x).getClose_hr());
                out.println((x == venues.size() - 1) ? "}" : "},");
            }
            out.print("}");
            out.println("</div>");
        %>

        <%
            out.println("<div id='existingBooking' style='visibility: hidden;'>"); //
            out.print("{");
            for (int x = 0; x < bookings.size(); x++) {
                out.println("\"" + x + "\":");
                out.println("{ \"date\":");
                out.println("\"" + bookings.get(x).getBkdate() + "\", ");
                out.println(" \"tsession\":");
                out.println("\"" + bookings.get(x).getTsession() + "\"");
                out.println((x == bookings.size() - 1) ? "}" : "},");
            }
            out.print("}");
            out.println("</div>");
        %>
    </body>
</html>
