package com.pptk.certificate_examination_center.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class CandidateDto implements Serializable {
    private Long registration_form_id;
    private String name;
    private String gender;
    private String email;
    private String phoneNumber;
    private LocalDate dob;
    private String address;
    private String citizen_id;
    private String imageUrl;

    public CandidateDto() {
    }

    public CandidateDto(Long registration_form_id, String name, String gender, String email, String phoneNumber, LocalDate dob, String address, String citizen_id, String imageUrl) {
        this.registration_form_id = registration_form_id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.address = address;
        this.citizen_id = citizen_id;
        this.imageUrl = imageUrl;
    }

    public Long getRegistration_form_id() {
        return registration_form_id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getCitizen_id() {
        return citizen_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
