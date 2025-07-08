package com.pptk.certificate_examination_center.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pptk.certificate_examination_center.dao.ExamScheduleDao;
import com.pptk.certificate_examination_center.dto.ExamScheduleDto;
import com.pptk.certificate_examination_center.service.ExamScheduleService;

@Service
public class ExamScheduleServiceImpl implements ExamScheduleService {

    private final ExamScheduleDao scheduleDao;

    @Autowired
    public ExamScheduleServiceImpl(ExamScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    @Override
    public List<ExamScheduleDto> getScheduleByRegistrationId(Integer certificateId) {
        return scheduleDao.selectScheduleByRegistrationId(certificateId);
    }
}
