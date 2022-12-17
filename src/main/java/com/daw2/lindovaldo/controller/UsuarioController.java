package com.daw2.lindovaldo.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daw2.lindovaldo.ajax.NotificacaoAlertify;
import com.daw2.lindovaldo.ajax.TipoNotificacaoAlertify;
import com.daw2.lindovaldo.model.Papel;
import com.daw2.lindovaldo.model.Usuario;
import com.daw2.lindovaldo.repository.PapelRepository;
import com.daw2.lindovaldo.service.CadastroUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	private static final Logger logger = LoggerFactory.getLogger(ConsultaController.class);
	
	@Autowired
	private PapelRepository papelRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/cadastrar")
	public String abrirCadastroUsuario(Usuario usuario, Model model) {
		List<Papel> papeis = papelRepository.findAll();
		model.addAttribute("todosPapeis", papeis);
		return "usuario/cadastrar";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrarNovoUsuario(@Valid Usuario usuario, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			logger.info("O usuario recebido para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			List<Papel> papeis = papelRepository.findAll();
			model.addAttribute("todosPapeis", papeis);
			return "usuario/cadastrar";
		} else {
			usuario.setAtivo(true);
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			cadastroUsuarioService.salvar(usuario);
			return "redirect:/usuarios/cadastrosucesso";
		}
	}
	
	@GetMapping("/cadastrosucesso")
	public String mostrarCadastroSucesso(Usuario usuario, Model model) {
		List<Papel> papeis = papelRepository.findAll();
		model.addAttribute("todosPapeis", papeis);
		NotificacaoAlertify notificacao =
				new NotificacaoAlertify("Cadastro de usuário efetuado com sucesso.",
						                TipoNotificacaoAlertify.SUCESSO);
		model.addAttribute("notificacao", notificacao);
		return "usuario/cadastrar";
	}
}
