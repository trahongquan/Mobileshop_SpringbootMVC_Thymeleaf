package com.springbootmvcwithentity.demo.dao;

import com.springbootmvcwithentity.demo.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
        public Authority findByUsername(String user);
        public void deleteByUsername(String username);
}
