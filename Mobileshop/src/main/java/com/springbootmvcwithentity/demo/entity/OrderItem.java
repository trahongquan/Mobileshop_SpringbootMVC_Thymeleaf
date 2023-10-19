package com.springbootmvcwithentity.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "orderitems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderItemID")
    private int orderItemID;

    @Column(name = "OrderID")
    private int orderID;

    @Column(name = "PhoneID")
    private int phoneID;

    @Column(name = "price")
    private String price;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "seri")
    private String seri;

    @Column(name = "missing")
    private int missing;

    // Getters and setters


    public OrderItem() {
    }

    public OrderItem(int orderID, int phoneID, String price, int quantity, String seri, int missing) {
        this.orderID = orderID;
        this.phoneID = phoneID;
        this.price = price;
        this.quantity = quantity;
        this.seri = seri;
        this.missing = missing;
    }

    public int getMissing() {
        return missing;
    }

    public void setMissing(int missing) {
        this.missing = missing;
    }

    public int getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(int orderItemID) {
        this.orderItemID = orderItemID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemID=" + orderItemID +
                ", orderID=" + orderID +
                ", phoneID=" + phoneID +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                ", seri=" + seri +
                '}';
    }
}

