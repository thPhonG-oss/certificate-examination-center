package com.pptk.certificate_examination_center.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    public Result(){}

    public Result(Long addmission_ticket_id, Integer score, String status) {
        this.addmission_ticket_id = addmission_ticket_id;
        this.score = score;
        this.status = status;
    }

    public Result(Long id, Long addmission_ticket_id, Integer score, String status) {
        this.id = id;
        this.addmission_ticket_id = addmission_ticket_id;
        this.score = score;
        this.status = status;
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
}