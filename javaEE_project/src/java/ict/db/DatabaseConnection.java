/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.Booking;
import ict.bean.Venue;
import ict.bean.Staff;
import ict.bean.Member;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kaming
 */
public class DatabaseConnection {

    private String url = "";
    private String username = "";
    private String password = "";

    public DatabaseConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(url, username, password);
    }

    public String validateUser(String role, String username, String password) {
        String userId = "";
        Connection conn = null;
        PreparedStatement pStatement = null;
        String preQueryStatement = "";
        if (role.equalsIgnoreCase("member")) {
            preQueryStatement = "SELECT * from mem_login WHERE uname=? and upass=?";
        }
        if (role.equalsIgnoreCase("staff")) {
            preQueryStatement = "SELECT * from staff_login WHERE uname=? and upass=?";
        }

        try {
            conn = getConnection();
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, username);
            pStatement.setString(2, password);

            ResultSet resultSet = null;
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getString(1);
            }

        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        }

        return userId;
    }

    public boolean newMember(String name, String firt_pwd, String email){
        Connection conn = null;
        PreparedStatement pStatement = null;
        boolean isSuccess = false;
        int latest_memberId = 0;
        String new_memberId = "";
        try{
            conn = getConnection();
            
            // Get the latest ID
            String preQueryStatement = "select max(memberId) from mem_user;";
            pStatement = conn.prepareStatement(preQueryStatement);
            ResultSet resultSet = null;
            resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                latest_memberId = Integer.parseInt(resultSet.getString(1).substring(1)) + 1;
                new_memberId = "M" + String.format("%05d", latest_memberId);
            }else{
                new_memberId = "M00000";
            }
            pStatement.close();
            
            // Add new member information into table 'mem_user'
            preQueryStatement = "insert into mem_user values (?, ?, ?);";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, new_memberId);
            pStatement.setString(2, "1");
            pStatement.setString(3, email);
            pStatement.executeUpdate();
            pStatement.close();
            
            // Add new member information into table 'mem_login'
            preQueryStatement = "insert into mem_login values (?, ?, ?);";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, new_memberId);
            pStatement.setString(2, name);
            pStatement.setString(3, firt_pwd);
            int rowCount = pStatement.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStatement.close();
            
            conn.close();
            
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        return isSuccess;
    }
    public boolean isExistUserName(String name){
        boolean isExistUserName = false;
        Connection conn = null;
        PreparedStatement pStatement = null;
        String preQueryStatement = "";
        preQueryStatement =  "select * from mem_login where uname=?";
        try{
            conn = getConnection();
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, name);
            
            ResultSet resultSet = null;
            resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                isExistUserName = true;
            }
        } catch(SQLException e){
            while (e != null){
                e.printStackTrace();
                e = e.getNextException();
            }
        }
        return isExistUserName;
    }
    public boolean isExistEmail(String email){
        boolean isExistEmail = false;
        Connection conn = null;
        PreparedStatement pStatement = null;
        String preQueryStatement = "";
        preQueryStatement =  "select * from mem_user where email=?";
        try{
            conn = getConnection();
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, email);
            
            ResultSet resultSet = null;
            resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                isExistEmail = true;
            }
        } catch(SQLException e){
            while (e != null){
                e.printStackTrace();
                e = e.getNextException();
            }
        }
        return isExistEmail;
    }
    public boolean newStaff(String name, String pwd){
        Connection conn = null;
        PreparedStatement pStatement = null;
        int latest_staffId = 0;
        String new_staffId = "";
        boolean isSuccess = false;
        System.out.println("Step 0");
        try{
            conn = getConnection();
            
            // Get the latest staff ID
            String preQueryStatement = "select max(staffId) from staff_user;";
            pStatement = conn.prepareStatement(preQueryStatement);
            ResultSet resultSet = null;
            resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                latest_staffId = Integer.parseInt(resultSet.getString(1).substring(1)) + 1;
                new_staffId = "S" + String.format("%04d", latest_staffId);
            }else{
                new_staffId = "S0000";
            }
            pStatement.close();
            System.out.println(new_staffId);
            
            // Add new staff information into table 'staff_user'
            preQueryStatement = "insert into staff_user values (?, ?, ?);";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, new_staffId);
            pStatement.setString(2, "staff");
            pStatement.setString(3, "1");
            pStatement.executeUpdate();
            pStatement.close();
            System.out.println("Step 2");
            
            // Add new member information into table 'staff_login'
            preQueryStatement = "insert into staff_login values (?, ?, ?);";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, new_staffId);
            pStatement.setString(2, name);
            pStatement.setString(3, pwd);
            int rowCount = pStatement.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStatement.close();
            System.out.println("Step 3");
            conn.close();
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        return isSuccess;
    }
    public ArrayList<Member> getMemberList(String keyWord){
        Connection conn = null;
        PreparedStatement pStatement = null;
        ArrayList<Member> member = new ArrayList<Member>();
        try{
            conn = getConnection();
            String preQueryStatement = "select user.memberId, login.uname, login.upass, user.email, user.member_st from mem_user as user inner join mem_login as login on user.memberId = login.memberId";
            
            if(!keyWord.equals("")){
                preQueryStatement += " where user.memberId like ? or login.uname like ? or user.email like ?";
                pStatement = conn.prepareStatement(preQueryStatement);
                pStatement.setString(1, "%" + keyWord + "%");
                pStatement.setString(2, "%" + keyWord + "%");
                pStatement.setString(3, "%" + keyWord + "%");
            }else{
                pStatement = conn.prepareStatement(preQueryStatement);
            }
            
            ResultSet rs = pStatement.executeQuery();
            while(rs.next()){
                member.add(new Member(rs.getString("memberId"), rs.getString("uname"), rs.getString("upass"), rs.getString("email"), rs.getString("member_st")));
            }
            
            pStatement.close();
            conn.close();
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        return member;
    }
    public ArrayList<Staff> getStaffList(String keyWord){
        Connection conn = null;
        PreparedStatement pStatement = null;
        ArrayList<Staff> staff = new ArrayList<Staff>();
        try{
            conn = getConnection();
            String preQueryStatement = "select user.staffId, login.uname, login.upass, user._role, user.user_st from staff_user as user inner join staff_login as login on user.staffId = login.staffId";
            
            if(!keyWord.equals("")){
                preQueryStatement += " where user.staffId like ? or login.uname like ? or user._role like ?";
                pStatement = conn.prepareStatement(preQueryStatement);
                pStatement.setString(1, "%" + keyWord + "%");
                pStatement.setString(2, "%" + keyWord + "%");
                pStatement.setString(3, "%" + keyWord + "%");
            }else{
                pStatement = conn.prepareStatement(preQueryStatement);
            }
            
            ResultSet rs = pStatement.executeQuery();
            while(rs.next()){
                staff.add(new Staff(rs.getString("staffId"), rs.getString("uname"), rs.getString("upass"), rs.getString("_role"), rs.getString("user_st")));
            }
            
            pStatement.close();
            conn.close();
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        return staff;
    }
    public boolean removeStaff(String id){
        Connection conn = null;
        PreparedStatement pStatement = null;
        boolean isRemoved = false;
        int rowCount;
        try{
            conn = getConnection();
            String preQueryStatement = "delete from staff_login where staffId = ?";
            
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, id);
            rowCount = pStatement.executeUpdate();
            if(rowCount == 0) return isRemoved;
            pStatement.close();
            
            preQueryStatement = "delete from staff_user where staffId = ?";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, id);
            rowCount = pStatement.executeUpdate();
            if(rowCount > 0) isRemoved = true;
            
            pStatement.close();
            conn.close();
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        
        return isRemoved;
    }
    public boolean editStatus(String id, String status){
        Connection conn = null;
        PreparedStatement pStatement = null;
        boolean editStatus = false;
        String preQueryStatement;
        int rowCount;
        if(!(status.matches("0") || status.matches("1"))) return editStatus;
        try{
            conn = getConnection();
            if(id.contains("M")){
                preQueryStatement = "update mem_user set member_st = ? where memberId = ?;";
            }else{
                preQueryStatement = "update staff_user set user_st = ? where staffId = ?;";
            }
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, status);
            pStatement.setString(2, id);
            rowCount = pStatement.executeUpdate();
            if(rowCount > 0) editStatus = true;
            pStatement.close();
            conn.close();
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        
        return editStatus;
    }
    
    public boolean memberInfoUpdate(Member m){
        Connection conn = null;
        PreparedStatement pStatement = null;
        String preQueryStatement;
        int rowCount;
        boolean isUpdated = false;
        System.out.println("1");
        try{
            conn = getConnection();
            preQueryStatement = "update mem_login set upass = ? where memberId = ?;";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, m.getMemberPassword());
            pStatement.setString(2, m.getMemberID());
            rowCount = pStatement.executeUpdate();
            if(rowCount == 0) return isUpdated;
            pStatement.close();
            System.out.println("2");
            
            preQueryStatement = "update mem_user set email = ? where memberId = ?;";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, m.getMemberEmail());
            pStatement.setString(2, m.getMemberID());
            rowCount = pStatement.executeUpdate();
            if(rowCount == 0) return isUpdated;
            else isUpdated = true;
            pStatement.close();
            System.out.println("3");
            
            conn.close();
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        
        return isUpdated;
    }

    
    public boolean addUser(String userId, String role, String status, String email) {
        Connection conn = null;
        PreparedStatement pStatement = null;
        boolean isSuccess = false;

        try {
            conn = getConnection();
            String preQueryStatement
                    = "INSERT INTO sys_user VALUES (?,?,?,?)";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, userId);
            pStatement.setString(2, role);
            pStatement.setString(3, status);
            pStatement.setString(4, email);

            int rowCount = pStatement.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStatement.close();
            conn.close();

        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        }
//        catch (IOException e){
//            e.printStackTrace();
//        }

        return isSuccess;
    }

    public ArrayList<Venue> getAllVenue() {
        Connection conn = null;
        PreparedStatement pStatement = null;
        ArrayList<Venue> venues = new ArrayList();
        ResultSet rs = null;

        try {
            conn = getConnection();
            String preQueryStatement
                    = "SELECT * FROM venue";
            pStatement = conn.prepareStatement(preQueryStatement);

            rs = pStatement.executeQuery();
            while (rs.next()) {
                Venue v = new Venue();
                v.setVenueId(rs.getString("venueId"));
                v.setVenue_pic(rs.getString("venue_pic"));
                v.setVenue_name(rs.getString("venue_name"));
                v.setVenue_type(rs.getString("venue_type"));
                v.setImgPath(rs.getString("imgPath"));
                v.setCapacity(rs.getInt("capacity"));
                v.setLocation(rs.getString("location"));
                v.setDescri(rs.getString("descri"));
                v.setHrfee(rs.getDouble("hrfee"));
                v.setV_status(rs.getString("v_status"));
                v.setOpen_hr(rs.getInt("open_hr"));
                v.setClose_hr(rs.getInt("close_hr"));
                venues.add(v);
            }
            pStatement.close();
            conn.close();

        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        }

        return venues;
    }

    public ArrayList<Booking> getAllBooking() {
        Connection conn = null;
        PreparedStatement pStatement = null;
        ArrayList<Booking> bookings = new ArrayList();
        ResultSet rs = null;

        try {
            conn = getConnection();
            String preQueryStatement
                    = "SELECT * FROM booking WHERE "
                    + "booking_status='0' ORDER BY bkdate ASC";
            pStatement = conn.prepareStatement(preQueryStatement);

            rs = pStatement.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getString("bookingId"));
                booking.setVenueId(rs.getString("venueId"));
                booking.setMemberId(rs.getString("memberId"));
                booking.setBkdate(rs.getString("bkdate"));
                booking.setTsession(rs.getString("tsession"));
                booking.setBooking_status(rs.getString("booking_status"));
                bookings.add(booking);
            }

        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        }
        return bookings;
    }

    public ArrayList<Booking> getBookingsById(String memberId) {    // for member get bookings including non-approved, approved, payed
        Connection conn = null;
        PreparedStatement pStatement = null;
        ArrayList<Booking> bookings = new ArrayList();
        ResultSet rs = null;

        try {
            conn = getConnection();
            String preQueryStatement
                    = "SELECT * FROM booking WHERE memberId=? and "
                    + "(booking_status='0' or booking_status='1') ORDER BY bkdate ASC";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, memberId);

            rs = pStatement.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getString("bookingId"));
                booking.setVenueId(rs.getString("venueId"));
                booking.setMemberId(rs.getString("memberId"));
                booking.setBkdate(rs.getString("bkdate"));
                booking.setTsession(rs.getString("tsession"));
                booking.setBooking_status(rs.getString("booking_status"));
                bookings.add(booking);
            }

        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        }
        return bookings;
    }

    public ArrayList<Booking> getBookingsByVenueId(String venueId){
        Connection conn = null;
        PreparedStatement pStatement = null;
        ArrayList<Booking> bookings = new ArrayList();
        ResultSet rs = null;

        try {
            conn = getConnection();
            String preQueryStatement
                    = "SELECT * FROM booking WHERE venueId=? and "
                    + "booking_status='1' ORDER BY bkdate ASC";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, venueId);

            rs = pStatement.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getString("bookingId"));
                booking.setVenueId(rs.getString("venueId"));
                booking.setMemberId(rs.getString("memberId"));
                booking.setBkdate(rs.getString("bkdate"));
                booking.setTsession(rs.getString("tsession"));
                booking.setBooking_status(rs.getString("booking_status"));
                bookings.add(booking);
            }

        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        }
        return bookings;
    }
    
    public boolean addBooking(String userId, String[] venueIds, String[] timeSessions, String[] dates) {
        Connection conn = null;
        PreparedStatement pStatement = null;
        boolean isSuccess = false;
        int rowCount = 0;

        try {
            conn = getConnection();
            for (int i = 0; i < venueIds.length; i++) {
                String preQueryStatement
                        = "insert into booking (venueId, memberId, bkdate, tsession, booking_status) "
                        + "	values (?, ?, ?, ?, '0')";
                pStatement = conn.prepareStatement(preQueryStatement);
                pStatement.setString(1, venueIds[i]);
                pStatement.setString(2, userId);
                pStatement.setString(3, dates[i]);
                pStatement.setString(4, timeSessions[i]);
                rowCount += pStatement.executeUpdate();
            }

            if (rowCount == venueIds.length) {
                isSuccess = true;
            }
            pStatement.close();
            conn.close();

        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        }

        return isSuccess;
    }

    public boolean cancelBooking(String bookingId) {
        Connection conn = null;
        PreparedStatement pStatement = null;
        boolean isSuccess = false;
        int rowCount = 0;

        try {
            conn = getConnection();
            String preQueryStatement
                    = "UPDATE booking SET booking_status='9' WHERE "
                    + "	bookingId=?";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, bookingId);
            rowCount += pStatement.executeUpdate();

            if (rowCount > 0) {
                isSuccess = true;
            }
            pStatement.close();
            conn.close();

        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        }

        return isSuccess;
    }

    public boolean addGuestToBooking(String[] guestName, String[] guestEmail, String bookingId){
        Connection conn = null;
        PreparedStatement pStatement = null;
        boolean isSuccess = false;
        int rowCount = 0;

        try {
            conn = getConnection();
            for (int i = 0; i < guestName.length; i++) {
                String preQueryStatement
                        = "insert into guest values (?, ?, ?)";
                pStatement = conn.prepareStatement(preQueryStatement);
                pStatement.setString(1, bookingId);
                pStatement.setString(2, guestName[i]);
                pStatement.setString(3, guestEmail[i]);
                rowCount += pStatement.executeUpdate();
            }

            if (rowCount == guestName.length) {
                isSuccess = true;
            }
            if(isSuccess){
                rowCount = 0;
                String preQueryStatement
                        = "UPDATE booking SET booking_status='1' WHERE bookingId=?";
                pStatement = conn.prepareStatement(preQueryStatement);
                pStatement.setString(1, bookingId);
                rowCount = pStatement.executeUpdate();
            }
            if (rowCount > 0) {
                isSuccess = true;
            }
            pStatement.close();
            conn.close();

        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        }

        return isSuccess;
    }

    public ArrayList<String[]> getGuestListByBookingId(String bookingId){
        Connection conn = null;
        PreparedStatement pStatement = null;
        ArrayList<String[]> guestList = new ArrayList();
        ResultSet rs = null;

        try {
            conn = getConnection();
            String preQueryStatement
                    = "SELECT * FROM guest WHERE bookingId=? ORDER BY gname ASC";
            pStatement = conn.prepareStatement(preQueryStatement);
            pStatement.setString(1, bookingId);

            rs = pStatement.executeQuery();
            while (rs.next()) {
                String gname = rs.getString("gname");
                String email = rs.getString("address");
                String[] guest = {gname, email};
                guestList.add(guest);
            }

        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        }
        return guestList;
    }
            
}
