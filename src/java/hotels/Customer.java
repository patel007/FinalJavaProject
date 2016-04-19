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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author patel
 */
@ManagedBean
@ApplicationScoped
public class Customer {

    public List<Customers> Customer;
    private Customers currentCustomers;

    /**
     *
     * @param Customer
     * @param currentCustomers
     */
    public Customer(List<Customers> Customer, Customers currentCustomers) {
        this.Customer = Customer;
        this.currentCustomers = currentCustomers;
    }

    /**
     *
     * @param Customer
     */
    public void setCustomer(List<Customers> Customer) {
        this.Customer = Customer;
    }

    /**
     *
     * @param currentCustomers
     */
    public void setCurrentCustomers(Customers currentCustomers) {
        this.currentCustomers = currentCustomers;
    }

    /**
     * No-arg Constructor -- sets up list from DB
     */
    public Customer() {
        currentCustomers = new Customers(-1, "", "", "", "", "", "", "", "", "", "");
        getCustomerFromDB();
    }

    /**
     * Wipe the Customer list and update it from the DB
     */
    public List getCustomerFromDB() {
        try (Connection conn = DBJMPs.getConnection()) {
            Customer = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
            while (rs.next()) {
                Customers c = new Customers(
                        rs.getInt("phone_num"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("address_line1"),
                        rs.getString("address_line2"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("country"),
                        rs.getString("postal_code"),
                        rs.getString("password")
                );
                Customer.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets Customers List as Empty
            Customer = new ArrayList<>();
        }
        return Customer;
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
     * @param first_name
     * @param id the ID to search for
     * @return the Customers -- null if not found
     */
    public Customers getCustomersById(String first_name) {
        for (Customers c : Customer) {
            if (c.getFirst_name() == first_name) {
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
        Connection conn;

        try {
            conn = DBJMPs.getConnection();
            String sql = "insert into customers( first_name, last_name, phone_num, email, address_line1, address_line2, city, province, country, postal_code, password) values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, currentCustomers.getFirst_name());
            ps.setString(2, currentCustomers.getLast_name());
            ps.setInt(3, currentCustomers.getPhone_num());
            ps.setString(4, currentCustomers.getEmail());
            ps.setString(5, currentCustomers.getAddress_line1());
            ps.setString(6, currentCustomers.getAddress_line2());
            ps.setString(7, currentCustomers.getCity());
            ps.setString(8, currentCustomers.getProvince());
            ps.setString(9, currentCustomers.getCountry());
            ps.setString(10, currentCustomers.getPostal_code());
            ps.setString(11, currentCustomers.getPassword());
            int i = ps.executeUpdate();
            if (i > 0) {
                getCustomerFromDB();

                return "login";
            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
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
        String first_name = currentCustomers.getFirst_name();
        getCustomerFromDB();
        currentCustomers = getCustomersById(first_name);
        return "viewCustomers";
    }

    /**
     *
     * @return @throws SQLException
     */
    public String login() throws SQLException {

        FacesContext message = FacesContext.getCurrentInstance();
        Connection conn = DBJMPs.getConnection();
        Statement st = conn.createStatement();
        String query = "select password from customers where email='" + currentCustomers.getEmail() + "';";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            if (currentCustomers.getPassword().equals(rs.getString("password"))) {
                message.getExternalContext().getSessionMap().put("user_session", currentCustomers.getEmail());
                return "booking";
            } else {
                message.addMessage(null, new FacesMessage("Invalid username and password"));
                return "index";

            }
        } else {
            message.addMessage(null, new FacesMessage("Invalid user try registering first"));
            return null;
        }

    }
}
