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
public class Room {
    
    private List<Rooms> Room;
    private Rooms currentRooms;

    /**
     * No-arg Constructor -- sets up list from DB
     */
    public Room() {
        currentRooms = new Rooms(-1, -1, "", -1);
        getRoomFromDB();
    }

    /**
     * Wipe the Room list and update it from the DB
     */
    private void getRoomFromDB() {
        try (Connection conn = DBJMPs.getConnection()) {
            Room = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Rooms");
            while (rs.next()) {
                Rooms r;
                r = new Rooms
                        (
                                rs.getInt("room_id"),
                                rs.getInt("room_num"),
                                rs.getString("room_type"),
                                rs.getInt("num_of_people")
                        );
                Room.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets Rooms List as Empty
            Room = new ArrayList<>();
        }
    }

    /**
     * Retrieve the List of Rooms objects
     *
     * @return the List of Rooms objects
     */
    public List<Rooms> getRoom() {
        return Room;
    }

    /**
     * Retrieve the current Rooms
     *
     * @return the registered Current Rooms
     */
    public Rooms getCurrentRooms() {
        return currentRooms;
    }

    /**
     * Retrieve a Rooms by ID
     *
     * @param room_id    
     * @return the Rooms -- null if not found
     */
    public Rooms getRoomsByRoomId(int room_id) {
        for (Rooms r : Room) {           
            if (r.getRoom_id() == room_id) {
                return r;
            }
        }
        return null;
    }

    /**
     * Retrieve a Rooms by title
     *
     * @param room_num     
     * @return the Rooms -- null if not found
     */
    public Rooms getRoomsByRoomNum(int room_num) {
        for (Rooms r : Room) {           
            if (r.getRoom_num() == room_num) {
                return r;
            }
        }
        return null;
    }

    /**
     * Navigate to a specific Rooms to view
     *
     * @param Rooms the Rooms to view
     * @return the navigation rule
     */
    public String viewRooms(Rooms Rooms) {
        currentRooms = Rooms;
        return "viewRooms";
    }

    /**
     * Navigate to add a new Rooms
     *
     * @return the navigation rule
     */
    public String addRooms() {
       currentRooms = new Rooms(-1, -1, "", -1);
       return "editRooms";
    }

    /**
     * Navigate to edit the current Rooms
     *
     * @return the navigation rule
     */
    public String editRooms() {
        return "editRooms";
    }

    /**
     * Navigate away from editing a Rooms without saving
     *
     * @return the navigation rule
     */
    public String cancelRooms() {
        // currentRooms can be corrupted -- reset it based on the DB
        int room_id = currentRooms.getRoom_id();
        getRoomFromDB();
        currentRooms = getRoomsByRoomId(room_id);
        return "viewRooms";
    }
    
    public int count_room(){
        try {
            Connection conn;
            
            conn=DBJMPs.getConnection();
            
            Statement s = conn.createStatement();
            try (ResultSet r = s.executeQuery("SELECT COUNT(*) AS room_num FROM Rooms")) {
                r.next();
                int count = r.getInt("room_num") ;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        return 50 - count;
    }
}
