package com.pptk.certificate_examination_center.controller;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class MainController {
    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> dashboard(){
        return ResponseEntity.ok("Welcome to dashboard");
    }
}
