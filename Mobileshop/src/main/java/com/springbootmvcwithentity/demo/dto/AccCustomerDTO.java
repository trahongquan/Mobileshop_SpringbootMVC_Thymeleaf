package com.springbootmvcwithentity.demo.dto;

import com.springbootmvcwithentity.demo.entity.Authority;
import com.springbootmvcwithentity.demo.entity.Customer;
import com.springbootmvcwithentity.demo.entity.Users;

import java.sql.Timestamp;

public class AccCustomerDTO {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String pass;
    private String address;
    private Timestamp registrationDate;
    private Users user;
    private Authority authority;

    public AccCustomerDTO(Customer customer, Users user, Authority authority) {
        this.customerId = customer.getCustomerId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.pass = customer.getPass();
        this.address = customer.getAddress();
        this.registrationDate = customer.getRegistrationDate();
        this.user = user;
        this.authority = authority;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
