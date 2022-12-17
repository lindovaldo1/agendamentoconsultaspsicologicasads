package com.daw2.lindovaldo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw2.lindovaldo.model.Psicologo;
import com.daw2.lindovaldo.repository.helper.psicologo.PsicologoQueries;

public interface PsicologoRepository extends JpaRepository<Psicologo, Long>, PsicologoQueries {
}