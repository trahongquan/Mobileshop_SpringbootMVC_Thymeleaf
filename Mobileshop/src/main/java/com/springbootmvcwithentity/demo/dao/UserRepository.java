package com.springbootmvcwithentity.demo.dao;

import com.springbootmvcwithentity.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
    public void deleteByUsername(String username);
}
