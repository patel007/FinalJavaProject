/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotels;

import java.sql.Timestamp;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author patel
 */
@ManagedBean
@ApplicationScoped
public class Customers {

    private int phone_num;
    private String first_name;
    private String last_name;
    private String email;
    private String address_line1;
    private String address_line2;
    private String city;
    private String province;
    private String country;
    private String postal_code;
    private String password;

    /**
     *
     */
    public Customers() {
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
     * @param phone_num
     * @param first_name
     * @param last_name
     * @param email
     * @param address_line1
     * @param address_line2
     * @param city
     * @param province
     * @param country
     * @param postal_code
     * @param password
     */
    public Customers(int phone_num, String first_name, String last_name, String email, String address_line1, String address_line2, String city, String province, String country, String postal_code, String password) {
        this.phone_num = phone_num;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postal_code = postal_code;
        this.password = password;
    }

    /**
     *
     * @return
     */
    public int getPhone_num() {
        return phone_num;
    }

    /**
     *
     * @param phone_num
     */
    public void setPhone_num(int phone_num) {
        this.phone_num = phone_num;
    }

    /**
     *
     * @return
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     *
     * @param first_name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     *
     * @return
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     *
     * @param last_name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
    public String getAddress_line1() {
        return address_line1;
    }

    /**
     *
     * @param address_line1
     */
    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    /**
     *
     * @return
     */
    public String getAddress_line2() {
        return address_line2;
    }

    /**
     *
     * @param address_line2
     */
    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getProvince() {
        return province;
    }

    /**
     *
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     *
     * @param postal_code
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

}
