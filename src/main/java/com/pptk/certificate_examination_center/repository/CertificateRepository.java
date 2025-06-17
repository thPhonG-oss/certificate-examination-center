package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    // Additional query methods can be defined here if needed
}
