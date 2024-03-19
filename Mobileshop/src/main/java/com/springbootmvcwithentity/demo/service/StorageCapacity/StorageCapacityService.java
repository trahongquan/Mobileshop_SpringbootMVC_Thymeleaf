package com.springbootmvcwithentity.demo.service.StorageCapacity;

import com.springbootmvcwithentity.demo.entity.extand.StorageCapacity;
import com.springbootmvcwithentity.demo.dao.extand.StorageCapacityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageCapacityService {
    private final StorageCapacityRepository storageCapacityRepository;

    @Autowired
    public StorageCapacityService(StorageCapacityRepository storageCapacityRepository) {
        this.storageCapacityRepository = storageCapacityRepository;
    }

    public List<StorageCapacity> findAll() {
        return storageCapacityRepository.findAll();
    }

    public StorageCapacity findById(int id) {
        Optional<StorageCapacity> result = storageCapacityRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Không tìm thấy dung lượng lưu trữ với ID=" + id);
        }
    }

    public void save(StorageCapacity storageCapacity) {
        storageCapacityRepository.save(storageCapacity);
    }

    public void deleteById(int id) {
        storageCapacityRepository.deleteById(id);
    }
}
