package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dto.ScheduleWithCertificateDto;
import com.pptk.certificate_examination_center.entity.Schedule;
import com.pptk.certificate_examination_center.repository.ScheduleRepository;
import com.pptk.certificate_examination_center.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public List<ScheduleWithCertificateDto> getAllSchedulesWithCertificate() {
        List<Object[]> results = scheduleRepository.findAllSchedulesWithCertificates();
        return results.stream()
                .map(ScheduleWithCertificateDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return null;
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return null;
    }

    @Override
    public Schedule updateSchedule(Long id, Schedule schedule) {
        return null;
    }

    @Override
    public void deleteSchedule(Long id) {

    }

    @Override
    public void updateNumberOfCandidatesForIndividual(Long schedule_id) {
        scheduleRepository.updateNumberOfCandidatesForIndividual(schedule_id);
    }
}
