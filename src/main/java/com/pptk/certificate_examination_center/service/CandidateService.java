package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.dto.CandidateDto;

import java.util.List;

public interface CandidateService {
    public List<CandidateDto> getAllCandidates();
    public CandidateDto getCandidateById(Long id);
    public CandidateDto createCandidate(CandidateDto candidateDto);
    public CandidateDto updateCandidate(Long id, CandidateDto candidateDto);
    public void deleteCandidate(Long id);
}
