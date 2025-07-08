package com.pptk.certificate_examination_center.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.pptk.certificate_examination_center.dto.DetailCandidateDto;
import com.pptk.certificate_examination_center.service.DetailCandidateService;
import com.pptk.certificate_examination_center.dao.DetailCandidateDao;

@Repository
public class DetailCandidateServiceImpl implements DetailCandidateService{
    @Autowired
    DetailCandidateDao detailCandidateDao;

    @Override
    public DetailCandidateDto getCandidateByRegistrationId(Integer registrationId, 
                                                           Integer certificateId) {
        return detailCandidateDao.selectCandidateByRegistration(registrationId, certificateId);
    }
}
