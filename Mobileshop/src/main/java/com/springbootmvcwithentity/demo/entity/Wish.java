package com.springbootmvcwithentity.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "wish")
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishID")
    private int wishId;

    @Column(name = "customerID")
    private int customerId;

    @Column(name = "PhoneID")
    private int phoneID;

    @Column(name = "Quantity")
    private int quantity;

    // Constructors, getters, and setters
    public Wish(){}
    public Wish(int customerId, int phoneID, int quantity) {
        this.customerId = customerId;
        this.phoneID = phoneID;
        this.quantity = quantity;
    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
