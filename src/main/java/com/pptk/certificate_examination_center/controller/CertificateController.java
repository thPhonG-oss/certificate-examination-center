package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.entity.Certificate;
import com.pptk.certificate_examination_center.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class CertificateController {
    @Autowired
    private CertificateService certificateService;
    @RequestMapping("/chung_chi")
    public List<Certificate>getAllCertificate(){
        return certificateService.getAllCertificate();
    }
}
