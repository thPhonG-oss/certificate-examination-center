package com.pptk.certificate_examination_center.controller;

import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.pptk.certificate_examination_center.service.ListRegistrationFormService;

@RestController
@RequestMapping("api/list-registration-forms") // localhost:8080/api/list-registration-forms
public class ListRegistrationFormController {
    @Autowired
    private ListRegistrationFormService listRegistrationFormService;

    @GetMapping
    public ResponseEntity<Object> getListRegistrationForms() {
        return ResponseEntity.ok(listRegistrationFormService.getListRegistrationForms());
    }
}
