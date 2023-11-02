package com.springbootmvcwithentity.demo.service.categorie;

import com.springbootmvcwithentity.demo.dao.CategoryRepository;
import com.springbootmvcwithentity.demo.entity.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Categories> findAll(){return categoryRepository.findAll();}
    @Override
    public Categories findById(int theCategoryID){
            Optional<Categories> result = categoryRepository.findById(theCategoryID);
            if (result.isPresent()) {
                return result.get(); // Trả về đối tượng Phone nếu nó tồn tại
            } else {
                throw new RuntimeException("Không tìm thấy điện thoại với ID=" + theCategoryID);
            }
        }
    @Override
    public void save(Categories theCategories){
        categoryRepository.save(theCategories);
    }; // được sử dụng cả add new và update
    @Override
    public void deleteById(int theId){
        categoryRepository.deleteById(theId);
    };


}
