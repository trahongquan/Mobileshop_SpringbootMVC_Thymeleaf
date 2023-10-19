//package com.springbootmvcwithentity.demo.entity;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "user_account")
//public class UserAccount {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "UserAccountID")
//    private Long id;
//
//    @Column(name = "Username", unique = true, nullable = false)
//    private String username;
//
//    @Column(name = "Password", nullable = false)
//    private String password;
//
//    @Column(name = "Authority", nullable = false)
//    private String authority;
//
//    @Column(name = "CustomerID")
//    private Long customerId;
//
//    @Column(name = "enabled")
//    private Long enabled;
//
//    // Constructors, getters, and setters
//
//    public UserAccount() {
//        // Default constructor
//    }
//
//    public UserAccount(String username, String password, String authority, Long customerId, Long enabled) {
//        this.username = username;
//        this.password = password;
//        this.authority = authority;
//        this.customerId = customerId;
//        this.enabled = enabled;
//    }
//
//    // Getters and setters for other fields
//
//    // You can generate getters and setters using your IDE or manually write them.
//}
