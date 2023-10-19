package com.springbootmvcwithentity.demo.service.order;

import com.springbootmvcwithentity.demo.entity.Order;

import java.util.List;

public interface orderService {
    List<Order> findAll();

    Order findById(int id);

    void save(Order order);

    void deleteById(int id);
}
