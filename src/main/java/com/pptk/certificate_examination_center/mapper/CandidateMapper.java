package com.pptk.certificate_examination_center.mapper;

import com.pptk.certificate_examination_center.dto.CandidateDto;
import com.pptk.certificate_examination_center.entity.Candidate;

public class CandidateMapper {
    public static CandidateDto toDto(Candidate candidate) {
        if (candidate == null) {
            return null;
        }
        CandidateDto dto = new CandidateDto(
                candidate.getId_registration_form(),
                candidate.getName(),
                candidate.getGender(),
                candidate.getEmail(),
                candidate.getPhoneNumber(),
                candidate.getDob(),
                candidate.getAddress(),
                candidate.getCitizen_id()
        );
        return dto;
    }

    public static Candidate toEntity(CandidateDto dto) {
        if (dto == null) {
            return null;
        }
        Candidate candidate = new Candidate();
        candidate.setId_registration_form(dto.getRegistration_form_id());
        candidate.setName(dto.getName());
        candidate.setGender(dto.getGender());
        candidate.setEmail(dto.getEmail());
        candidate.setPhoneNumber(dto.getPhoneNumber());
        candidate.setDob(dto.getDob());
        candidate.setAddress(dto.getAddress());
        candidate.setCitizen_id(dto.getCitizen_id());
        return candidate;
    }
}
