package com.pptk.certificate_examination_center.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "phieu_dang_ky")
public class RegistrationForm {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_phieu_dang_ky")
    private Long id;

    @Column(name = "id_khach_hang")
    private Long customer_id;

    @Column(name = "ngay_dang_ky")
    private LocalDate registration_date;

    @Column(name = "id_nhan_vien")
    private Long user_id;

    @Column(name = "id_lich_thi")
    private Long schedule_id;

    @Column(name="trang_thai_gia_han")
    private String renew_status;

    @Column(name = "lan_gia_han")
    private Integer number_of_renewals;

    public RegistrationForm() {
    }

    public RegistrationForm(Long id, Long customer_id, LocalDate registration_date, Long user_id, Long schedule_id, String renew_status, Integer number_of_renewals) {
        this.id = id;
        this.customer_id = customer_id;
        this.registration_date = registration_date;
        this.user_id = user_id;
        this.schedule_id = schedule_id;
        this.renew_status = renew_status;
        this.number_of_renewals = number_of_renewals;
    }

    public RegistrationForm(Long id, Long customer_id, LocalDate registration_date, Long user_id, String renew_status) {
        this.id = id;
        this.customer_id = customer_id;
        this.registration_date = registration_date;
        this.user_id = user_id;
        this.renew_status = renew_status;
    }

    public RegistrationForm(Long customer_id, LocalDate registration_date, Long user_id, String renew_status) {
        this.customer_id = customer_id;
        this.registration_date = registration_date;
        this.user_id = user_id;
        this.renew_status = renew_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDate getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(LocalDate registration_date) {
        this.registration_date = registration_date;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getRenew_status() {
        return renew_status;
    }

    public void setRenew_status(String renew_status) {
        this.renew_status = renew_status;
    }

    public Long getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Long schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Integer getNumber_of_renewals() {
        return number_of_renewals;
    }

    public void setNumber_of_renewals(Integer number_of_renewals) {
        this.number_of_renewals = number_of_renewals;
    }
}
