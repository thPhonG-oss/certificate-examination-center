package com.pptk.certificate_examination_center.dao;

import com.pptk.certificate_examination_center.dto.DetailCandidateDto;

public interface DetailCandidateDao {
    DetailCandidateDto selectCandidateByRegistration(Integer registrationId);
}
