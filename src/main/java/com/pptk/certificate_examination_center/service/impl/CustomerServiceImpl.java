package com.pptk.certificate_examination_center.service.impl;

//import com.pptk.certificate_examination_center.dao.CustomerDao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pptk.certificate_examination_center.dto.CustomerDto;
import com.pptk.certificate_examination_center.entity.Customer;
import com.pptk.certificate_examination_center.exception.CustomerNotFoundException;
import com.pptk.certificate_examination_center.mapper.CustomerMapper;
import com.pptk.certificate_examination_center.repository.CustomerRepository;
import com.pptk.certificate_examination_center.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        customerRepository.findAll().forEach(customer -> {
            CustomerDto customerDto = CustomerMapper.toDto(customer);
            customerDtos.add(customerDto);
        });
        return customerDtos;
    }
    
    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));

        return CustomerMapper.toDto(customer);
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toDto(savedCustomer);
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return CustomerMapper.toDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
