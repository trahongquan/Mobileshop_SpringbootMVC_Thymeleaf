package com.springbootmvcwithentity.demo.dao.extand;

import com.springbootmvcwithentity.demo.entity.extand.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    // Các phương thức truy vấn dữ liệu khác (nếu cần)
}
