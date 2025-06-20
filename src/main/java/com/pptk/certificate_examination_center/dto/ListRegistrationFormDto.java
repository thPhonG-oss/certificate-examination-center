package com.pptk.certificate_examination_center.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ListRegistrationFormDto implements Serializable {

    private int status;
    private String customerID;
    private String customerName;
    private LocalDate registerDate;
    private String candidateID;
    private String candidateName;
    private String examSchedule;
    private String registrationFormId;
    private String certificateName;

    public ListRegistrationFormDto() {
    }

    public ListRegistrationFormDto(int status, String customerID, String customerName,
                                   LocalDate registerDate, String candidateID, String candidateName,
                                   String examSchedule, String registrationFormId, String certificateName) {
        this.status = status;
        this.customerID = customerID;
        this.customerName = customerName;
        this.registerDate = registerDate;
        this.candidateID = candidateID;
        this.candidateName = candidateName;
        this.examSchedule = examSchedule;
        this.registrationFormId = registrationFormId;
        this.certificateName = certificateName;
    }

    // Getters & Setters

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public String getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(String candidateID) {
        this.candidateID = candidateID;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getExamSchedule() {
        return examSchedule;
    }

    public void setExamSchedule(String examSchedule) {
        this.examSchedule = examSchedule;
    }

    public String getRegistrationFormId() {
        return registrationFormId;
    }

    public void setRegistrationFormId(String registrationFormId) {
        this.registrationFormId = registrationFormId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }
}
