package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dto.CandidateDto;
import com.pptk.certificate_examination_center.entity.Candidate;
import com.pptk.certificate_examination_center.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {


    @Override
    public List<CandidateDto> getAllCandidates() {
        return List.of();
    }

    @Override
    public CandidateDto getCandidateById(Long id) {
        return null;
    }

    @Override
    public CandidateDto createCandidate(CandidateDto candidateDto) {
        return null;
    }

    @Override
    public CandidateDto updateCandidate(Long id, CandidateDto candidateDto) {
        return null;
    }

    @Override
    public void deleteCandidate(Long id) {

    }

}
