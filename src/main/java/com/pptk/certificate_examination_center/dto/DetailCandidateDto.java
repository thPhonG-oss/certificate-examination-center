package com.pptk.certificate_examination_center.dto;

public class DetailCandidateDto {
    private String candidate_id;
    private String certificate_id;
    private String candidate_name;
    private String dob;
    private String citizen_id;
    private String exam_schedule;

    public DetailCandidateDto() {
    }

    public DetailCandidateDto(String candidate_id, String certificate_id, String candidate_name, String dob, String citizen_id, String exam_schedule) {
        this.candidate_id = candidate_id;
        this.certificate_id = certificate_id;
        this.candidate_name = candidate_name;
        this.dob = dob;
        this.citizen_id = citizen_id;
        this.exam_schedule = exam_schedule;
    }

    public String getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(String candidate_id) {
        this.candidate_id = candidate_id;
    }

    public String getCertificate_id() {
        return certificate_id;
    }

    public void setCertificate_id(String certificate_id) {
        this.certificate_id = certificate_id;
    }

    public String getCandidate_name() {
        return candidate_name;
    }

    public void setCandidate_name(String candidate_name) {
        this.candidate_name = candidate_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCitizen_id() {
        return citizen_id;
    }

    public void setCitizen_id(String citizen_id) {
        this.citizen_id = citizen_id;
    }

    public String getExam_schedule() {
        return exam_schedule;
    }

    public void setExam_schedule(String exam_schedule) {
        this.exam_schedule = exam_schedule;
    }
}