package com.springbootmvcwithentity.demo.service.emp;

import java.util.List;
import  com.springbootmvcwithentity.demo.entity.Employees;

public interface EmployeeService {
    public List<Employees> findAll();
    public Employees findById(int theId);
    public void save (Employees theEmployee); // được sử dụng cả add new và update
    public void deleteById(int theId);
    public List<Employees> searchBy(String theName);
}
