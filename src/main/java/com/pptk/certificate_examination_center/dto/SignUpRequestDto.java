package com.pptk.certificate_examination_center.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

public class SignUpRequestDto {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Size(max = 20, message = "Username must not exceed 20 characters")
    private String username;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must have at least 8 characters")
    @Size(max = 20, message = "Password can have at most 20 characters")
    private String password;

    private Set<String> roles;

    private String phone_number;
    private String full_name;
    private String gender;
    private LocalDate dob;
    private String address;

    public SignUpRequestDto(String username, String email, String password, Set<String> roles, String phone_number, String full_name, String gender, LocalDate dob, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
    }

    public SignUpRequestDto(String username, String email, String password, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public SignUpRequestDto(){}

    public @NotBlank(message = "Username is required") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") @Size(max = 20, message = "Username must not exceed 20 characters") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is required") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") @Size(max = 20, message = "Username must not exceed 20 characters") String username) {
        this.username = username;
    }

    public @Email(message = "Email is not valid") @NotBlank(message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email is not valid") @NotBlank(message = "Email is required") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must have at least 8 characters") @Size(max = 20, message = "Password can have at most 20 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must have at least 8 characters") @Size(max = 20, message = "Password can have at most 20 characters") String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
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
}
