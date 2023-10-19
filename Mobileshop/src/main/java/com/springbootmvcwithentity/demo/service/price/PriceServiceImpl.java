//package com.springbootmvcwithentity.demo.service.price;
//
//import com.springbootmvcwithentity.demo.dao.PhoneRepository;
//import com.springbootmvcwithentity.demo.dao.PriceRepository;
//import com.springbootmvcwithentity.demo.entity.Phones;
//import com.springbootmvcwithentity.demo.entity.Price;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PriceServiceImpl implements PriceService {
//    private PriceRepository priceRepository;
//
//    @Autowired
//    public PriceServiceImpl(PriceRepository priceRepository) {
//        this.priceRepository = priceRepository;
//    }
//
//    @Override
//    public List<Price> findAll() {
//        return priceRepository.findAll();
//    }
//
//    @Override
//    public Price findById(int priceId) {
//        Optional<Price> result = priceRepository.findById(priceId);
//        if (result.isPresent()) {
//            return result.get(); // Trả về đối tượng Phone nếu nó tồn tại
//        } else {
//            throw new RuntimeException("Không tìm thấy điện thoại với ID=" + priceId);
//        }
//    }
//
//    @Override
//    public void save(Price price) {
//
//        priceRepository.save(price);
//
//    }
//
//    @Override
//    public void deleteById(int priceId) {
//        priceRepository.deleteById(priceId);
//    }
//
//}
