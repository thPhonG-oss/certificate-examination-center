package com.pptk.certificate_examination_center.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "phieu_du_thi")
public class AddmissionTickets {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phieu_du_thi")
    private Long id;

    @Column(name = "id_thi_sinh")
    private Integer candidate;

    @Column(name="phong_thi")
    private String examRoom;
    
    //constructor
    public AddmissionTickets() {
    }
    public AddmissionTickets(Long id, Integer candidate, String examRoom) {
        this.id = id;
        this.candidate = candidate;
        this.examRoom = examRoom;
    }
    //toString
    @Override
    public String toString() {
        return "AddmissionTickets{" +
                "id=" + id +
                ", candidate=" + candidate +
                ", examRoom='" + examRoom + '\'' +
                '}';
    }
    //Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCandidate() {
        return candidate;
    }

    public void setCandidate(Integer candidate) {
        this.candidate = candidate;
    }
    public String getExamRoom() {
        return examRoom;
    }

    public void setExamRoom(String examRoom) {
        this.examRoom = examRoom;
    
    }
}
