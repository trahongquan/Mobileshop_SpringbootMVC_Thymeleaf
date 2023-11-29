package com.springbootmvcwithentity.demo.service.PrdRevService.brand;

import com.springbootmvcwithentity.demo.dao.BrandRepository;
import com.springbootmvcwithentity.demo.dao.PrdRevRepository;
import com.springbootmvcwithentity.demo.entity.Brands;
import com.springbootmvcwithentity.demo.entity.productreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrdRevServiceImpl implements PrdRevService {
    private PrdRevRepository prdRevRepository;

    @Autowired
    public PrdRevServiceImpl(PrdRevRepository prdRevRepository) {
        this.prdRevRepository = prdRevRepository;
    }

    @Override
    public List<productreview> findAll(){return prdRevRepository.findAll();};
    @Override
    public productreview findById(int Id){
        Optional<productreview> result = prdRevRepository.findById(Id);
        if (result.isPresent()) {
            return result.get(); // Trả về đối tượng Phone nếu nó tồn tại
        } else {
            throw new RuntimeException("Không tìm thấy điện thoại với ID=" + Id);
        }
    };
    @Override
    public void save(productreview theBrands){prdRevRepository.save(theBrands);}; // được sử dụng cả add new và update
    @Override
    public void deleteById(int theId){prdRevRepository.deleteById(theId);};


}
