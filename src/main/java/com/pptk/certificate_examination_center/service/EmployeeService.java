package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.entity.Employee;

public interface EmployeeService {
     public boolean existsByName(String name);
     public boolean existsByEmail(String email);
     void saveEmployee(Employee employee);
}
