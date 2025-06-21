package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.entity.Employee;
import com.pptk.certificate_examination_center.repository.EmployeeRepository;
import com.pptk.certificate_examination_center.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean existsByName(String name) {
        return employeeRepository.existsByName(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
