package com.springbootmvcwithentity.demo.dao.extand;

import com.springbootmvcwithentity.demo.entity.extand.Models;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Models, Integer> {
    // Các phương thức truy vấn dữ liệu khác (nếu cần)
}
