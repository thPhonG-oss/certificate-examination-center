package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.dto.ApiResponseDto;
import com.pptk.certificate_examination_center.entity.ResponseStatus;
import com.pptk.certificate_examination_center.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ApiResponseDto<Object>> getAllSchedule(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponseDto<>(
                    String.valueOf(ResponseStatus.SUCCESS),
                        "Request All schedules successfully",
                        scheduleService.getAllSchedulesWithCertificate()
                )
        );
    }
}
