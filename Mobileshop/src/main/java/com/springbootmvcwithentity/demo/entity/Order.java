package com.springbootmvcwithentity.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private int orderID;

    @Column(name = "CustomerID")
    private int customerId;

    @Column(name = "OrderDate")
    private String orderDate;

    @Column(name = "dateprocessed")
    private String dateProcessed;

    @Column(name = "PaymentMethodID")
    private long paymentMethodId;

    @Column(name = "Amount")
    private double amount;

    @Column(name = "NumberOrAddressPayment")
    private String numberOrAddressPayment;

    @Column(name = "CVV")
    private String cvv;

    @Column(name = "ExpirationDate")
    private String expirationDate;

    @Column(name = "EmployeeID")
    private String employeeID;

    // Getters and setters


    public Order() {
    }

    public Order(Customer customer, String orderDate, String dateProcessed, PaymentMethod paymentMethod,
                 double amount, String numberOrAddressPayment, String cvv, String expirationDate) {
        this.customerId = customer.getCustomerId();
        this.orderDate = orderDate;
        this.dateProcessed = dateProcessed;
        this.paymentMethodId = paymentMethod.getPaymentMethodID();
        this.amount = amount;
        this.numberOrAddressPayment = numberOrAddressPayment;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    public Order(Customer customer, String orderDate, String dateProcessed, PaymentMethod paymentMethod,
                 double amount, String numberOrAddressPayment, String cvv, String expirationDate, String employeeID) {
        this.customerId = customer.getCustomerId();
        this.orderDate = orderDate;
        this.dateProcessed = dateProcessed;
        this.paymentMethodId = paymentMethod.getPaymentMethodID();
        this.amount = amount;
        this.numberOrAddressPayment = numberOrAddressPayment;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.employeeID = employeeID;
    }


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(String dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNumberOrAddressPayment() {
        return numberOrAddressPayment;
    }

    public void setNumberOrAddressPayment(String numberOrAddressPayment) {
        this.numberOrAddressPayment = numberOrAddressPayment;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", customerId=" + customerId +
                ", orderDate='" + orderDate + '\'' +
                ", dateProcessed='" + dateProcessed + '\'' +
                ", paymentMethodId=" + paymentMethodId +
                ", amount=" + amount +
                ", numberOrAddressPayment='" + numberOrAddressPayment + '\'' +
                ", cvv='" + cvv + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", employeeID='" + employeeID + '\'' +
                '}';
    }
}
