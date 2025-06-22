package com.pptk.certificate_examination_center.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chung_chi")
public class Certificate {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_chung_chi")
    private Long id;
    @Column(name = "ten_chung_chi", nullable = false)
    private String name;
    @Column(name = "gia")
    private Double price;

    public Certificate() {
    }

    public Certificate(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Certificate(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}