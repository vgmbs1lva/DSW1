package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;

public class Candidatura {
    private int id;
    private Profissional profissional;
    private Vaga vaga;
    private StatusCandidatura status;
    private LocalDate dataCandidatura;
    private String curriculo; // Para armazenar o currículo em PDF

    public Candidatura() {
    }

    public Candidatura(Profissional profissional, Vaga vaga, StatusCandidatura status, String curriculo) {
        this.profissional = profissional;
        this.vaga = vaga;
        this.status = status;
        this.dataCandidatura = LocalDate.now();
        this.curriculo = curriculo;
    }

    public Candidatura(int id, Profissional profissional, Vaga vaga, StatusCandidatura status, LocalDate dataCandidatura, String curriculo) {
        this.id = id;
        this.profissional = profissional;
        this.vaga = vaga;
        this.status = status;
        this.dataCandidatura = dataCandidatura;
        this.curriculo = curriculo;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public StatusCandidatura getStatus() {
        return status;
    }

    public void setStatus(StatusCandidatura status) {
        this.status = status;
    }

    public LocalDate getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(LocalDate dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }
}
