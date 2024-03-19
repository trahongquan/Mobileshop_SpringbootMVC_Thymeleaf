package com.springbootmvcwithentity.demo.service.Model;

import com.springbootmvcwithentity.demo.entity.extand.Models;
import com.springbootmvcwithentity.demo.dao.extand.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Models> findAll() {
        return modelRepository.findAll();
    }

    public Models findById(int id) {
        Optional<Models> result = modelRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Không tìm thấy mô hình với ID=" + id);
        }
    }

    public void save(Models model) {
        modelRepository.save(model);
    }

    public void deleteById(int id) {
        modelRepository.deleteById(id);
    }
}
