package com.pptk.certificate_examination_center.mapper;

import com.pptk.certificate_examination_center.dto.CustomerDto;
import com.pptk.certificate_examination_center.entity.Customer;

public class CustomerMapper {
    public static CustomerDto toDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDto dto = new CustomerDto(
                customer.getName(),
                customer.getOrganization_name(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getCitizen_id(),
                customer.getType(),
                customer.getDob()
        );
        return dto;
    }

    public static Customer toEntity(CustomerDto dto) {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setOrganization_name(dto.getOrganization());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhoneNumber());
        customer.setAddress(dto.getAddress());
        customer.setCitizen_id(dto.getCitizen_id());
        customer.setType(dto.getCustomer_type());
        customer.setDob(dto.getDob());
        return customer;
    }
}
