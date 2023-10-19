package com.springbootmvcwithentity.demo.dao;

import com.springbootmvcwithentity.demo.entity.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories(basePackages = "com.springbootmvcwithentity.demo.dao")
public interface BrandRepository extends JpaRepository<Brands, Integer> {
    //public List<Phones> findAllByPhoneId();
}
