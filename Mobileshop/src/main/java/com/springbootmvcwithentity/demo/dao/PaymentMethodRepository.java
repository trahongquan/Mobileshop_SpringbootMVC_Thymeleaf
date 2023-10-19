package com.springbootmvcwithentity.demo.dao;

import com.springbootmvcwithentity.demo.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    PaymentMethod findByMethodName(String name);
}

