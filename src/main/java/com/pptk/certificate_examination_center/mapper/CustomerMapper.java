package com.pptk.certificate_examination_center.mapper;

import com.pptk.certificate_examination_center.dto.CustomerDto;
import com.pptk.certificate_examination_center.entity.Customer;

public class CustomerMapper {
    public static CustomerDto toDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDto dto = new CustomerDto(customer.getCustomer_name(),
                customer.getCustomer_email(),
                customer.getCustomer_phone(),
                customer.getCustomer_address(),
                customer.getCustomer_citizen_id(),
                customer.getCustomer_type(),
                customer.getCustomer_dob());
        return dto;
    }

    public static Customer toEntity(CustomerDto dto) {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setCustomer_name(dto.getName());
        customer.setCustomer_email(dto.getEmail());
        customer.setCustomer_phone(dto.getPhoneNumber());
        customer.setCustomer_address(dto.getAddress());
        customer.setCustomer_citizen_id(dto.getCitizen_id());
        customer.setCustomer_type(dto.getCustomer_type());
        customer.setCustomer_dob(dto.getDob());
        return customer;
    }
}
