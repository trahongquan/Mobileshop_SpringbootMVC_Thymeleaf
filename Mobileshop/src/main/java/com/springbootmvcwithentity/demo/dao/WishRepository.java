package com.springbootmvcwithentity.demo.dao;

import com.springbootmvcwithentity.demo.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Integer> {
    // Add custom queries if needed
    Wish findByCustomerId(int id);
}
