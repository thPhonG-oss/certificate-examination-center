package com.pptk.certificate_examination_center.mapper;

import com.pptk.certificate_examination_center.dto.CandidateDto;
import com.pptk.certificate_examination_center.entity.Candidate;

public class CandidateMapper {
    public static CandidateDto toDto(Candidate candidate) {
        if (candidate == null) {
            return null;
        }
        return new CandidateDto(
                candidate.getId_registration_form(),
                candidate.getName(),
                candidate.getGender(),
                candidate.getEmail(),
                candidate.getPhoneNumber(),
                candidate.getDob(),
                candidate.getAddress(),
                candidate.getCitizen_id(),
                candidate.getImageUrl()
        );
    }

    public static Candidate toEntity(CandidateDto dto) {
        if (dto == null) {
            return null;
        }
        return new Candidate(
                dto.getName(),
                dto.getGender(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getDob(),
                dto.getAddress(),
                dto.getCitizen_id(),
                dto.getRegistration_form_id(),
                dto.getImageUrl()
        );
    }
}
