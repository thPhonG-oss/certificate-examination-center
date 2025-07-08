package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dao.RegistrationFormDao;
import com.pptk.certificate_examination_center.dto.UpdateRegistrationFormDto;
import com.pptk.certificate_examination_center.entity.RegistrationForm;
import com.pptk.certificate_examination_center.repository.RegistrationFormRepository;
import com.pptk.certificate_examination_center.service.RegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegistrationFormServiceImpl implements RegistrationFormService {
    @Autowired
    private RegistrationFormDao registrationFormDao;
    @Autowired
    private RegistrationFormRepository registrationFormRepository;

    @Override
    public boolean updateRegistrationForm(UpdateRegistrationFormDto dto) {
        return registrationFormDao.updateRegistrationForm(dto);
    }

    public RegistrationFormServiceImpl() {
        super();
    }

    @Override
    public void saveRegistrationForm(Long employeeId, Long customerId, LocalDate registration_date, Long schedule_id) {
            registrationFormRepository.saveRegistrationForm(employeeId, customerId, registration_date, schedule_id);
    }

    @Override
    public Long getLastInsertedId() {
        return registrationFormRepository.getLastInsertedId();
    }

    @Override
    public void updateRegisterDetails(Long registration_form_id, Long schedule_id) {
         registrationFormRepository.updateRegisterDetails(registration_form_id, schedule_id);
    }

    @Override
    public RegistrationForm getFormById(Long registration_form_id) {
        return registrationFormRepository.getFormById(registration_form_id);
    }


}
