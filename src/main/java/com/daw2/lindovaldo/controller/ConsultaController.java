package com.daw2.lindovaldo.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.daw2.lindovaldo.model.Consulta;
import com.daw2.lindovaldo.model.Horario;
import com.daw2.lindovaldo.model.Paciente;
import com.daw2.lindovaldo.model.Psicologo;
import com.daw2.lindovaldo.model.Status;
import com.daw2.lindovaldo.model.filter.ConsultaFilter;
import com.daw2.lindovaldo.model.filter.PacienteFilter;
import com.daw2.lindovaldo.model.filter.PsicologoFilter;
import com.daw2.lindovaldo.repository.ConsultaRepository;
import com.daw2.lindovaldo.repository.PacienteRepository;
import com.daw2.lindovaldo.repository.PsicologoRepository;
import com.daw2.lindovaldo.service.ConsultaService;

import pagination.PageWrapper;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private PsicologoRepository psicologoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    @GetMapping("/cadastrar")
	public String abrirCadastro(HttpSession sessao) {
		Consulta consulta = buscarConsultaNaSessao(sessao);
		sessao.setAttribute("consulta", consulta);
		return "consulta/cadastrar";
	}

	@GetMapping("/abrirpesquisar")
    public String abrirPesquisa() {
        return "consulta/pesquisar";
    }

	@GetMapping("/pesquisar")
	public String pesquisar(ConsultaFilter filtro, Model model,
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
		Page<Consulta> pagina = consultaRepository.pesquisar(filtro, pageable, false);
		PageWrapper<Consulta> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "consulta/mostrartodas";
	}


    private Consulta buscarConsultaNaSessao(HttpSession sessao) {
		Consulta consulta = (Consulta) sessao.getAttribute("consulta");
		if (consulta == null) {
			consulta = new Consulta();
		}
		return consulta;
	}
	
	@GetMapping("/abrirescolherpsicologo")
	public String abrirEscolhaPsicologo() {
		return "consulta/escolherpsicologo";
	}
	
	@GetMapping("/pesquisarpsicologo")
	public String pesquisarPessoa(PsicologoFilter filtro, Model model,
			@PageableDefault(size = 10) 
    		@SortDefault(sort = "codigo", direction = Sort.Direction.ASC)
    		Pageable pageable, HttpServletRequest request) {
		
		Page<Psicologo> pagina = psicologoRepository.pesquisar(filtro, pageable);
		PageWrapper<Psicologo> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		
		return "consulta/mostrarpsicologo";
	}
	
	@PostMapping("/escolherpsicologo")
	public String escolherPsicologo(Psicologo psicologo, HttpSession sessao) {
		Consulta consulta = buscarConsultaNaSessao(sessao);
		consulta.setPsicologo(psicologo);
		sessao.setAttribute("consulta", consulta);
		return "consulta/cadastrar";
	}
		
	@GetMapping("/abrirescolherpaciente")
	public String abrirEscolhaPaciente() {
		return "consulta/escolherpaciente";
	}
	
	@GetMapping("/pesquisarpaciente")
	public String pesquisarPaciente(PacienteFilter filtro, Model model,
			@PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
		Page<Paciente> pagina = pacienteRepository.pesquisar(filtro, pageable);
		PageWrapper<Paciente> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "consulta/mostrarpaciente";
	}
	
	@PostMapping("/escolherpaciente")
	public String escolherPaciente(Paciente paciente, HttpSession sessao) {
		Consulta consulta = buscarConsultaNaSessao(sessao);
		consulta.setPaciente(paciente);
		sessao.setAttribute("consulta", consulta);
		return "consulta/cadastrar";
	}
	
	@GetMapping("/abrirescolherdiahora")
	public String abrirEscolhaDiaHora() {
		return "consulta/escolherdiahora";
	}

	@PostMapping("/escolherdiahora")
	public String escolherDiaHora(Horario horario, Date consulteDate, HttpSession sessao) {

		LocalDate date = consulteDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Consulta consulta = buscarConsultaNaSessao(sessao);
		List<Consulta> consultas = consultaRepository.findByPsicologo(consulta.getPsicologo());
		consulta.setHora(horario);
		consulta.setConsulteDate(date);

		if(!(consulta.getConsulteDate().isAfter(LocalDate.now()))){
			return "redirect:/consultas/cadastro/errodata";
		}

		System.out.println(consulta.toString());
		sessao.setAttribute("consulta", consulta);
		return "consulta/cadastrar";
	}

	@GetMapping("/efetuarcadastro")
	public String cadastrar(HttpSession sessao, SessionStatus status) {
		Consulta consulta = buscarConsultaNaSessao(sessao);  
		consultaService.salvar(consulta);
		return "redirect:/consultas/cadastro/sucesso";
	}
	
	@GetMapping("/cadastro/sucesso")
	public String mostrarMensagemCadastroSucesso(Model model) {
		model.addAttribute("mensagem", "Agendamento de Consulta efetuado com sucesso.");
		return "mostrarmensagem";
	}
	
	@GetMapping("/cadastro/errodata")
	public String mostrarMensagemCadastroErroData(Model model) {
		model.addAttribute("mensagem", "Agendamento de Consulta não efetuado! A data deve ser posterior a data atual.");
		return "mostrarmensagem";
	}

	@GetMapping("/cadastro/errohora")
	public String mostrarMensagemCadastroErroHora(Model model) {
		model.addAttribute("mensagem", "Agendamento de Consulta não efetuado! Esse horario não está disponivel.");
		return "mostrarmensagem";
	}

}
