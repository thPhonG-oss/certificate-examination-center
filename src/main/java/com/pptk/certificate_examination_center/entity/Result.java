package com.pptk.certificate_examination_center.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ket_qua")
public class Result {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_ket_qua")
    private Long id;

    @Column(name = "id_phieu_du_thi")
    private Long addmission_ticket_id;
    @Column(name = "diem")
    private Integer score;

    @Column(name = "trang_thai")
    private String status;

    @Column(name = "trang_thai_nhan")
    private Boolean receive_status;

    @Column(name = "chi_tiet_ket_qua")
    private String details_result;

    @Column(name = "ngay_cap")
    private LocalDate certificate_issue_date;

    public Result(){}

    public Result(Long id, Long addmission_ticket_id, Integer score, String status, Boolean receive_status, String details_result, LocalDate certificate_issue_date) {
        this.id = id;
        this.addmission_ticket_id = addmission_ticket_id;
        this.score = score;
        this.status = status;
        this.receive_status = receive_status;
        this.details_result = details_result;
        this.certificate_issue_date = certificate_issue_date;
    }

    public Result(Long id, Long addmission_ticket_id, Integer score, String status, Boolean receive_status) {
        this.id = id;
        this.addmission_ticket_id = addmission_ticket_id;
        this.score = score;
        this.status = status;
        this.receive_status = receive_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddmission_ticket_id() {
        return addmission_ticket_id;
    }

    public void setAddmission_ticket_id(Long addmission_ticket_id) {
        this.addmission_ticket_id = addmission_ticket_id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getReceive_status() {
        return receive_status;
    }

    public void setReceive_status(Boolean receive_status) {
        this.receive_status = receive_status;
    }

    public String getDetails_result() {
        return details_result;
    }

    public void setDetails_result(String details_result) {
        this.details_result = details_result;
    }

    public LocalDate getCertificate_issue_date() {
        return certificate_issue_date;
    }

    public void setCertificate_issue_date(LocalDate certificate_issue_date) {
        this.certificate_issue_date = certificate_issue_date;
    }
}
