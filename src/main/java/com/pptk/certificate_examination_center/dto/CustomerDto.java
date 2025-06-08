package com.pptk.certificate_examination_center.dto;

import com.pptk.certificate_examination_center.entity.Customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link Customer}
 */
public class CustomerDto implements Serializable {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final String citizen_id;
    private final String customer_type;
    private final LocalDate dob;


    public CustomerDto(String name, String email, String phoneNumber, String address, String citizen_id, String customer_type, LocalDate dob) {
        this.name = name;
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto entity = (CustomerDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.phoneNumber, entity.phoneNumber) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.citizen_id, entity.citizen_id) &&
                Objects.equals(this.customer_type, entity.customer_type) &&
                Objects.equals(this.dob, entity.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber, address, citizen_id, customer_type, dob);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "email = " + email + ", " +
                "phoneNumber = " + phoneNumber + ", " +
                "address = " + address + ", " +
                "citizen_id = " + citizen_id + ", " +
                "customer_type = " + customer_type + ", " +
                "dob = " + dob + ")";
    }
}