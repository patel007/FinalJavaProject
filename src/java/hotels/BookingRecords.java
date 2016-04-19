/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotels;

import java.util.Date;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author patel
 */
@ManagedBean
@ApplicationScoped
public class BookingRecords {

    private String location;
    private String start_date;
    private String end_date;

    /**
     *
     * @param location
     * @param start_date
     * @param end_date
     */
    public BookingRecords(String location, String start_date, String end_date) {

        this.location = location;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public String getStart_date() {
        return start_date;
    }

    /**
     *
     * @param start_date
     */
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     *
     * @return
     */
    public String getEnd_date() {
        return end_date;
    }

    /**
     *
     * @param end_date
     */
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

}
