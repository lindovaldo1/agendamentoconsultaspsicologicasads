package com.daw2.lindovaldo.repository.helper.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.daw2.lindovaldo.model.Paciente;
import com.daw2.lindovaldo.model.filter.PacienteFilter;

public interface PacienteQueries {

	Page<Paciente> pesquisar(PacienteFilter filtro, Pageable pageable);

}
