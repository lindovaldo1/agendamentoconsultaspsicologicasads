package com.daw2.lindovaldo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw2.lindovaldo.model.Psicologo;
import com.daw2.lindovaldo.model.filter.PsicologoFilter;
import com.daw2.lindovaldo.repository.PsicologoRepository;
import com.daw2.lindovaldo.service.PsicologoService;

import pagination.PageWrapper;

@Controller
@RequestMapping("/psicologos")
public class PsicologoController {

	@Autowired
	private PsicologoService psicologoService;

	@Autowired
	private PsicologoRepository psicologoRepository;

	@GetMapping("/abrirpesquisar")
	public String abrirPesquisa() {
		return "psicologo/pesquisar";
	}

	@GetMapping("/pesquisar")
	public String pesquisar(PsicologoFilter filtro, Model model,
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
		Page<Psicologo> pagina = psicologoRepository.pesquisar(filtro, pageable);
		PageWrapper<Psicologo> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "psicologo/mostrartodas";
	}

}
