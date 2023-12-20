package com.springbootmvcwithentity.demo.service.categorie;

import com.springbootmvcwithentity.demo.entity.Brands;
import com.springbootmvcwithentity.demo.entity.Categories;

import java.util.List;
//Bỏ
public interface CategorieService {

/*
    public Brands getBrandNameById(int brandId);
*/
    public List<Categories> findAll();
    public Categories findById(int theId);
    public void save(Categories theCategories); // được sử dụng cả add new và update
    public void deleteById(int theId);
}
