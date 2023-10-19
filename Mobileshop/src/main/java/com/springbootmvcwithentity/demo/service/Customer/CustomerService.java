package com.springbootmvcwithentity.demo.service.Customer;


import com.springbootmvcwithentity.demo.entity.Customer;
import com.springbootmvcwithentity.demo.entity.Phones;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAll();
    public Customer findById(int theId);
    public void save(Customer theCustomer); // được sử dụng cả add new và update
    public void deleteById(int theId);
}
