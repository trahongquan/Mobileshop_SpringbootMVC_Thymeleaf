package com.springbootmvcwithentity.demo.dao.extand;

import com.springbootmvcwithentity.demo.entity.extand.StorageCapacity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageCapacityRepository extends JpaRepository<StorageCapacity, Integer> {
    // Các phương thức truy vấn dữ liệu khác (nếu cần)
}