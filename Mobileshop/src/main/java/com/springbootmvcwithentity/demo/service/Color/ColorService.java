package com.springbootmvcwithentity.demo.service.Color;

import com.springbootmvcwithentity.demo.entity.extand.Color;
import com.springbootmvcwithentity.demo.dao.extand.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    private final ColorRepository colorRepository;

    @Autowired
    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    public Color findById(int id) {
        Optional<Color> result = colorRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Không tìm thấy màu sắc với ID=" + id);
        }
    }

    public void save(Color color) {
        colorRepository.save(color);
    }

    public void deleteById(int id) {
        colorRepository.deleteById(id);
    }
}
