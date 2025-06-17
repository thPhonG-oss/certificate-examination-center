package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationFormRepository extends JpaRepository<RegistrationForm, Long> {
    // Additional query methods can be defined here if needed
}
