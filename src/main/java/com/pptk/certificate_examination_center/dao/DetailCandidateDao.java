package com.pptk.certificate_examination_center.dao;

import com.pptk.certificate_examination_center.dto.DetailCandidateDto;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface DetailCandidateDao {
    DetailCandidateDto selectCandidateByRegistration(Integer registrationId, Integer certificateId);
}
