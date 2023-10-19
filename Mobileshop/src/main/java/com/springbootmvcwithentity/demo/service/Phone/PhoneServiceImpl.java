package com.springbootmvcwithentity.demo.service.Phone;

import com.springbootmvcwithentity.demo.dao.PhoneRepository;
import com.springbootmvcwithentity.demo.entity.Phones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
    private PhoneRepository phoneRepository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<Phones> findAll() {
        return phoneRepository.findAll();
    }

    @Override
    public Phones findById(int phoneId) {
        Optional<Phones> result = phoneRepository.findById(phoneId);
        if (result.isPresent()) {
            return result.get(); // Trả về đối tượng Phone nếu nó tồn tại
        } else {
            Phones p = new Phones();
            p.setPhoneId(0);
            return p;
//            throw new RuntimeException("Không tìm thấy điện thoại với ID=" + phoneId);
        }
    }

    @Override
    public void save(Phones phone) {

        phoneRepository.save(phone);

    }

    @Override
    public void deleteById(int phoneId) {
        phoneRepository.deleteById(phoneId);
    }

}
