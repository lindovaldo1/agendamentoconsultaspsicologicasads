package com.daw2.lindovaldo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.daw2.lindovaldo.repository.PsicologoRepository;

@Service
public class PsicologoService {

	@Autowired
	private PsicologoRepository psicologoRepository; 
	
}
