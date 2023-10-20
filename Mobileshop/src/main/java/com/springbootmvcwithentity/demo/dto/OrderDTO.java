package com.springbootmvcwithentity.demo.dto;

import com.springbootmvcwithentity.demo.entity.*;

import java.util.List;

public class OrderDTO {
    private int orderID;

    private int customerId;

    private String orderDate;

    private String dateProcessed;

    private long paymentMethodId;

    private double amount;

    private String numberOrAddressPayment;

    private String cvv;

    private String expirationDate;

    private String employeeID;

    private List<OrderitemDTO> orderitemDTOS;

    private List<Phones> phones;

    private Phones phone;

    private List<String> seris;

    private Customer customer;

    private Employees employee;

    public OrderDTO() {
    }

    public OrderDTO(int customerId, String orderDate, String dateProcessed, long paymentMethodId, double amount, String numberOrAddressPayment, String cvv, String expirationDate, List<OrderitemDTO> orderitemDTOS, Customer customer) {
        this.orderID = orderID;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.dateProcessed = dateProcessed;
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.numberOrAddressPayment = numberOrAddressPayment;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.orderitemDTOS = orderitemDTOS;
        this.customer = customer;
    }

    public OrderDTO(Order order, List<OrderitemDTO> orderitemDTOS, List<String> seris, List<Phones> phones, Customer customer) {
        this.orderID = order.getOrderID();
        this.customerId = order.getCustomerId();
        this.orderDate = order.getOrderDate() ;
        this.dateProcessed = order.getDateProcessed();
        this.paymentMethodId = order.getPaymentMethodId();
        this.amount = order.getAmount();
        this.numberOrAddressPayment = order.getNumberOrAddressPayment();
        this.cvv = order.getCvv();
        this.expirationDate = order.getExpirationDate();
        this.employeeID = order.getEmployeeID();
        this.orderitemDTOS = orderitemDTOS;
        this.seris = seris;
        this.phones = phones;
        this.customer = customer;
    }

    public OrderDTO(Order order, List<OrderitemDTO> orderitemDTOS, Customer customer) {
        this.orderID = order.getOrderID();
        this.customerId = order.getCustomerId();
        this.orderDate = order.getOrderDate() ;
        this.dateProcessed = order.getDateProcessed();
        this.paymentMethodId = order.getPaymentMethodId();
        this.amount = order.getAmount();
        this.numberOrAddressPayment = order.getNumberOrAddressPayment();
        this.cvv = order.getCvv();
        this.expirationDate = order.getExpirationDate();
        this.employeeID = order.getEmployeeID();
        this.orderitemDTOS = orderitemDTOS;
        this.customer = customer;
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

    public List<OrderitemDTO> getOrderitemDTOS() {
        return orderitemDTOS;
    }

    public void setOrderitemDTOS(List<OrderitemDTO> orderitemDTOS) {
        this.orderitemDTOS = orderitemDTOS;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public List<String> getSeri() {
        return seris;
    }

    public void setSeri(List<String> seris) {
        this.seris = seris;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public List<Phones> getPhones() {
        return phones;
    }

    public void setPhones(List<Phones> phones) {
        this.phones = phones;
    }

    public Phones getPhone() {
        return phone;
    }

    public void setPhone(Phones phone) {
        this.phone = phone;
    }
}
