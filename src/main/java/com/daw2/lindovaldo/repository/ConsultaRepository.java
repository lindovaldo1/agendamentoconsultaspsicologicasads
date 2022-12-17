package com.daw2.lindovaldo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw2.lindovaldo.model.Consulta;
import com.daw2.lindovaldo.model.Psicologo;
import com.daw2.lindovaldo.model.Status;
import com.daw2.lindovaldo.repository.helper.consulta.ConsultaQueries;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>, ConsultaQueries {

    List<Consulta> findByStatus(Status ativo);

    List<Consulta> findByPsicologo(Psicologo psicologo);

}
