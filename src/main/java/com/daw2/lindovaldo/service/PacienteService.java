package com.daw2.lindovaldo.service;

import com.daw2.lindovaldo.model.Paciente;
import com.daw2.lindovaldo.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public void salvar(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Transactional
    public void alterar(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

}
