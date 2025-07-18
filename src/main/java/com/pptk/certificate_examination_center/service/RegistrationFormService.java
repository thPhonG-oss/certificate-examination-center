package com.pptk.certificate_examination_center.service;


import com.pptk.certificate_examination_center.dto.UpdateRegistrationFormDto;
import com.pptk.certificate_examination_center.entity.RegistrationForm;

import java.time.LocalDate;

public interface RegistrationFormService {
    boolean updateRegistrationForm(UpdateRegistrationFormDto dto);
    public void saveRegistrationForm(Long employeeId, Long customerId, LocalDate registration_date, Long schedule_id);
    public Long getLastInsertedId();
    public void updateRegisterDetails(Long registration_form_id, Long schedule_id);
    public RegistrationForm getFormById(Long registration_form_id);
}
