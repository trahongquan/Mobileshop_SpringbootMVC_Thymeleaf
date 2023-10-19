package com.springbootmvcwithentity.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "Username"))
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserAccountID")
    private Long UserAccountID;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "CustomerID")
    private Long customerID;
    @Column(name = "enabled")
    private int enabled;

    // Constructors, getters, and setters


    public Users() {
    }

    public Users(String username, String password, Long customerID, int enabled) {
        this.username = username;
        this.password = password;
        this.customerID = customerID;
        this.enabled = enabled;
    }

    public Users(Customer customer, int enabled) {
        this.username = customer.getEmail();
        this.password = customer.getPass();
        this.customerID = (long) customer.getCustomerId();
        this.enabled = enabled;
    }

    public Users(Employees employee, int enabled) {
        this.username = employee.getEmail();
        this.password = employee.getPass();
        this.customerID = (long) employee.getEmployeeID();
        this.enabled = enabled;
    }

    public Long getUserAccountID() {
        return UserAccountID;
    }

    public void setUserAccountID(Long userAccountID) {
        UserAccountID = userAccountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
