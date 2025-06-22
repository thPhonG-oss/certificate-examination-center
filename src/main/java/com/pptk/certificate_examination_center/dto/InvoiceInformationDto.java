package com.pptk.certificate_examination_center.dto;

public class InvoiceInformationDto {
    private Integer idPdk;
    private Integer idKh;
    private String typeKh;
    private Long number;

    public InvoiceInformationDto(Integer idPdk, Integer idKh, String typeKh, Long number) {
        this.idPdk = idPdk;
        this.idKh = idKh;
        this.typeKh = typeKh;
        this.number = number;
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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
