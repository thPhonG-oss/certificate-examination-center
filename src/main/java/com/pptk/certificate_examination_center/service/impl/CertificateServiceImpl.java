package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.entity.Certificate;
import com.pptk.certificate_examination_center.repository.CertificateRepository;
import com.pptk.certificate_examination_center.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;
    @Override
    public List<Certificate> getAllCertificate() {
        return certificateRepository.findAll();
    }
}
