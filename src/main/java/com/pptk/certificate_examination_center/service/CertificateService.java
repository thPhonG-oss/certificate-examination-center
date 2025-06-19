package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.entity.Certificate;

import java.util.List;

public interface CertificateService {
    public List<Certificate> getAllCertificates();
    public Certificate getCertificateById(Long id);
    public Certificate saveCertificate(Certificate certificate);
    public void deleteCertificate(Long id);
    public List<Certificate> searchCertificates(String keyword);
}
