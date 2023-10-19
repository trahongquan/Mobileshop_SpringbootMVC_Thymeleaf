package com.springbootmvcwithentity.demo.dao;


import com.springbootmvcwithentity.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> findAllByCustomerId(int id);
}


