package com.pptk.certificate_examination_center.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customer_name;
    @Column(name = "customer_email")
    private String customer_email;
    @Column(name = "customer_phone")
    private String customer_phone;
    @Column(name = "customer_address")
    private String customer_address;
    @Column(name = "customer_citizen_id")
    private String customer_citizen_id;
    @Column(name = "customer_type")
    private String customer_type;
    @Column(name = "customer_dob")
    private LocalDate customer_dob;

    public Customer(){}
    public Customer(String customer_name, String customer_email, String customer_phone, String customer_address, String customer_citizen_id, String customer_type, LocalDate customer_dob) {
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_phone = customer_phone;
        this.customer_address = customer_address;
        this.customer_citizen_id = customer_citizen_id;
        this.customer_type = customer_type;
        this.customer_dob = customer_dob;
    }
    public Customer(Long id, String customer_name, String customer_email, String customer_phone, String customer_address, String customer_citizen_id, String customer_type, LocalDate customer_dob) {
        this.id = id;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_phone = customer_phone;
        this.customer_address = customer_address;
        this.customer_citizen_id = customer_citizen_id;
        this.customer_type = customer_type;
        this.customer_dob = customer_dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String name) {
        this.customer_name = name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String email) {
        this.customer_email = email;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String phoneNumber) {
        this.customer_phone = phoneNumber;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String address) {
        this.customer_address = address;
    }

    public String getCustomer_citizen_id() {
        return customer_citizen_id;
    }

    public void setCustomer_citizen_id(String citizen_id) {
        this.customer_citizen_id = citizen_id;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public LocalDate getCustomer_dob() {
        return customer_dob;
    }

    public void setCustomer_dob(LocalDate dob) {
        this.customer_dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + customer_name + '\'' +
                ", email='" + customer_email + '\'' +
                ", phoneNumber='" + customer_phone + '\'' +
                ", address='" + customer_address + '\'' +
                ", citizen_id='" + customer_citizen_id + '\'' +
                ", customer_type='" + customer_type + '\'' +
                ", dob=" + customer_dob +
                '}';
    }


}
