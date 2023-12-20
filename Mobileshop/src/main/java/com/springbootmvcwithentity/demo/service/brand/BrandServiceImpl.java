package com.springbootmvcwithentity.demo.service.brand;

import com.springbootmvcwithentity.demo.dao.BrandRepository;
import com.springbootmvcwithentity.demo.dao.PhoneRepository;
import com.springbootmvcwithentity.demo.entity.Brands;
import com.springbootmvcwithentity.demo.entity.Phones;
import com.springbootmvcwithentity.demo.service.Phone.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService{
    private BrandRepository brandRepository;
    private PhoneRepository phoneRepository;

    public BrandServiceImpl(BrandRepository brandRepository, PhoneRepository phoneRepository) {
        this.brandRepository = brandRepository;
        this.phoneRepository = phoneRepository;
    }

    @Autowired


    @Override
    public List<Brands> findAll(){return brandRepository.findAll();};
    @Override
    public Brands findById(int brandId){
        Optional<Brands> result = brandRepository.findById(brandId);
        if (result.isPresent()) {
            return result.get(); // Trả về đối tượng Phone nếu nó tồn tại
        } else {
            throw new RuntimeException("Không tìm thấy điện thoại với ID=" + brandId);
        }
    };
    @Override
    public void save(Brands theBrands){brandRepository.save(theBrands);}; // được sử dụng cả add new và update
    @Override
    public void deleteById(int theId){brandRepository.deleteById(theId);};
    @Override
    public boolean isBrandInUse(int brandID) {
        List<Phones> phones = phoneRepository.findAllByBrandId(brandID);
        if (!phones.isEmpty()) {
            return true;
        }else {return false;}
    }

}
