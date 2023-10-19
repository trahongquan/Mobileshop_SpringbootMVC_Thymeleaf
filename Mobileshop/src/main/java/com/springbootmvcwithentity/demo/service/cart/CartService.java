package com.springbootmvcwithentity.demo.service.cart;

import com.springbootmvcwithentity.demo.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();

    Cart getCartById(int cartId);

    List<Cart> getCartsByCustomerId(int customerId);

    void addToCart(Cart cart);

    void updateCart(Cart cart);

    void removeFromCart(int cartId);

}
