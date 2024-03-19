package com.springbootmvcwithentity.demo.dao.extand;

import com.springbootmvcwithentity.demo.entity.extand.OperatingSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatingSystemRepository extends JpaRepository<OperatingSystem, Integer> {
    // Các phương thức truy vấn dữ liệu khác (nếu cần)
}