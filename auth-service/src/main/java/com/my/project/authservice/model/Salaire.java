package com.my.project.authservice.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Salaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer heuresSupplementaires;
    private Double montant;
    private Date date;
    private Double primes;
    private Integer absences;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHeuresSupplementaires() {
        return heuresSupplementaires;
    }

    public void setHeuresSupplementaires(Integer heuresSupplementaires) {
        this.heuresSupplementaires = heuresSupplementaires;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrimes() {
        return primes;
    }

    public void setPrimes(Double primes) {
        this.primes = primes;
    }

    public Integer getAbsences() {
        return absences;
    }

    public void setAbsences(Integer absences) {
        this.absences = absences;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
