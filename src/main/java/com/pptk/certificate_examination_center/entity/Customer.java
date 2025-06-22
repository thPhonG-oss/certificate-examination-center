package com.pptk.certificate_examination_center.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "khach_hang")
public class Customer {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_khach_hang")
    private Long id;

    @Column(name = "ho_ten")
    private String name;
    @Column(name = "ten_co_quan")
    private String organization_name;
    @Column(name = "email")
    private String email;
    @Column(name = "sdt")
    private String phone;
    @Column(name = "dia_chi")
    private String address;
    @Column(name = "cccd")
    private String citizen_id;
    @Column(name = "loai_khach_hang")
    private String type;
    @Column(name = "ngay_sinh")
    private LocalDate dob;

    public Customer(){}
    public Customer(String name, String organization_name, String email, String phone, String address, String citizen_id, String type, LocalDate dob) {
        this.name = name;
        this.organization_name = organization_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.citizen_id = citizen_id;
        this.type = type;
        this.dob = dob;
    }

    public Customer(Long id, String name, String organization_name, String email, String phone, String address, String citizen_id, String type, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.organization_name = organization_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.citizen_id = citizen_id;
        this.type = type;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCitizen_id() {
        return citizen_id;
    }

    public void setCitizen_id(String citizen_id) {
        this.citizen_id = citizen_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", organization_name='" + organization_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", citizen_id='" + citizen_id + '\'' +
                ", type='" + type + '\'' +
                ", dob=" + dob +
                '}';
    }
}