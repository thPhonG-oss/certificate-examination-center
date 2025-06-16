package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional query methods can be defined here if needed
}
