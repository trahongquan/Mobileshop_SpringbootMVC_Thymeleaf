package com.springbootmvcwithentity.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "CartCookie")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private int cartId;

    @Column(name = "CustomerID")
    private int customerId;

    @Column(name = "Model")
    private String model;

    @Column(name = "Quantity")
    private int quantity;

    // Constructors, getters, and setters
}
