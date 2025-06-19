package com.pptk.certificate_examination_center.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleWithCertificateDto {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private Long certificateId; // Add this field
    private String certificateName;
    private Integer currentParticipants;
    private Integer maxParticipants;

    public ScheduleWithCertificateDto(Object[] objects) {
        this.id = ((Number) objects[0]).longValue();
        this.date = ((java.sql.Date) objects[1]).toLocalDate();
        this.time = ((java.sql.Time) objects[2]).toLocalTime();
        this.certificateName = (String) objects[3];
        this.currentParticipants = ((Number) objects[4]).intValue();
        this.maxParticipants = ((Number) objects[5]).intValue();
        this.certificateId = ((Number) objects[6]).longValue(); // Add this
    }

    // Add getter
    public Long getCertificateId() {
        return certificateId;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public Integer getCurrentParticipants() {
        return currentParticipants;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }
}