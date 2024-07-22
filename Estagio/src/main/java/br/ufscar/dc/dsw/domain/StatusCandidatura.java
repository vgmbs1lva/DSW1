package br.ufscar.dc.dsw.domain;

public class StatusCandidatura {
    private int id;
    private String descricao;

    public StatusCandidatura() {
    }

    public StatusCandidatura(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
