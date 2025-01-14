package com.my.project.frontend.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Presence {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String checkIn;
    private String checkOut;
    private String status; // Present, Absent, Late
    private Integer heuresDAbsence;
    private User user; // Simplified relationship for Thymeleaf use

    // Getters and Setters

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
