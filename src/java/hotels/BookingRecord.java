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
        currentBookingRecords = new BookingRecords("", null, null);
        getBookingRecordFromDB();
    }

    /**
     * Wipe the BookingRecord list and update it from the DB
     */
    private void getBookingRecordFromDB() {
        try (Connection conn = DBJMPs.getConnection()) {
            BookingRecord = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM booking_records");
            while (rs.next()) {
                BookingRecords br = new BookingRecords(
                        rs.getString("location"),
                        rs.getString("start_date"),
                        rs.getString("end_date")
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
//    public BookingRecords getBookingRecordsByBookId(int book_id) {
//        for (BookingRecords br : BookingRecord) {           
//            if (br.getBook_id() == book_id) {
//                return br;
//            }
//        }
//        return null;
//    }
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
        Connection conn;
        try {
            //java.sql.Date sqlStartDate = currentBookingRecords.getStart_date();

            conn = DBJMPs.getConnection();
            String sql = "INSERT into booking_records (location, start_date, end_date ) values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, currentBookingRecords.getLocation());
            pstmt.setString(2, currentBookingRecords.getStart_date());
            pstmt.setString(3, currentBookingRecords.getEnd_date());

            int i = pstmt.executeUpdate();
            //conn.close();
            if (i > 0) {
                return "room";
            } else {
                return "booking";
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
//        int book_id = currentBookingRecords.getBook_id();
//        getBookingRecordFromDB();
//        currentBookingRecords = getBookingRecordsByBookId(book_id);
        return "viewBookingRecords";
    }

}
