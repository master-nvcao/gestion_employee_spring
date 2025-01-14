package com.my.project.salaireservice.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String checkIn;
    private String checkOut;
    private String status; // Present, Absent, Late
    private Integer heuresDAbsence;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

// Getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getHeuresDAbsence() {
        return heuresDAbsence;
    }

    public void setHeuresDAbsence(Integer heuresDAbsence) {
        this.heuresDAbsence = heuresDAbsence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}