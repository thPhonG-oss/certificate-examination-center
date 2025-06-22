
package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.dto.ApiResponseDto;
import com.pptk.certificate_examination_center.dto.ScheduleDto;
import com.pptk.certificate_examination_center.entity.Schedule;
import com.pptk.certificate_examination_center.service.ScheduleService;
import jakarta.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @PostMapping ("lich_thi")
    public Schedule setSchedule(@RequestBody Schedule schedule){
        return scheduleService.setSchedule(schedule);
    }
    @DeleteMapping("/lich_thi/{id}")
    public ResponseEntity<Map<String, String>> deleteSchedule(@PathVariable Integer id){
        return scheduleService.deleteSchedule(id);
    }
    @PutMapping("/lich_thi/{id}")
    public Schedule updateSchedule(@PathVariable Integer id, @RequestBody ScheduleDto scheduleDto){
        return scheduleService.updateSchedule(id,scheduleDto);
    }
    @GetMapping("/lich_thi")
    public List< Schedule > getAllSchedule(){
        return  scheduleService.getAllSchedule();
    }
    @GetMapping("/lich_thi/{id}")
    public Schedule getFindOneSchedule(@PathVariable("id") Integer id){
        return scheduleService.getFindOneSchedule(id);
    }

    @GetMapping("/schedulewithcertificate")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> getAllScheduleWithCertificateName(){
        return ResponseEntity.ok(scheduleService.getAllSchedulesWithCertificate());
    }
}
