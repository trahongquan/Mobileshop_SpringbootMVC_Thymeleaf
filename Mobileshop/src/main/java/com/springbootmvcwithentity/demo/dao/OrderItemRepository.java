package com.springbootmvcwithentity.demo.dao;

import com.springbootmvcwithentity.demo.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    public List<OrderItem> findAllByOrderID(int orderID);
}

