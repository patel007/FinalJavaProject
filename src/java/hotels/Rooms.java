/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotels;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author patel
 */
@ManagedBean
@ApplicationScoped
public class Rooms {

    private String room_type;
    private int num_of_people;

    /**
     *
     * @param room_type
     * @param num_of_people
     */
    public Rooms(String room_type, int num_of_people) {

        this.room_type = room_type;
        this.num_of_people = num_of_people;
    }

    /**
     *
     * @return
     */
    public String getRoom_type() {
        return room_type;
    }

    /**
     *
     * @param room_type
     */
    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    /**
     *
     * @return
     */
    public int getNum_of_people() {
        return num_of_people;
    }

    /**
     *
     * @param num_of_people
     */
    public void setNum_of_people(int num_of_people) {
        this.num_of_people = num_of_people;
    }

}
