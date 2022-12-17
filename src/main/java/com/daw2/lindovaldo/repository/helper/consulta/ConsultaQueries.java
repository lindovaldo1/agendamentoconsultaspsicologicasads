package com.daw2.lindovaldo.repository.helper.consulta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.daw2.lindovaldo.model.Consulta;
import com.daw2.lindovaldo.model.filter.ConsultaFilter;

public interface ConsultaQueries {

	Page<Consulta> pesquisar(ConsultaFilter filtro, Pageable pageable, boolean b);

}
