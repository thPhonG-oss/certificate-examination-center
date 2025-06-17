package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.Employee;
import jakarta.persistence.PrePersist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Additional query methods can be defined here if needed
}
