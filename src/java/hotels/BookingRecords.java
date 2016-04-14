/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotels;

import java.util.Date;

/**
 *
 * @author patel
 */
public class BookingRecords {
    private int book_id;
    private int cust_id;
    private int room_id;
    private String flags;
    private String location;
    private Date start_date;
    private Date end_date;

    public BookingRecords(int book_id, int cust_id, int room_id, String flags, String location, Date start_date, Date end_date) {
        this.book_id = book_id;
        this.cust_id = cust_id;
        this.room_id = room_id;
        this.flags = flags;
        this.location = location;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    
   
}
