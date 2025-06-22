package com.pptk.certificate_examination_center.dto;

import com.pptk.certificate_examination_center.entity.Certificate;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceDto {
    private Integer idPdk;
    private Integer idKh;
    private String typeKh;
    private Double sumMoney;
    private Double discount;
    private Double payment;
    private List<Certificate> certificates;

    public InvoiceDto(Integer idPdk, Integer idKh, String typeKh, Double sumMoney, Double discount, Double payment, List<Certificate> certificates) {
        this.idPdk = idPdk;
        this.idKh = idKh;
        this.typeKh = typeKh;
        this.sumMoney = sumMoney;
        this.discount = discount;
        this.payment = payment;
        this.certificates = certificates;
    }

    public Integer getIdPdk() {
        return idPdk;
    }

    public void setIdPdk(Integer idPdk) {
        this.idPdk = idPdk;
    }

    public Integer getIdKh() {
        return idKh;
    }

    public void setIdKh(Integer idKh) {
        this.idKh = idKh;
    }

    public String getTypeKh() {
        return typeKh;
    }

    public void setTypeKh(String typeKh) {
        this.typeKh = typeKh;
    }

    public Double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
}
