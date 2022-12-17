package com.daw2.lindovaldo.repository;

import com.daw2.lindovaldo.model.Paciente;
import com.daw2.lindovaldo.model.Status;
import com.daw2.lindovaldo.repository.helper.paciente.PacienteQueries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long>, PacienteQueries {

    List<Paciente> findByStatus(Status ativo);
}
