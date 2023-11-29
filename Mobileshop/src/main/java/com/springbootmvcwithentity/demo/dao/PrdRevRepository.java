package com.springbootmvcwithentity.demo.dao;

import com.springbootmvcwithentity.demo.entity.productreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrdRevRepository extends JpaRepository<productreview, Integer> {
    List<productreview> findAllByPhoneID(int id);
}
