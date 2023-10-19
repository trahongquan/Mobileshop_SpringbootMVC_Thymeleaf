package com.springbootmvcwithentity.demo.service.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootmvcwithentity.demo.entity.Employees;
import com.springbootmvcwithentity.demo.dao.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employees> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employees findById(int theId) {
        Optional<Employees> result = employeeRepository.findById(theId);
        if (result.isPresent()) {
            return result.get(); // Trả về đối tượng Employee nếu nó tồn tại
        } else {
            throw new RuntimeException("Không tìm thấy nhân viên với ID=" + theId);
        }
    }

    @Override
    public void save(Employees theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public List<Employees> searchBy(String theName) {
        List<Employees> results = null;
        if (theName != null && (theName.trim().length() > 0)) {
            results = employeeRepository.findByFirstNameContainsOrLastNameContainsIgnoreCase(theName, theName);
        } else {
            results = findAll();
        }
        return results;
    }

}
