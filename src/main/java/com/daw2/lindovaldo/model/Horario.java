package com.daw2.lindovaldo.model;

public enum Horario {

    PRIMEIRA("08:00 - 08:50"),
    SEGUNDA("09:00 - 09:50"),
    TERCEIRA("10:00 - 10:50"),
    QUARTA("11:00 - 11:50"),
    QUINTA("13:00 - 13:50"),
    SEXTA("14:00 - 14:50"),
    SETIMA("15:00 - 15:50"),
    OITAVA("16:00 - 16:50");

    private String descricao;

    private Horario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
