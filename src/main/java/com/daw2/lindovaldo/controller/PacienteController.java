package com.daw2.lindovaldo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw2.lindovaldo.model.Paciente;
import com.daw2.lindovaldo.model.Status;
import com.daw2.lindovaldo.model.filter.PacienteFilter;
import com.daw2.lindovaldo.repository.PacienteRepository;
import com.daw2.lindovaldo.service.PacienteService;

import pagination.PageWrapper;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger logger = LoggerFactory.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping("/abrirpesquisar")
    public String abrirPesquisa() {
        return "paciente/pesquisar";
    }

    @GetMapping("/pesquisar")
    public String pesquisar(PacienteFilter filtro, Model model,
            @PageableDefault(size = 10) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
            HttpServletRequest request) {
        Page<Paciente> pagina = pacienteRepository.pesquisar(filtro, pageable);
        PageWrapper<Paciente> paginaWrapper = new PageWrapper<>(pagina, request);
        model.addAttribute("pagina", paginaWrapper);
        return "paciente/mostrartodas";
    }

    @GetMapping("/cadastrar")
    public String abrirCadastro(Paciente paciente, Model model) {
        List<Paciente> pacientes = pacienteRepository.findByStatus(Status.ATIVO);
        model.addAttribute("pacientes", pacientes);
        return "paciente/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Paciente paciente, BindingResult resultado, Model model) {        

        if (resultado.hasErrors()) {
			logger.info("O paciente recebido para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			List<Paciente> pacientes = pacienteRepository.findByStatus(Status.ATIVO);
			model.addAttribute("pacientes", pacientes);
			return "paciente/cadastrar";
		} else {
			pacienteService.salvar(paciente);
			return "redirect:/pacientes/cadastrar/sucesso";
		}

    }

    @GetMapping("/cadastrar/sucesso")
    public String mostrarMensagemCadastroSucesso(Model model) {
        model.addAttribute("mensagem", "Cadastro de Paciente efetuado com sucesso.");
        return "mostrarmensagem";
    }

    @PostMapping("/abriralterar")
    public String abrirAlterar(Paciente paciente, Model model) {
        List<Paciente> pacientes = pacienteRepository.findAll();
        model.addAttribute("pacientes", pacientes);
        return "paciente/alterar";
    }

    @PostMapping("/alterar")
    public String alterar(@Valid Paciente paciente) {
        pacienteService.alterar(paciente);
        return "redirect:/pacientes/alterar/sucesso";
    }

    @GetMapping("/alterar/sucesso")
    public String mostrarMensagemAlterarSucesso(Model model) {
        model.addAttribute("mensagem", "Alteração do Paciente efetuada com sucesso.");
        return "mostrarmensagem";
    }

    @PostMapping("/abrirremover")
    public String abrirRemover(Paciente paciente) {
        return "paciente/remover";
    }

    @PostMapping("/remover")
    public String remover(Paciente paciente) {
        paciente.setStatus(Status.INATIVO);
        pacienteService.alterar(paciente);
        return "redirect:/pacientes/remover/sucesso";
    }

    @GetMapping("/remover/sucesso")
    public String mostrarMensagemRemoverSucesso(Model model) {
        model.addAttribute("mensagem", "Remoção (INATIVO) de Pessoa efetuada com sucesso.");
        return "mostrarmensagem";
    }

}
