package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.Employee;
import jakarta.persistence.PrePersist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Optional<Employee> findByEmail(String email);
    public boolean existsByName(String name);
    public boolean existsByEmail(String email);
}
