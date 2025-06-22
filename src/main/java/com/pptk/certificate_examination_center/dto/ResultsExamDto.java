package com.pptk.certificate_examination_center.dto;

public class ResultsExamDto {
    private Integer idThiSinh;
    private String tenChungChi;
    private String diem;
    private Integer idKetQua;

    // Constructors
    public ResultsExamDto() {}
    public ResultsExamDto(Integer idThiSinh, String tenChungChi, String diem,Integer idKetQua) {
        this.idThiSinh = idThiSinh;
        this.tenChungChi = tenChungChi;
        this.diem = diem;
        this.idKetQua = idKetQua;
    }

    // Getters and Setters
    public Integer getIdThiSinh() { return idThiSinh; }
    public void setIdThiSinh(Integer idThiSinh) { this.idThiSinh = idThiSinh; }
    public String getTenChungChi() { return tenChungChi; }
    public void setTenChungChi(String tenChungChi) { this.tenChungChi = tenChungChi; }
    public String getDiem() { return diem; }
    public void setDiem(String diem) { this.diem = diem; }
    public Integer getIdKetQua() { return idKetQua; }
    public void setIdKetQua(Integer idKetQua) { this.idKetQua = idKetQua; }
}