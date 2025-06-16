package com.pptk.certificate_examination_center.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "lich_thi")
public class Schedule {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_lich_thi")
    private Long id;

    @Column(name = "ngay_thi")
    private LocalDate date;

    @Column(name = "gio_thi")
    private LocalTime time;

    @Column(name = "id_chung_chi")
    private Long certification_id;

    @Column(name = "so_luong_dk")
    private Integer currentParticipants;

    @Column(name = "sl_toi_da")
    private Integer maxParticipants;

    public Schedule() {}

    public Schedule(LocalDate date, LocalTime time, Long certification_id, Integer currentParticipants, Integer maxParticipants) {
        this.date = date;
        this.time = time;
        this.certification_id = certification_id;
        this.currentParticipants = currentParticipants;
        this.maxParticipants = maxParticipants;
    }

    public Schedule(Long id, LocalDate date, LocalTime time, Long certification_id, Integer currentParticipants, Integer maxParticipants) {
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
