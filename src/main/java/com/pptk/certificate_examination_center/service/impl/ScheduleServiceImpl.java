package com.pptk.certificate_examination_center.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pptk.certificate_examination_center.dao.ScheduleDao;
import com.pptk.certificate_examination_center.dto.ScheduleDto;
import com.pptk.certificate_examination_center.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao scheduleDao;

    @Autowired
    public ScheduleServiceImpl(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    @Override
    public List<ScheduleDto> getScheduleByRegistrationId(Integer registrationId) {
        return scheduleDao.selectScheduleByRegistrationId(registrationId);
    }
}
