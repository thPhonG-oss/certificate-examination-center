package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.dto.IndividualRegisterDto;
import com.pptk.certificate_examination_center.service.ScheduleService;
import com.pptk.certificate_examination_center.service.impl.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registrations/individual") // localhost:8080/registrations
public class IndividualRegisterController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    RegistrationServiceImpl registrationService;



    @GetMapping("/form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("schedules", scheduleService.getAllSchedulesWithCertificate());
        return "individual-form";
    }

    @PostMapping("/submit")
    public ResponseEntity<Object> submitRegistration(@RequestBody IndividualRegisterDto individualRegisterDto) {
        registrationService.saveIndividualRegistration(individualRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Registration successful");
    }
}
