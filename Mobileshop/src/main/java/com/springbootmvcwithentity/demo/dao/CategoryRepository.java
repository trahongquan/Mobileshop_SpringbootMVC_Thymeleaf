package com.springbootmvcwithentity.demo.dao;

import com.springbootmvcwithentity.demo.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories(basePackages = "com.springbootmvcwithentity.demo.dao")
public interface CategoryRepository extends JpaRepository<Categories, Integer> {

}
