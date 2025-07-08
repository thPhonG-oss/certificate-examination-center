package com.pptk.certificate_examination_center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pptk.certificate_examination_center.dto.DetailCandidateDto;
import com.pptk.certificate_examination_center.service.DetailCandidateService;

@RestController
@RequestMapping("/candidate")
public class DetailCandidateController {

    @Autowired
    private DetailCandidateService detailcandidateService;

    @GetMapping("/detail-candidate-by-registration")
    public ResponseEntity<DetailCandidateDto> getCandidateByRegistrationId(
        @RequestParam Integer registrationId, 
        @RequestParam Integer certificateId) {

        DetailCandidateDto candidate = detailcandidateService.getCandidateByRegistrationId(registrationId, certificateId);

        if(candidate == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(candidate);
    }
}
