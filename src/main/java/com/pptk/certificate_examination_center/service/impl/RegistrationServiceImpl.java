package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dto.CandidateDto;
import com.pptk.certificate_examination_center.dto.CustomerDto;
import com.pptk.certificate_examination_center.dto.IndividualRegisterDto;
import com.pptk.certificate_examination_center.entity.Candidate;
import com.pptk.certificate_examination_center.entity.RegistrationForm;
import com.pptk.certificate_examination_center.entity.Schedule;
import com.pptk.certificate_examination_center.mapper.CandidateMapper;
import com.pptk.certificate_examination_center.repository.RegistrationFormRepository;
import com.pptk.certificate_examination_center.service.CandidateService;
import com.pptk.certificate_examination_center.service.CustomerService;
import com.pptk.certificate_examination_center.service.RegistrationFormService;
import com.pptk.certificate_examination_center.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistrationServiceImpl {
    @Autowired
    private RegistrationFormService registrationFormService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ScheduleService scheduleService;

    public RegistrationForm saveIndividualRegistration(IndividualRegisterDto individualRegisterDto) {
        Long employee_id = getCurrentUserId(); // Assuming a static employee ID for now, this should be replaced with actual logic to get the logged-in employee's ID
        CustomerDto customer = individualRegisterDto.getCustomer();
        CandidateDto candidate = individualRegisterDto.getCandidate();
        List<Schedule> schedules = individualRegisterDto.getSchedules();

        customerService.createCustomer(customer);
        Long customer_id = customerService.getCustomerIdByEmail(customer.getEmail());

        registrationFormService.saveRegistrationForm(employee_id, customer_id, LocalDate.now());

        Long registration_form_id = registrationFormService.getLastInsertedId();

        Candidate candidateEntity = CandidateMapper.toEntity(candidate);
        candidateEntity.setId_registration_form(registration_form_id);

//        CandidateDto savedCandidate = candidateService.createCandidate(CandidateMapper.toDto(candidateEntity));
          candidateService.createCandidate(CandidateMapper.toDto(candidateEntity));

        schedules.forEach(schedule -> {
            scheduleService.updateNumberOfCandidatesForIndividual(schedule.getId());
            registrationFormService.updateRegisterDetails(registration_form_id, schedule.getId());
        });

        return registrationFormService.getFormById(registration_form_id);
    }

    public RegistrationForm saveOrganizationRegistration() {
        return null;
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return userDetails.getId();
        }
        throw new RuntimeException("User not authenticated");
    }
}
