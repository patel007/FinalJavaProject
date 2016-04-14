/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotels;

import java.sql.Timestamp;

/**
 *
 * @author patel
 */
public class Customers {
    private int id;
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

    public Customers(int id, int phone_num, String first_name, String last_name, String email, String address_line1, String address_line2, String city, String province, String country, String postal_code) 
    {
        this.id = id;
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
    }

    
    
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(int phone_num) {
        this.phone_num = phone_num;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
                    
}
