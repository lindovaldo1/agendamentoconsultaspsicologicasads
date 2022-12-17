package com.daw2.lindovaldo.repository.helper.paciente;

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
import org.springframework.util.StringUtils;

import com.daw2.lindovaldo.model.Paciente;
import com.daw2.lindovaldo.model.Status;
import com.daw2.lindovaldo.model.filter.PacienteFilter;
import com.daw2.lindovaldo.repository.pagination.PaginacaoUtil;

public class PacienteQueriesImpl implements PacienteQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Paciente> pesquisar(PacienteFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Paciente> criteriaQuery = builder.createQuery(Paciente.class);
		Root<Paciente> p = criteriaQuery.from(Paciente.class);
		TypedQuery<Paciente> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(p.<Long>get("codigo"),
					filtro.getCodigo()));
		}
		if (StringUtils.hasText(filtro.getNome())) {
			predicateList.add(builder.like(builder.lower(p.<String>get("nome")),
					"%" + filtro.getNome().toLowerCase() + "%"));
		}
		if (StringUtils.hasText(filtro.getCpf())) {
			predicateList.add(builder.like(builder.lower(p.<String>get("cpf")),
					"%" + filtro.getCpf().toLowerCase() + "%"));
		}
		predicateList.add(builder.equal(p.<Status>get("status"),
				Status.ATIVO));

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(p).where(predArray);
		PaginacaoUtil.prepararOrdem(p, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<Paciente> pacientes = typedQuery.getResultList();

		long totalPacientes = PaginacaoUtil.getTotalRegistros(p, predArray, builder, manager);

		Page<Paciente> page = new PageImpl<>(pacientes, pageable, totalPacientes);

		return page;
	}

}
