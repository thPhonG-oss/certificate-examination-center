package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT kh.id_khach_hang FROM khach_hang kh WHERE kh.email = :email", nativeQuery = true)
    public Long getCustomerIdByEmail(@Param("email") String email);
}
