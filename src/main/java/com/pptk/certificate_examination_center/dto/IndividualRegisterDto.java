package com.pptk.certificate_examination_center.dto;

import com.pptk.certificate_examination_center.entity.RegistrationForm;
import com.pptk.certificate_examination_center.entity.Schedule;

public class IndividualRegisterDto {
    private CustomerDto customer;
    private CandidateDto candidate;
    Schedule schedule;
    private RegistrationForm registrationForm;

    public IndividualRegisterDto(CustomerDto customer, CandidateDto candidate, Schedule schedules, RegistrationForm registrationForm) {
        this.customer = customer;
        this.candidate = candidate;
        this.schedule = schedules;
        this.registrationForm = registrationForm;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public CandidateDto getCandidate() {
        return candidate;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public RegistrationForm getRegistrationForm() {
        return registrationForm;
    }
}
