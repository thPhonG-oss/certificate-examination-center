package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.dto.UpdateRegistrationFormDto;
import com.pptk.certificate_examination_center.service.RegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration-form")
public class RegistrationFormController {

    @Autowired
    private RegistrationFormService registrationFormService;

    @PatchMapping("/update")
    public ResponseEntity<String> updateRegistrationForm(@RequestBody UpdateRegistrationFormDto updateDto) {
        // ✅ Log đầu vào
        System.out.println("Payload nhận được: " + updateDto.getFormId() + ", " + updateDto.getScheduleId() + ", " + updateDto.getEvidenceCode());

        registrationFormService.updateRegistrationForm(updateDto);
        return ResponseEntity.ok("Cập nhật thành công");
    }
}
