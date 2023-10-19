package com.springbootmvcwithentity.demo.dto;

import com.springbootmvcwithentity.demo.entity.Order;
import com.springbootmvcwithentity.demo.entity.OrderItem;
import com.springbootmvcwithentity.demo.entity.Phones;

import javax.persistence.Column;
import java.util.List;

public class OrderitemDTO {

    private int orderItemID;

    private int orderID;

    private int phoneID;

    private String price;

    private int quantity;

    private int missing;

    private String seri;

    private List<String> seris;

    private PhoneDTO phoneDTO;

    public OrderitemDTO() {
    }

    public OrderitemDTO(int orderItemID, int orderID, int phoneID, String price, int quantity, int missing, String seri, List<String> seris) {
        this.orderItemID = orderItemID;
        this.orderID = orderID;
        this.phoneID = phoneID;
        this.price = price;
        this.quantity = quantity;
        this.missing = missing;
        this.seri = seri;
        this.seris = seris;
    }

    public OrderitemDTO(OrderItem orderItem, List<String> seris) {
        this.orderItemID = orderItem.getOrderItemID();
        this.orderID = orderItem.getOrderID();
        this.phoneID = orderItem.getPhoneID();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
        this.missing = orderItem.getMissing();
        this.seri = orderItem.getSeri();
        this.seris = seris;
    }

    public OrderitemDTO(OrderItem orderItem, PhoneDTO phoneDTO) {
        this.orderItemID = orderItem.getOrderItemID();
        this.orderID = orderItem.getOrderID();
        this.phoneID = orderItem.getPhoneID();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
        this.missing = orderItem.getMissing();
        this.seri = orderItem.getSeri();
        this.phoneDTO = phoneDTO;
    }

    public PhoneDTO getPhoneDTO() {
        return phoneDTO;
    }

    public void setPhoneDTO(PhoneDTO phoneDTO) {
        this.phoneDTO = phoneDTO;
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

    public int getMissing() {
        return missing;
    }

    public void setMissing(int missing) {
        this.missing = missing;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public List<String> getSeris() {
        return seris;
    }

    public void setSeris(List<String> seris) {
        this.seris = seris;
    }
}
