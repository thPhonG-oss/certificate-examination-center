package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    // Additional query methods can be defined here if needed
}
