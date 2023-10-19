package com.springbootmvcwithentity.demo.service.orderitem;
import com.springbootmvcwithentity.demo.entity.OrderItem;

import java.util.List;

public interface orderitemsService {

        List<OrderItem> findAll();

        OrderItem findById(int id);

        void save(OrderItem orderItem);

        void deleteById(int id);
}
