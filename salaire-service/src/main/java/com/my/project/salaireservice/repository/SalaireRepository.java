package com.my.project.salaireservice.repository;

import com.my.project.salaireservice.model.Salaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaireRepository extends JpaRepository<Salaire, Long> {}
