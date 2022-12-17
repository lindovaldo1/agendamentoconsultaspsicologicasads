package com.daw2.lindovaldo.repository.helper.psicologo;

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

import com.daw2.lindovaldo.model.Psicologo;
import com.daw2.lindovaldo.model.Specialty;
import com.daw2.lindovaldo.model.filter.PsicologoFilter;
import com.daw2.lindovaldo.repository.pagination.PaginacaoUtil;

public class PsicologoQueriesImpl implements PsicologoQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Psicologo> pesquisar(PsicologoFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Psicologo> criteriaQuery = builder.createQuery(Psicologo.class);
		Root<Psicologo> p = criteriaQuery.from(Psicologo.class);
		TypedQuery<Psicologo> typedQuery;
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
		if (StringUtils.hasText(filtro.getSpecialty())) {
			predicateList.add(builder.equal(p.<Specialty>get("specialty"),
					Specialty.valueOf(filtro.getSpecialty().toUpperCase())));

		}
		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(p).where(predArray);
		PaginacaoUtil.prepararOrdem(p, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<Psicologo> psicologos = typedQuery.getResultList();

		long totalPsicologs = PaginacaoUtil.getTotalRegistros(p, predArray, builder, manager);

		Page<Psicologo> page = new PageImpl<>(psicologos, pageable, totalPsicologs);

		return page;
	}

}
