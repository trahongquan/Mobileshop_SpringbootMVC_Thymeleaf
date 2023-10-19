package com.springbootmvcwithentity.demo.service.PaymentMethod;

import com.springbootmvcwithentity.demo.dao.PaymentMethodRepository;
import com.springbootmvcwithentity.demo.entity.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod findById(Long paymentMethodID) {
        Optional<PaymentMethod> result = paymentMethodRepository.findById(paymentMethodID);
        if (result.isPresent()) {
            return result.get(); // Trả về đối tượng Phone nếu nó tồn tại
        } else {
            throw new RuntimeException("Không tìm thấy điện thoại với ID=" + paymentMethodID);
        }
    }

    @Override
    public void save(PaymentMethod paymentMethod) {}

    @Override
    public void deleteById(Long paymentMethodID) {
        paymentMethodRepository.deleteById(paymentMethodID);
    }
}
