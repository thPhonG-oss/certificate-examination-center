package com.pptk.certificate_examination_center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pptk.certificate_examination_center.service.ScheduleService;


@RestController
@RequestMapping("/by-registration-certificate")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{registrationId}")
    public ResponseEntity<Object> getScheduleByRegistrationId(@PathVariable Integer registrationId){
        Object schedule = scheduleService.getScheduleByRegistrationId(registrationId);
        if (schedule == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy lịch thi");
        }
        return ResponseEntity.ok(schedule);
    }
}

