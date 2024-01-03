package com.springbootmvcwithentity.demo.service.cart;

import com.springbootmvcwithentity.demo.dao.WishRepository;
import com.springbootmvcwithentity.demo.entity.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishServiceImpl implements WishService {
    private final WishRepository wishRepository;

    @Autowired
    public WishServiceImpl(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    @Override
    public List<Wish> getAllCarts() {
        return wishRepository.findAll();
    }

    @Override
    public Wish getWishById(int cartId) {
        return wishRepository.findById(cartId).orElse(null);
    }

    @Override
    public List<Wish> getWishByCustomerId(int customerId) {
        // Implement logic to get carts by customer ID
        // You can use wishRepository.findByCustomerId(customerId);
        return null;
    }

    @Override
    public void addToWish(Wish cart) {
        wishRepository.save(cart);
    }

    @Override
    public void updateWish(Wish cart) {
        wishRepository.save(cart);
    }

    @Override
    public void removeFromWish(int cartId) {
        wishRepository.deleteById(cartId);
    }
}

