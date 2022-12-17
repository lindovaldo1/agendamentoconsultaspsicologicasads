package com.daw2.lindovaldo.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "consulta")
@DynamicUpdate
public class Consulta {

    @Id
    @SequenceGenerator(name = "generator3", sequenceName = "consulta_code_seq", allocationSize = 1)
    @GeneratedValue(generator = "generator3", strategy = GenerationType.SEQUENCE)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "codigo_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "codigo_psicologo")
    private Psicologo psicologo;

    @Column(name = "data")
    private LocalDate consulteDate;

    @Column(name = "horario")
    @Enumerated(EnumType.STRING)
    private Horario hora;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVO;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Psicologo getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(Psicologo psicologo) {
        this.psicologo = psicologo;
    }

    public LocalDate getConsulteDate() {
        return consulteDate;
    }

    public void setConsulteDate(LocalDate consulteDate) {
        this.consulteDate = consulteDate;
    }

    public Horario getHora() {
        return hora;
    }

    public void setHora(Horario hora) {
        this.hora = hora;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
        result = prime * result + ((psicologo == null) ? 0 : psicologo.hashCode());
        result = prime * result + ((consulteDate == null) ? 0 : consulteDate.hashCode());
        result = prime * result + ((hora == null) ? 0 : hora.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Consulta other = (Consulta) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        if (paciente == null) {
            if (other.paciente != null)
                return false;
        } else if (!paciente.equals(other.paciente))
            return false;
        if (psicologo == null) {
            if (other.psicologo != null)
                return false;
        } else if (!psicologo.equals(other.psicologo))
            return false;
        if (consulteDate == null) {
            if (other.consulteDate != null)
                return false;
        } else if (!consulteDate.equals(other.consulteDate))
            return false;
        if (hora != other.hora)
            return false;
        if (status != other.status)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Consulta [codigo=" + codigo + ", paciente=" + paciente + ", psicologo=" + psicologo + ", consulteDate="
                + consulteDate + ", hora=" + hora + ", status=" + status + "]";
    }

}
