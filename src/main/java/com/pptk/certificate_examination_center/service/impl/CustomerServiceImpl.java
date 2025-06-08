package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dao.CustomerDao;
import com.pptk.certificate_examination_center.dto.CustomerDto;
import com.pptk.certificate_examination_center.mapper.CustomerMapper;
import com.pptk.certificate_examination_center.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;


    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customers = new ArrayList<>();
        customerDao.selectAllCustomers().forEach(customer -> {
            customers.add(CustomerMapper.toDto(customer));
        });
        return customers;
    }
}
