package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
}
