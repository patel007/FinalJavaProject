/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotels;

/**
 *
 * @author patel
 */
public class Rooms {
    
    private int room_id;
    private int room_num;
    private String room_type;
    private int num_of_people;

    public Rooms(int room_id, int room_num, String room_type, int num_of_people) {
        this.room_id = room_id;
        this.room_num = room_num;
        this.room_type = room_type;
        this.num_of_people = num_of_people;
    }    

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getNum_of_people() {
        return num_of_people;
    }

    public void setNum_of_people(int num_of_people) {
        this.num_of_people = num_of_people;
    }
    
    
    
}
