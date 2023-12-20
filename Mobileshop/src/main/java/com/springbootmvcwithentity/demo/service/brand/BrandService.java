package com.springbootmvcwithentity.demo.service.brand;

import com.springbootmvcwithentity.demo.entity.Brands;

import java.util.List;

public interface BrandService {

/*
    public Brands getBrandNameById(int brandId);
*/
    public List<Brands> findAll();
    public Brands findById(int theId);
    public void save(Brands theBrands); // được sử dụng cả add new và update
    public void deleteById(int theId);
    boolean isBrandInUse(int brandID);
}
