package com.springbootmvcwithentity.demo.dao.extand;

import com.springbootmvcwithentity.demo.entity.extand.RAM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RAMRepository extends JpaRepository<RAM, Integer> {
    // Các phương thức truy vấn dữ liệu khác (nếu cần)
}
