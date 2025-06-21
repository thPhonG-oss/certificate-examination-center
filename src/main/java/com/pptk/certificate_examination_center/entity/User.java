package com.pptk.certificate_examination_center.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "[user]")
public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone_number;
    private String full_name;
    private String gender;
    private LocalDate dob;
    private String address;
    private Boolean enabled;


    public User() {
    }

    public User(String username, String password, String email, String phone_number, String full_name, String gender, LocalDate dob, String address, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.enabled = enabled;
    }

    public User(Long id, String username, String password, String email, String phone_number, String full_name, String gender, LocalDate dob, String address, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
