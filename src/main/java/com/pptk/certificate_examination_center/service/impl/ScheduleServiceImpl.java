package com.pptk.certificate_examination_center.service.impl;

import com.pptk.certificate_examination_center.dto.ScheduleDto;
import com.pptk.certificate_examination_center.dto.ScheduleWithCertificateDto;
import com.pptk.certificate_examination_center.entity.Schedule;
import com.pptk.certificate_examination_center.mapper.ScheduleMapper;
import com.pptk.certificate_examination_center.repository.ScheduleRepository;
import com.pptk.certificate_examination_center.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }
    @Override
    public Schedule getFindOneSchedule(Integer id) {
        return scheduleRepository.findById(id.longValue()).orElse(null);
    }

    @Override
    public Schedule setSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public ResponseEntity<Map<String, String>> deleteSchedule(Integer id) {
        scheduleRepository.deleteById(id.longValue());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Xóa thành công");
        return ResponseEntity.ok(response);
    }

    @Override
    public Schedule updateSchedule(Integer id, ScheduleDto schedule) {
        Schedule schedule_new = ScheduleMapper.toEntity(schedule); // nếu toEntity là static
        schedule_new.setId(id.longValue()); // đảm bảo ID được cập nhật đúng
        return scheduleRepository.save(schedule_new); // lưu lại
    }

    @Override
    public void updateNumberOfCandidatesForIndividual(Long schedule_id) {

    }

    @Override
    public List<ScheduleWithCertificateDto> getAllSchedulesWithCertificate() {
        return List.of();
    }
}
