/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotels;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patel
 */
public class DBJMPs {

    /**
     *
     */
    public final static String SALT = "THISISArandomSTRINGofCHARACTERSusedTOsaltTHEpasswords";

    /**
     *
     * @param password
     * @return
     */
    public static String hash(String password) {
        try {
            String salted = password + SALT;
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] hash = md.digest(salted.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(b & 0xff).toUpperCase();
                if (hex.length() == 1) {
                    sb.append("0");
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(DBJMPs.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBJMPs.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        String hostname = "LocalHost";
        String port = "3306";
        String dbname = "hotels";
        String username = "root";
        String password = "";
        String jdbc = String.format("jdbc:mysql://%s:%s/%s", hostname, port, dbname);
        return DriverManager.getConnection(jdbc, username, password);
    }
}
