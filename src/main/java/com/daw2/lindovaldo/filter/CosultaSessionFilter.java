package com.daw2.lindovaldo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.daw2.lindovaldo.model.Consulta;

@Component
@Order(1)
public class CosultaSessionFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CosultaSessionFilter.class);

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession sessao = req.getSession(false); // false para nao criar uma caso nao exista
        if (sessao != null) {
            String url = req.getRequestURI();
            logger.info("Filtrando a URL: {}", url);
            if (!url.startsWith("/consultas") && !url.startsWith("/css")
                    && !url.startsWith("/js") && !url.startsWith("/images")) {
                Consulta consulta = (Consulta) sessao.getAttribute("consulta");
                if (consulta != null) {
                    consulta.setPsicologo(null);
                    consulta.setPaciente(null);
                    consulta.setConsulteDate(null);
                    consulta.setHora(null);
                    sessao.setAttribute("consulta", null);
                    logger.info("Limpou a consulta da sessao atual.");
                }
            }
        }
        chain.doFilter(request, response);  // deixa o atendimento normal da requisicao continuar.
    }

}