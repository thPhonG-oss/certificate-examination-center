package com.pptk.certificate_examination_center.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tai_khoan")
public class Account {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_tai_khoan")
    private Long id;

    @Column(name = "ten_dang_nhap", nullable = false, unique = true)
    private String username;

    @Column(name = "mat_khau", nullable = false)
    private String password;

    @Column(name = "id_nhan_vien")
    private Long employee_id;

    @Column(name = "id_vai_tro")
    private Long role_id;

    @Column(name = "trang_thai", nullable = false)
    private String status;

    public Account() {
    }

    public Account(Long id, String username, String password, Long employee_id, Long role_id, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.employee_id = employee_id;
        this.role_id = role_id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
