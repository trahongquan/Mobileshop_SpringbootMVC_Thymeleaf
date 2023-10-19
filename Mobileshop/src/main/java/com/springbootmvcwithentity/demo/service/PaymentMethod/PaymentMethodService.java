package com.springbootmvcwithentity.demo.service.PaymentMethod;

import com.springbootmvcwithentity.demo.entity.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodService {
    List<PaymentMethod> findAll();

    PaymentMethod findById(Long paymentMethodID);

    void save(PaymentMethod paymentMethod);

    void deleteById(Long paymentMethodID);
}

