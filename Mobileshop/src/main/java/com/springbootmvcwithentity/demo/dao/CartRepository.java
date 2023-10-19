package com.springbootmvcwithentity.demo.dao;

import com.springbootmvcwithentity.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    // Add custom queries if needed
}
