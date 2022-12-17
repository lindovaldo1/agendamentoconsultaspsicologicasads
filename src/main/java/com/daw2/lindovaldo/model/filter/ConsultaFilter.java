package com.daw2.lindovaldo.model.filter;

import java.time.LocalDate;

public class ConsultaFilter {

    private Long codigo;
    private Long paciente;
    private Long psicologo;
    private LocalDate consulteDate;
    private String hora;
    private String status;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Long getPaciente() {
		return paciente;
	}
	public void setPaciente(Long paciente) {
		this.paciente = paciente;
	}
	public Long getPsicologo() {
		return psicologo;
	}
	public void setPsicologo(Long psicologo) {
		this.psicologo = psicologo;
	}
	public LocalDate getConsulteDate() {
		return consulteDate;
	}
	public void setConsulteDate(LocalDate consulteDate) {
		this.consulteDate = consulteDate;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    

}
