package com.pptk.certificate_examination_center.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nhan_vien")
public class Employee {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_nhan_vien")
    private Long id;

    @Column(name = "ho_ten", nullable = false)
    private String name;

    @Column(name = "gioi_tinh", nullable = false)
    private String gender;

    private String email;

    private String password;

    @Column(name = "so_dien_thoai", nullable = false)
    private String phone;

    @Column(name = "dia_chi")
    private String address;

    @Column(name = "ngay_sinh")
    private LocalDate dob;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "nhan_vien_vai_tro",
            joinColumns = @JoinColumn(name = "id_nhan_vien"),
            inverseJoinColumns = @JoinColumn(name = "id_vai_tro"))
    private Set<Role> roles = new HashSet<>();

    public Employee() {
    }

    public Employee(String name, String gender, String email, String phone, String address, LocalDate dob, Set<Role> roles) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.roles = roles;
    }

    public Employee(Long id, String name, String gender, String email, String phone, String address, LocalDate dob, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
