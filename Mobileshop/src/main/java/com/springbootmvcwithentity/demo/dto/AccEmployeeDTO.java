package com.springbootmvcwithentity.demo.dto;

import com.springbootmvcwithentity.demo.entity.Authority;
import com.springbootmvcwithentity.demo.entity.Users;

import java.sql.Timestamp;

public class AccEmployeeDTO {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String pass;
    private Timestamp hireDate;
    private Users user;
    private Authority authority;
}
