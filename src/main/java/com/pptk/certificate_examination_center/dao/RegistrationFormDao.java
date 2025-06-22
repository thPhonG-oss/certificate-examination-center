package com.pptk.certificate_examination_center.dao;

import com.pptk.certificate_examination_center.dto.UpdateRegistrationFormDto;

public interface RegistrationFormDao {
    boolean updateRegistrationForm(UpdateRegistrationFormDto dto);
}
