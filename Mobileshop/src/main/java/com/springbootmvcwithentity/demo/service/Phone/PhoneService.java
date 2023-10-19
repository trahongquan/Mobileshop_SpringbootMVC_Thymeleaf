package com.springbootmvcwithentity.demo.service.Phone;


import com.springbootmvcwithentity.demo.entity.Phones;

import java.util.List;

public interface PhoneService {

    public List<Phones> findAll();
    public Phones findById(int theId);
    public void save(Phones thePhone); // được sử dụng cả add new và update
    public void deleteById(int theId);
}
