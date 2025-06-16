package com.pptk.certificate_examination_center.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "hoa_don")
public class Invoice {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_hoa_don")
    private Long id;

    @Column(name = "id_phieu_dang_ky")
    private Long registration_form_id;

    @Column(name = "ngay_tao")
    private LocalDate invoice_date;

    @Column(name = "giam_gia")
    private Double sales_amount;

    @Column(name = "tong_tien")
    private Double total_amount;

    @Column(name = "trang_thai")
    private String status;

    @Column(name = "phuong_thuc_tt")
    private String payment_method;

    public Invoice() {
    }

    public Invoice(Long registration_form_id, LocalDate invoice_date, Double sales_amount, Double total_amount, String status, String payment_method) {
        this.registration_form_id = registration_form_id;
        this.invoice_date = invoice_date;
        this.sales_amount = sales_amount;
        this.total_amount = total_amount;
        this.status = status;
        this.payment_method = payment_method;
    }

    public Invoice(Long id, Long registration_form_id, LocalDate invoice_date, Double sales_amount, Double total_amount, String status, String payment_method) {
        this.id = id;
        this.registration_form_id = registration_form_id;
        this.invoice_date = invoice_date;
        this.sales_amount = sales_amount;
        this.total_amount = total_amount;
        this.status = status;
        this.payment_method = payment_method;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRegistration_form_id() {
        return registration_form_id;
    }

    public void setRegistration_form_id(Long registration_form_id) {
        this.registration_form_id = registration_form_id;
    }

    public LocalDate getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(LocalDate invoice_date) {
        this.invoice_date = invoice_date;
    }

    public Double getSales_amount() {
        return sales_amount;
    }

    public void setSales_amount(Double sales_amount) {
        this.sales_amount = sales_amount;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
