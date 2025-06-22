package com.pptk.certificate_examination_center.dto;

public class DetailResultsDto {
    private String name;
    private String email;
    private String phone;
    private String ten_chung_chi;
    private String ngay_thi;
    private String diem;
    private String trangthai;
    private Integer trang_thai_nhan;
    private Integer id_ket_qua;

    public DetailResultsDto() {}

    public DetailResultsDto(String name, String email, String phone, String ten_chung_chi,
                            String ngay_thi, String diem, String trangthai,
                            Integer trang_thai_nhan, Integer id_kq) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.ten_chung_chi = ten_chung_chi;
        this.ngay_thi = ngay_thi;
        this.diem = diem;
        this.trangthai = trangthai;
        this.trang_thai_nhan = trang_thai_nhan;
        this.id_ket_qua = id_kq;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTen_chung_chi() {
        return ten_chung_chi;
    }

    public void setTen_chung_chi(String ten_chung_chi) {
        this.ten_chung_chi = ten_chung_chi;
    }

    public String getNgay_thi() {
        return ngay_thi;
    }

    public void setNgay_thi(String ngay_thi) {
        this.ngay_thi = ngay_thi;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public Integer getTrang_thai_nhan() {
        return trang_thai_nhan;
    }

    public void setTrang_thai_nhan(Integer trang_thai_nhan) {
        this.trang_thai_nhan = trang_thai_nhan;
    }

    public Integer getId_ket_qua() {
        return id_ket_qua;
    }

    public void setId_ket_qua(Integer id_ket_qua) {
        this.id_ket_qua = id_ket_qua;
    }
}