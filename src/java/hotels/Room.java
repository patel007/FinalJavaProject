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
        currentRooms = new Rooms("", -1);
        getRoomFromDB();
    }

    /**
     * Wipe the Room list and update it from the DB
     */
    private void getRoomFromDB() {
        try (Connection conn = DBJMPs.getConnection()) {
            Room = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rooms");
            while (rs.next()) {
                Rooms r;
                r = new Rooms(
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
     * @param room_type
     *
     * @return the Rooms -- null if not found
     */
    public Rooms getRoomsByRoomId(String room_type) {
        for (Rooms r : Room) {
            if (r.getRoom_type().equals(room_type)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Retrieve a Rooms by title
     *
     * @param num_of_people
     *
     * @return the Rooms -- null if not found
     */
    public Rooms getRoomsByRoomNum(int num_of_people) {
        for (Rooms r : Room) {
            if (r.getNum_of_people() == num_of_people) {
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
        Connection conn;

        try {
            conn = DBJMPs.getConnection();
            String sql = "insert into rooms(room_type, num_of_people) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, currentRooms.getRoom_type());
            ps.setInt(2, currentRooms.getNum_of_people());
            ps.executeUpdate();
            getRoomFromDB();

        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "feedback";
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
        String room_type = currentRooms.getRoom_type();
        getRoomFromDB();
        currentRooms = getRoomsByRoomId(room_type);
        return "viewRooms";
    }

    /**
     *
     * @return
     */
    public int count_room() {
        try {
            Connection conn;

            conn = DBJMPs.getConnection();

            Statement s = conn.createStatement();
            try (ResultSet r = s.executeQuery("SELECT COUNT(*) AS room_num FROM Rooms")) {
                r.next();
                int count = r.getInt("room_num");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        return 50 - count;
    }
}
