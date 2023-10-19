package com.springbootmvcwithentity.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbootmvcwithentity.demo.entity.Employees;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {
        public List<Employees> findAllByOrderByLastNameAsc();
        public List<Employees> findByFirstNameContainsOrLastNameContainsIgnoreCase(String name, String lName);
        public Employees findByEmail(String email);
}
