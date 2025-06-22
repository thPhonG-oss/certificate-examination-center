package com.pptk.certificate_examination_center.service;

import com.pptk.certificate_examination_center.dto.ScheduleDto;
import com.pptk.certificate_examination_center.entity.Certificate;
import com.pptk.certificate_examination_center.entity.Schedule;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
    public List<Schedule> getAllSchedule();
    public Schedule setSchedule(Schedule schedule);
    public Schedule getFindOneSchedule(Integer id);
    public ResponseEntity<Map<String, String>> deleteSchedule(Integer id);
    public Schedule updateSchedule(Integer id, ScheduleDto scheduleDto);
}
