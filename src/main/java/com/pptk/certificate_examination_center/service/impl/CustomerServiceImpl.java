package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dao.CustomerDao;
import com.pptk.certificate_examination_center.dto.CustomerDto;
import com.pptk.certificate_examination_center.entity.Customer;
import com.pptk.certificate_examination_center.exception.CustomerNotFoundException;
import com.pptk.certificate_examination_center.mapper.CustomerMapper;
import com.pptk.certificate_examination_center.repository.CustomerRepository;
import com.pptk.certificate_examination_center.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerDao customerDao;

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

    @Override
    public CustomerDto getCustomerByRegistrationID(Integer registrationId) {
        Customer customer = customerDao.selectCustomerByRegistrationId(registrationId);
        return CustomerMapper.toDto(customer);
    }

    @Override
    public Long getCustomerIdByEmail(String email) {
        Long customerId = customerRepository.getCustomerIdByEmail(email);
        if (customerId == null) {
            throw new CustomerNotFoundException("Customer not found with email: " + email);
        }
        return customerId;
    }

    @Override
    public CustomerDto getCustomerByCitizenId(String citizenId) {
        Customer customer = customerRepository.findCustomerByCitizen_id(citizenId);
        if (customer == null) {
            return null;
        }
        return CustomerMapper.toDto(customer);
    }

    @Transactional
    @Modifying
    @Override
    public void updateCustomer(CustomerDto customerDto, Long customerId) {
        customerRepository.updateCustomer(
                customerId,
                customerDto.getName(),
                customerDto.getOrganization(),
                customerDto.getPhoneNumber(),
                customerDto.getEmail(),
                customerDto.getAddress(),
                customerDto.getCitizen_id(),
                customerDto.getDob()
        );
    }
}