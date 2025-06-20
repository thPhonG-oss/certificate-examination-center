package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dao.RegistrationFormDao;
import com.pptk.certificate_examination_center.dto.UpdateRegistrationFormDto;
import com.pptk.certificate_examination_center.service.RegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationFormServiceImpl implements RegistrationFormService {

    @Autowired
    private RegistrationFormDao registrationFormDao;

    @Override
    public boolean updateRegistrationForm(UpdateRegistrationFormDto dto) {
        return registrationFormDao.updateRegistrationForm(dto);
    }
}
