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
public class Customer {
    
   private List<Customers> Customer;
    private Customers currentCustomers;

    /**
     * No-arg Constructor -- sets up list from DB
     */
    public Customer() {
        currentCustomers = new Customers(-1, -1, "", "", "", "", "", "", "", "", "");
        getCustomerFromDB();
    }

    /**
     * Wipe the Customer list and update it from the DB
     */
    private void getCustomerFromDB() {
        try (Connection conn = DBJMPs.getConnection()) {
            Customer = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
            while (rs.next()) {
                Customers c = new Customers(
                        rs.getInt("id"),
                        rs.getInt("phone_num"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("address_line1"),
                        rs.getString("address_line2"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("country"),
                        rs.getString("postal_code")         
                        
                );
                Customer.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets Customers List as Empty
            Customer = new ArrayList<>();
        }
    }

    /**
     * Retrieve the List of Customers objects
     *
     * @return the List of Customers objects
     */
    public List<Customers> getCustomer() {
        return Customer;
    }

    /**
     * Retrieve the current Customers
     *
     * @return the registered Current Customers
     */
    public Customers getCurrentCustomers() {
        return currentCustomers;
    }

    /**
     * Retrieve a Customers by ID
     *
     * @param id the ID to search for
     * @return the Customers -- null if not found
     */
    public Customers getCustomersById(int id) {
        for (Customers c : Customer) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Retrieve a Customers by title
     *
     * @param first_name     
     * @return the Customers -- null if not found
     */
    public Customers getCustomersByName(String first_name) {
        for (Customers c : Customer) {            
            if (c.getFirst_name().equals(first_name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Navigate to a specific Customers to view
     *
     * @param Customers the Customers to view
     * @return the navigation rule
     */
    public String viewCustomers(Customers Customers) {
        currentCustomers = Customers;
        return "viewCustomers";
    }

    /**
     * Navigate to add a new Customers
     *
     * @return the navigation rule
     */
    public String addCustomers() {
        currentCustomers = new Customers(-1, -1, "", "", "", "", "", "", "", "", "");
        return "editCustomers";
    }

    /**
     * Navigate to edit the current Customers
     *
     * @return the navigation rule
     */
    public String editCustomers() {
        return "editCustomers";
    }

    /**
     * Navigate away from editing a Customers without saving
     *
     * @return the navigation rule
     */
    public String cancelCustomers() {
        // currentCustomers can be corrupted -- reset it based on the DB
        int id = currentCustomers.getId();
        getCustomerFromDB();
        currentCustomers = getCustomersById(id);
        return "viewCustomers";
    }

    /**
     * Navigate away from editing a Customers and save it
     *
     * @param user the current user editing the Customers
     * @return the navigation rule
     */
//    public String saveCustomers(User user) {
//        try (Connection conn = DBJMPs.getConnection()) {
//            // If there's a current Customers, update rather than insert
//            if (currentCustomers.getId() >= 0) {
//                String sql = "UPDATE Customer SET title = ?, contents = ? WHERE id = ?";
//                PreparedStatement pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1, currentCustomers.getTitle());
//                pstmt.setString(2, currentCustomers.getContents());
//                pstmt.setInt(3, currentCustomers.getId());
//                pstmt.executeUpdate();
//            } else {
//                String sql = "INSERT INTO Customer (user_id, title, created_time, contents) VALUES (?,?,NOW(),?)";
//                PreparedStatement pstmt = conn.prepareStatement(sql);
//                pstmt.setInt(1, user.getId());
//                pstmt.setString(2, currentCustomers.getTitle());
//                pstmt.setString(3, currentCustomers.getContents());
//                pstmt.executeUpdate();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        getCustomerFromDB();
//        // Update the currentCustomers so that its details appear after navigation
//        currentCustomers = getCustomersByTitle(currentCustomers.getTitle());
//        return "viewCustomers";
//    }
}
