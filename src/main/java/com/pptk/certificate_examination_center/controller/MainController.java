package com.pptk.certificate_examination_center.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String index() {
        return "index"; // This will resolve to src/main/resources/templates/index.html
    }

    @GetMapping("/list-registration-forms")
    public String registrationList() {
        return "listRegistrationPage";
    }

    @GetMapping("/detail-registration-form")
    public String detailRegistrationForm() {
        return "detailRegistrationFormPage"; 
    }
}
