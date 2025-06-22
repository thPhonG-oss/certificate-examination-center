package com.pptk.certificate_examination_center.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "thi_sinh")
public class Candidate {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_thi_sinh")
    private Long id;

    @Column(name ="ho_ten")
    private String name;

    @Column(name = "gioi_tinh")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "sdt")
    private String phoneNumber;

    @Column(name = "ngay_sinh")
    private LocalDate dob;

    @Column(name = "dia_chi")
    private String address;

    @Column(name = "cccd")
    private String citizen_id;

    @Column(name = "id_phieu_dang_ky")
    private Long id_registration_form;

    public Candidate() {
    }

    public Candidate(String name, String gender, String email, String phoneNumber, LocalDate dob, String address, String citizen_id, Long id_registration_form) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.address = address;
        this.citizen_id = citizen_id;
        this.id_registration_form = id_registration_form;
    }

    public Candidate(Long id, String name, String gender, String email, String phoneNumber, LocalDate dob, String address, String citizen_id, Long id_registration_form) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.address = address;
        this.citizen_id = citizen_id;
        this.id_registration_form = id_registration_form;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public Long getId_registration_form() {
        return id_registration_form;
    }

    public void setId_registration_form(Long id_registration_form) {
        this.id_registration_form = id_registration_form;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", citizen_id='" + citizen_id + '\'' +
                ", id_registration_form=" + id_registration_form +
                '}';
    }
}