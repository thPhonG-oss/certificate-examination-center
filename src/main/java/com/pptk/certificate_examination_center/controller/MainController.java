package com.pptk.certificate_examination_center.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@Controller
@RequestMapping("/api")
public class MainController {
    @GetMapping("auth/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/dashboard")
//    @PreAuthorize("hasRole('ROLE_USER')")
    public String index() {
        return "index";
    }
}
