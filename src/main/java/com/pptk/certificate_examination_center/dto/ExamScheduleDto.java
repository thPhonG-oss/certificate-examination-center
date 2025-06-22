package com.pptk.certificate_examination_center.dto;

public class ExamScheduleDto {
    private Long id;
    private String date;              // format: dd/MM/yyyy
    private String time;              // format: HH:mm
    private Long certification_id;
    private Integer currentParticipants;
    private Integer maxParticipants;

    public ExamScheduleDto() {}

    public ExamScheduleDto(Long id, String date, String time, Long certification_id, Integer currentParticipants, Integer maxParticipants) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.certification_id = certification_id;
        this.currentParticipants = currentParticipants;
        this.maxParticipants = maxParticipants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getCertification_id() {
        return certification_id;
    }

    public void setCertification_id(Long certification_id) {
        this.certification_id = certification_id;
    }

    public Integer getCurrentParticipants() {
        return currentParticipants;
    }

    public void setCurrentParticipants(Integer currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
}
