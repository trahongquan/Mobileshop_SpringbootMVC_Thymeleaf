package com.springbootmvcwithentity.demo.service.categorie;

import com.springbootmvcwithentity.demo.dao.CategorieRepository;
import com.springbootmvcwithentity.demo.dao.PhoneRepository;
import com.springbootmvcwithentity.demo.entity.Brands;
import com.springbootmvcwithentity.demo.entity.Categories;
import com.springbootmvcwithentity.demo.entity.Phones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//Bỏ
@Service
public class CategorieServiceImpl implements CategorieService {
    private CategorieRepository categorieRepository;
    private PhoneRepository phoneRepository;

    @Autowired
    public CategorieServiceImpl(CategorieRepository categorieRepository, PhoneRepository phoneRepository) {
        this.categorieRepository = categorieRepository;
        this.phoneRepository = phoneRepository;
    }



/*    public Brands getBrandNameById(int brandId) {
        Brands brand = null; categorieRepository.findById(brandId);
        return brand;
    }*/
    public List<Categories> findAll(){return null;};
    @Override
    public Categories findById(int theCategoryID){
            Optional<Categories> result = categorieRepository.findById(theCategoryID);
            if (result.isPresent()) {
                return result.get(); // Trả về đối tượng Phone nếu nó tồn tại
            } else {
                throw new RuntimeException("Không tìm thấy điện thoại với ID=" + theCategoryID);
            }
        };
    @Override
    public void save(Categories theCategories){
        categorieRepository.save(theCategories);
    }; // được sử dụng cả add new và update
    @Override
    public void deleteById(int theId){
        categorieRepository.deleteById(theId);
    };


}
