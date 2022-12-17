package com.daw2.lindovaldo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "psicologo")
@DynamicUpdate
public class Psicologo implements Serializable {

	@Id
	@SequenceGenerator(name = "generator2", sequenceName = "psicologo_code_seq", allocationSize = 1)
	@GeneratedValue(generator = "generator2", strategy = GenerationType.SEQUENCE)
	private Long codigo;

	@NotBlank(message = "Name is required")
	private String nome;

	@NotBlank(message = "CPF is required")
	private String cpf;

	@Enumerated(EnumType.STRING)
	private Specialty specialty;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long code) {
		this.codigo = code;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, cpf, nome, specialty);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Psicologo other = (Psicologo) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(nome, other.nome) && specialty == other.specialty;
	}

	@Override
	public String toString() {
		return "Psicologo [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", specialty=" + specialty + "]";
	}

}
