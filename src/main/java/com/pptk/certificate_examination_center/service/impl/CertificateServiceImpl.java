package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.entity.Certificate;
import com.pptk.certificate_examination_center.service.CertificateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Override
    public List<Certificate> getAllCertificates() {
        return List.of();
    }

    @Override
    public Certificate getCertificateById(Long id) {
        return null;
    }

    @Override
    public Certificate saveCertificate(Certificate certificate) {
        return null;
    }

    @Override
    public void deleteCertificate(Long id) {

    }

    @Override
    public List<Certificate> searchCertificates(String keyword) {
        return List.of();
    }
}
