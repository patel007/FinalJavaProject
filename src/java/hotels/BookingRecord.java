/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotels;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patel
 */
@ManagedBean
@ApplicationScoped
public class BookingRecord {
    
    private List<BookingRecords> BookingRecord;
    private BookingRecords currentBookingRecords;

    /**
     * No-arg Constructor -- sets up list from DB
     */
    public BookingRecord() {
        currentBookingRecords = new BookingRecords(-1, -1, -1, "", "", null, null);
        getBookingRecordFromDB();
    }

    /**
     * Wipe the BookingRecord list and update it from the DB
     */
    private void getBookingRecordFromDB() {
        try (Connection conn = DBJMPs.getConnection()) {
            BookingRecord = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BookingRecords");
            while (rs.next()) {
                BookingRecords br = new BookingRecords
                (
                        rs.getInt("book_id"),
                        rs.getInt("cust_id"),
                        rs.getInt("room_id"),
                        rs.getString("flags"),
                        rs.getString("location"),
                        rs.getTimestamp("start_date"),
                        rs.getTimestamp("end_date")
                );
                BookingRecord.add(br);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingRecord.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets BookingRecords List as Empty
            BookingRecord = new ArrayList<>();
        }
    }

    /**
     * Retrieve the List of BookingRecords objects
     *
     * @return the List of BookingRecords objects
     */
    public List<BookingRecords> getBookingRecord() {
        return BookingRecord;
    }

    /**
     * Retrieve the current BookingRecords
     *
     * @return the registered Current BookingRecords
     */
    public BookingRecords getCurrentBookingRecords() {
        return currentBookingRecords;
    }

    /**
     * Retrieve a BookingRecords by ID
     *
     * @param book_id 
     * @return the BookingRecords -- null if not found
     */
    public BookingRecords getBookingRecordsByBookId(int book_id) {
        for (BookingRecords br : BookingRecord) {           
            if (br.getBook_id() == book_id) {
                return br;
            }
        }
        return null;
    }

    /**
     * Retrieve a BookingRecords by title
     *
     * @param cust_id
     * @return the BookingRecords -- null if not found
     */
    public BookingRecords getBookingRecordsByCustomerId(int cust_id) {
        for (BookingRecords br : BookingRecord) {           
            if (br.getCust_id() == cust_id) {
                return br;
            }
        }
        return null;
    }

    /**
     * Navigate to a specific BookingRecords to view
     *
     * @param BookingRecords the BookingRecords to view
     * @return the navigation rule
     */
    public String viewBookingRecords(BookingRecords BookingRecords) {
        currentBookingRecords = BookingRecords;
        return "viewBookingRecords";
    }

    /**
     * Navigate to add a new BookingRecords
     *
     * @return the navigation rule
     */
    public String addBookingRecords() {
        currentBookingRecords = new BookingRecords(-1, -1, -1, "", "", null, null);
        return "editBookingRecords";
    }

    /**
     * Navigate to edit the current BookingRecords
     *
     * @return the navigation rule
     */
    public String editBookingRecords() {
        return "editBookingRecords";
    }

    /**
     * Navigate away from editing a BookingRecords without saving
     *
     * @return the navigation rule
     */
    public String cancelBookingRecords() {
        // currentBookingRecords can be corrupted -- reset it based on the DB
        int book_id = currentBookingRecords.getBook_id();
        getBookingRecordFromDB();
        currentBookingRecords = getBookingRecordsByBookId(book_id);
        return "viewBookingRecords";
    }
    
        
         public String connect(){  
        Connection conn;
        try {
    
            
            conn = DBJMPs.getConnection();
            String sql = "INSERT into BookingRecords (book_id, cust_id, room_id, flags, location, start_date, end_date ) values(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentBookingRecords.getBook_id() );
            pstmt.setInt(2, currentBookingRecords.getCust_id());
            pstmt.setInt(3, currentBookingRecords.getRoom_id());
            pstmt.setString(4, currentBookingRecords.getFlags());
            pstmt.setString(5, currentBookingRecords.getLocation());
            pstmt.setDate(6, (Date) currentBookingRecords.getStart_date());
            pstmt.setDate(7, (Date) currentBookingRecords.getEnd_date());
            
            int i = pstmt.executeUpdate();
            //conn.close();
            if(i>0){
                return "booking";
            }
            else{
                return "index";
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
}
}
