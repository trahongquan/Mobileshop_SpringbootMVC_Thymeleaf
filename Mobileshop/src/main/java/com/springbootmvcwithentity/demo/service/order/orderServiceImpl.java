package com.springbootmvcwithentity.demo.service.order;

import com.springbootmvcwithentity.demo.dao.OrderRepository;
import com.springbootmvcwithentity.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class orderServiceImpl implements orderService {

    private OrderRepository orderRepository;
    @Autowired
    public orderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(int id) {
        Optional<Order> oder = orderRepository.findById(id);
        if(oder.isPresent()){
            return oder.get();
        }else {
            throw new RuntimeException("Không tìm thấy Order id="+ id);
        }
    }

    @Override
    public void save(Order orderItem) {
        orderRepository.save(orderItem);
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }
}
