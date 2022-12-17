package com.daw2.lindovaldo.service;

import com.daw2.lindovaldo.model.Consulta;
import com.daw2.lindovaldo.repository.ConsultaRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;


    @Transactional
    public void salvar(Consulta consulta ) {
        consultaRepository.save(consulta);
    }

    @Transactional
    public void alterar(Consulta consulta) {
        consultaRepository.save(consulta);
    }

}
