package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dto.CandidateDto;
import com.pptk.certificate_examination_center.repository.CandidateRepository;
import com.pptk.certificate_examination_center.service.CandidateService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<CandidateDto> getAllCandidates() {
        return List.of();
    }

    @Override
    public CandidateDto getCandidateById(Long id) {
        return null;
    }

    @Override
    @Transactional
    @Modifying
    public void createCandidate(CandidateDto candidateDto) {
        candidateRepository.saveCandidate(
                candidateDto.getRegistration_form_id(),
                candidateDto.getName(),
                candidateDto.getGender(),
                candidateDto.getDob(),
                candidateDto.getPhoneNumber(),
                candidateDto.getEmail(),
                candidateDto.getAddress(),
                candidateDto.getCitizen_id(),
                candidateDto.getImageUrl()
        );
    }

    @Override
    public CandidateDto updateCandidate(Long id, CandidateDto candidateDto) {
        return null;
    }

    @Override
    public void deleteCandidate(Long id) {

    }
}
