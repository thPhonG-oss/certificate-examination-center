package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.dto.DetailCandidateDto;

public interface DetailCandidateService {
    DetailCandidateDto getCandidateByRegistrationId(Integer registrationId);
}
