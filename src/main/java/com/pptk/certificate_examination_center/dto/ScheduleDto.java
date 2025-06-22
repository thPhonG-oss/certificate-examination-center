package com.pptk.certificate_examination_center.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class ScheduleDto {
    private Long certification_id;
    private LocalDate date;
    private LocalTime time;
    private Integer maxParticipants;
    private Integer currentParticipants;

    public Long getCertification_id() {
        return certification_id;
    }

    public void setCertification_id(Long certification_id) {
        this.certification_id = certification_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Integer getCurrentParticipants() {
        return currentParticipants;
    }

    public void setCurrentParticipants(Integer currentParticipants) {
        this.currentParticipants = currentParticipants;
    }
}
