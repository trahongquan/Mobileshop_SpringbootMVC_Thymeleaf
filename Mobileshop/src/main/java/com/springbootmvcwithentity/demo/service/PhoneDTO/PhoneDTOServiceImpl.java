//package com.springbootmvcwithentity.demo.service.PhoneDTO;
//
//import com.springbootmvcwithentity.demo.dao.PhoneDTORepository;
//import com.springbootmvcwithentity.demo.dao.PhoneRepository;
//import com.springbootmvcwithentity.demo.dto.PhoneDTO;
//import com.springbootmvcwithentity.demo.entity.Phones;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PhoneDTOServiceImpl implements PhoneDTOService {
//    private PhoneDTORepository phoneDTORepository;
//
//    @Autowired
//    public PhoneDTOServiceImpl(PhoneDTORepository phoneDTORepository) {
//        this.phoneDTORepository = phoneDTORepository;
//    }
//
////    @Override
////    public List<Phones> findAll() {
////        return phoneDTORepository.findAll();
////    }
//
//    @Override
//    public PhoneDTO findById(int phoneId) {
//        Optional<PhoneDTO> result = phoneDTORepository.findById(phoneId);
//        if (result.isPresent()) {
//            return result.get(); // Trả về đối tượng Phone nếu nó tồn tại
//        } else {
//            throw new RuntimeException("Không tìm thấy điện thoại với ID=" + phoneId);
//        }
//    }
//
////    @Override
////    public void save(Phones phone) {
////
////        phoneDTORepository.save(phone);
////
////    }
//
////    @Override
////    public void deleteById(int phoneId) {
////        phoneDTORepository.deleteById(phoneId);
////    }
//
//}
