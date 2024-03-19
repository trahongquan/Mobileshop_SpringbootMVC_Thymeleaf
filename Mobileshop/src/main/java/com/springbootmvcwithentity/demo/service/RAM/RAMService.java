package com.springbootmvcwithentity.demo.service.RAM;

import com.springbootmvcwithentity.demo.entity.extand.RAM;
import com.springbootmvcwithentity.demo.dao.extand.RAMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RAMService {
    private final RAMRepository ramRepository;

    @Autowired
    public RAMService(RAMRepository ramRepository) {
        this.ramRepository = ramRepository;
    }

    public List<RAM> findAll() {
        return ramRepository.findAll();
    }

    public RAM findById(int id) {
        Optional<RAM> result = ramRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Không tìm thấy RAM với ID=" + id);
        }
    }

    public void save(RAM ram) {
        ramRepository.save(ram);
    }

    public void deleteById(int id) {
        ramRepository.deleteById(id);
    }
}