package com.my.project.frontend.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Salaire {

    private Long id;

    private Double base;
    private Integer heuresSupplementaires;
    private Double montant;

    @DateTimeFormat
    private Date date;
    private Double primes;
    private Integer absences;


    private User user;


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

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }
}
