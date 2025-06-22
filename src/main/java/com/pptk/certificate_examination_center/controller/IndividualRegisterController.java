package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.dto.ApiResponseDto;
import com.pptk.certificate_examination_center.dto.IndividualRegisterDto;
import com.pptk.certificate_examination_center.entity.ResponseStatus;
import com.pptk.certificate_examination_center.service.ScheduleService;
import com.pptk.certificate_examination_center.service.impl.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("registrations/individual") // localhost:8080/registrations
public class IndividualRegisterController {
    @Autowired
    RegistrationServiceImpl registrationService;

    @PostMapping("/submit")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ApiResponseDto<Object>> submitRegistration(@RequestBody IndividualRegisterDto individualRegisterDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponseDto<>(
                                String.valueOf(ResponseStatus.SUCCESS),
                                "Save form successfully",
                                registrationService.saveIndividualRegistration(individualRegisterDto)
                        )
                );
    }
}
