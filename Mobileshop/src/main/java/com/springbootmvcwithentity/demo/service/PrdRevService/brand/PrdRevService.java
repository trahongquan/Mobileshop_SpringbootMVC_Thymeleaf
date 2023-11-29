package com.springbootmvcwithentity.demo.service.PrdRevService.brand;

import com.springbootmvcwithentity.demo.entity.Brands;
import com.springbootmvcwithentity.demo.entity.productreview;

import java.util.List;

public interface PrdRevService {

/*
    public Brands getBrandNameById(int brandId);
*/
    public List<productreview> findAll();
    public productreview findById(int theId);
    public void save(productreview productreview); // được sử dụng cả add new và update
    public void deleteById(int theId);
}
