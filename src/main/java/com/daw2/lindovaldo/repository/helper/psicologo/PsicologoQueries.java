package com.daw2.lindovaldo.repository.helper.psicologo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.daw2.lindovaldo.model.Psicologo;
import com.daw2.lindovaldo.model.filter.PsicologoFilter;

public interface PsicologoQueries {

	Page<Psicologo> pesquisar(PsicologoFilter filtro, Pageable pageable);

}
