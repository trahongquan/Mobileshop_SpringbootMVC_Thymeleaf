package com.springbootmvcwithentity.demo.service.cart;

import com.springbootmvcwithentity.demo.dao.CartRepository;
import com.springbootmvcwithentity.demo.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(int cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    @Override
    public List<Cart> getCartsByCustomerId(int customerId) {
        // Implement logic to get carts by customer ID
        // You can use cartRepository.findByCustomerId(customerId);
        return null;
    }

    @Override
    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void removeFromCart(int cartId) {
        cartRepository.deleteById(cartId);
    }
}

