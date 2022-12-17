package com.daw2.lindovaldo.repository.helper.consulta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.daw2.lindovaldo.model.Consulta;
import com.daw2.lindovaldo.model.Horario;
import com.daw2.lindovaldo.model.Paciente;
import com.daw2.lindovaldo.model.Psicologo;
import com.daw2.lindovaldo.model.Status;
import com.daw2.lindovaldo.model.filter.ConsultaFilter;
import com.daw2.lindovaldo.repository.pagination.PaginacaoUtil;

public class ConsultaQueriesImpl implements ConsultaQueries {
	
	@PersistenceContext
	private EntityManager manager;

	
	@Override
	public Page<Consulta> pesquisar(ConsultaFilter filtro, Pageable pageable, boolean b) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Consulta> criteriaQuery = builder.createQuery(Consulta.class);
		Root<Consulta> c = criteriaQuery.from(Consulta.class);
		TypedQuery<Consulta> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(c.<Long>get("codigo"),
					filtro.getCodigo()));
		}

		if (filtro.getPsicologo() != null) {
			predicateList.add(builder.equal(c.<Psicologo>get("psicologo").<Long>get("codigo"),
					filtro.getPsicologo()));
		}

		if (filtro.getPaciente() != null) {
			predicateList.add(builder.equal(c.<Paciente>get("paciente").<Long>get("codigo"),
					filtro.getPaciente()));
		}
		
		if (filtro.getConsulteDate() != null) {
			predicateList.add(builder.greaterThanOrEqualTo(
					c.<LocalDate>get("consulteDate"), 
		            filtro.getConsulteDate()));
		}
		

		predicateList.add(builder.equal(c.<Status>get("status"),
				Status.ATIVO));

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(c).where(predArray);
		PaginacaoUtil.prepararOrdem(c, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<Consulta> consultas = typedQuery.getResultList();

		long totalConsultas = PaginacaoUtil.getTotalRegistros(c, predArray, builder, manager);

		Page<Consulta> page = new PageImpl<>(consultas, pageable, totalConsultas);

		return page;
	}




}
