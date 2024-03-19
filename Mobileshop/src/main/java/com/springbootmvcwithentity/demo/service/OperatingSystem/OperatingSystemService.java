package com.springbootmvcwithentity.demo.service.OperatingSystem;

import com.springbootmvcwithentity.demo.entity.extand.OperatingSystem;
import com.springbootmvcwithentity.demo.dao.extand.OperatingSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperatingSystemService {
    private final OperatingSystemRepository operatingSystemRepository;

    @Autowired
    public OperatingSystemService(OperatingSystemRepository operatingSystemRepository) {
        this.operatingSystemRepository = operatingSystemRepository;
    }

    public List<OperatingSystem> findAll() {
        return operatingSystemRepository.findAll();
    }

    public OperatingSystem findById(int id) {
        Optional<OperatingSystem> result = operatingSystemRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Không tìm thấy hệ điều hành với ID=" + id);
        }
    }

    public void save(OperatingSystem operatingSystem) {
        operatingSystemRepository.save(operatingSystem);
    }

    public void deleteById(int id) {
        operatingSystemRepository.deleteById(id);
    }
}
