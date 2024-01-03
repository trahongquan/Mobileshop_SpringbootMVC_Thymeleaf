package com.springbootmvcwithentity.demo.service.cart;

import com.springbootmvcwithentity.demo.entity.Wish;

import java.util.List;

public interface WishService {
    List<Wish> getAllCarts();

    Wish getWishById(int cartId);

    List<Wish> getWishByCustomerId(int customerId);

    void addToWish(Wish cart);

    void updateWish(Wish cart);

    void removeFromWish(int cartId);

}
