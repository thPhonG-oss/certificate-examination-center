package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.dto.ScheduleWithCertificateDto;
import com.pptk.certificate_examination_center.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    public List<ScheduleWithCertificateDto> getAllSchedulesWithCertificate();
    public List<Schedule> getAllSchedules();
    public Schedule getScheduleById(Long id);
    public Schedule createSchedule(Schedule schedule);
    public Schedule updateSchedule(Long id, Schedule schedule);
    public void deleteSchedule(Long id);
    public void updateNumberOfCandidatesForIndividual(Long schedule_id);
}
