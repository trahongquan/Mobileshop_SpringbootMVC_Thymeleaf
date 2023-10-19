package com.springbootmvcwithentity.demo.service.orderitem;

import com.springbootmvcwithentity.demo.dao.OrderItemRepository;
import com.springbootmvcwithentity.demo.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import java.util.List;
import java.util.Optional;

@Service
public class orderitemServiceImpl implements orderitemsService {

    private OrderItemRepository orderItemRepository;

    @Autowired
    public orderitemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem findById(int id) {
        Optional<OrderItem> result = orderItemRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }else {throw new RuntimeException("Không tìm thấy OrderItem với id= "+id);
        }
    }

    @Override
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteById(int id) {
        orderItemRepository.deleteById(id);
    }
}
