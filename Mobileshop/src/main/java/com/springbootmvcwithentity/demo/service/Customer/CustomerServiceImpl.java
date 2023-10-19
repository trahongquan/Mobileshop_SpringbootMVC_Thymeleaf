package com.springbootmvcwithentity.demo.service.Customer;

import com.springbootmvcwithentity.demo.dao.CustomerRepository;
import com.springbootmvcwithentity.demo.dao.PhoneRepository;
import com.springbootmvcwithentity.demo.entity.Customer;
import com.springbootmvcwithentity.demo.entity.Phones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int CustomerId) {
        Optional<Customer> result = customerRepository.findById(CustomerId);
        if (result.isPresent()) {
            return result.get(); // Trả về đối tượng Phone nếu nó tồn tại
        } else {
            throw new RuntimeException("Không tìm thấy điện thoại với ID=" + CustomerId);
        }
    }

    @Override
    public void save(Customer customer) {

        customerRepository.save(customer);

    }

    @Override
    public void deleteById(int CustomerId) {
        customerRepository.deleteById(CustomerId);
    }

}
