/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotels;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author patel
 */
@ManagedBean
@ApplicationScoped
public class Login {

    String email;
    String password;

    /**
     *
     */
    public Login() {
    }

    /**
     *
     * @param email
     * @param password
     */
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return @throws SQLException
     */
    public String login() throws SQLException {

        FacesContext message = FacesContext.getCurrentInstance();
        Connection conn = DBJMPs.getConnection();
        Statement st = conn.createStatement();
        String query = "select password from customers where email='" + email + "';";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            if (password.equals(rs.getString("password"))) {
                message.getExternalContext().getSessionMap().put("user_session", email);
                return "booking";
            } else {
                message.addMessage(null, new FacesMessage("Invalid username and password"));
                return "login";

            }
        } else {
            message.addMessage(null, new FacesMessage("Invalid user try registering first"));
            return "login";
        }

    }

    /**
     *
     * @return
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    /**
     *
     */
    public void updateUsersFromDB() {

    }
}
