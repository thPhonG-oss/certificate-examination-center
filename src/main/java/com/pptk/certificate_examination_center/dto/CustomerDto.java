package com.pptk.certificate_examination_center.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.pptk.certificate_examination_center.entity.Customer;
//import java.util.Objects;

/**
 * DTO for {@link Customer}
 */
public class CustomerDto implements Serializable {
    private final String name;
    private final String organization; // Assuming this is the organization name
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final String citizen_id;
    private final String customer_type;
    private final LocalDate dob;

    public CustomerDto(String name, String organization, String email, String phoneNumber, String address, String citizen_id, String customer_type, LocalDate dob) {
        this.name = name;
        this.organization = organization;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.citizen_id = citizen_id;
        this.customer_type = customer_type;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public String getOrganization() {
        return organization;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCitizen_id() {
        return citizen_id;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public LocalDate getDob() {
        return dob;
    }
}