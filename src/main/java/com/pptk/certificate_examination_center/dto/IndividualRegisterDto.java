package com.pptk.certificate_examination_center.dto;

import com.pptk.certificate_examination_center.entity.RegistrationForm;
import com.pptk.certificate_examination_center.entity.Schedule;

import java.util.List;

public class IndividualRegisterDto {
    private CustomerDto customer;
    private CandidateDto candidate;
    List<Schedule> schedules;
    private RegistrationForm registrationForm;

    public IndividualRegisterDto(CustomerDto customer, CandidateDto candidate, List<Schedule> schedules, RegistrationForm registrationForm) {
        this.customer = customer;
        this.candidate = candidate;
        this.schedules = schedules;
        this.registrationForm = registrationForm;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public CandidateDto getCandidate() {
        return candidate;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public RegistrationForm getRegistrationForm() {
        return registrationForm;
    }
}
