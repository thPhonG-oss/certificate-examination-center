package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.dto.CustomerDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(Long id);
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    void deleteCustomer(Long id);
    public Long getCustomerIdByEmail(@Param("email") String email);
}
