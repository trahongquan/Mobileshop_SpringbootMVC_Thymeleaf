package com.springbootmvcwithentity.demo.service.Users;

import com.springbootmvcwithentity.demo.entity.Users;

import java.util.List;

public interface UserService {

    Users findByUsername(String username);

    List<Users> getAllUsers();

    Users createUser(Users user);

    void updateUser(Users user);

    void deleteUser(String username);
    public void save(Users user);
}
