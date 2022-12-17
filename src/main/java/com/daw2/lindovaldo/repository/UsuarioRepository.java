package com.daw2.lindovaldo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw2.lindovaldo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByNomeUsuarioIgnoreCase(String nomeUsuario);
	
}
