package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(Long id);
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    void deleteCustomer(Long id);
    CustomerDto getCustomerByRegistrationID(Integer registrationId);
    public Long getCustomerIdByEmail(String email);
    public CustomerDto getCustomerByCitizenId(String citizenId);
    public void updateCustomer(CustomerDto customerDto);
}
