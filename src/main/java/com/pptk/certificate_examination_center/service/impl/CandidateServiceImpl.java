package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dto.CandidateDto;
import com.pptk.certificate_examination_center.entity.Candidate;
import com.pptk.certificate_examination_center.exception.CandidateNotFoundException;
import com.pptk.certificate_examination_center.mapper.CandidateMapper;
import com.pptk.certificate_examination_center.repository.CandidateRepository;
import com.pptk.certificate_examination_center.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<CandidateDto> getAllCandidates() {
        List<CandidateDto> candidateDto = new ArrayList<>();
        List<Candidate> candidates = candidateRepository.findAll();
        candidates.forEach(candidate -> {
            CandidateDto dto = CandidateMapper.toDto(candidate);
            candidateDto.add(dto);
        });
        return candidateDto;
    }

    @Override
    public CandidateDto getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with id: " + id));
        return CandidateMapper.toDto(candidate);
    }

    @Override
    public CandidateDto createCandidate(CandidateDto candidateDto) {
        Candidate candidate = CandidateMapper.toEntity(candidateDto);
        Candidate savedCandidate = candidateRepository.save(candidate);
        return CandidateMapper.toDto(savedCandidate);
    }

    @Override
    public CandidateDto updateCandidate(Long id, CandidateDto candidateDto) {
        Candidate existingCandidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with id: " + id));
        Candidate updatedCandidate = CandidateMapper.toEntity(candidateDto);
        return CandidateMapper.toDto(candidateRepository.save(updatedCandidate));
    }

    @Override
    public void deleteCandidate(Long id) {
        if(!candidateRepository.existsById(id)) {
            throw new CandidateNotFoundException("Candidate not found with id: " + id);
        }else {
            candidateRepository.deleteById(id);
        }
    }
}
