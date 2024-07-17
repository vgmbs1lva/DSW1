package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Candidatura {
    private Long id;
    private Long vagaId;
    private Long profissionalId;
    private Date dataCandidatura;
    private String curriculoPath;
    private String status;

    public Candidatura() {
    }

    public Candidatura(Long id) {
        this.id = id;
    }

    public Candidatura(Long vagaId, Long profissionalId, Date dataCandidatura, String curriculoPath, String status) {
        this.vagaId = vagaId;
        this.profissionalId = profissionalId;
        this.dataCandidatura = dataCandidatura;
        this.curriculoPath = curriculoPath;
        this.status = status;
    }

    public Candidatura(Long id, Long vagaId, Long profissionalId, Date dataCandidatura, String curriculoPath, String status) {
        this.id = id;
        this.vagaId = vagaId;
        this.profissionalId = profissionalId;
        this.dataCandidatura = dataCandidatura;
        this.curriculoPath = curriculoPath;
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVagaId() {
        return vagaId;
    }

    public void setVagaId(Long vagaId) {
        this.vagaId = vagaId;
    }

    public Long getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(Long profissionalId) {
        this.profissionalId = profissionalId;
    }

    public Date getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(Date dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public String getCurriculoPath() {
        return curriculoPath;
    }

    public void setCurriculoPath(String curriculoPath) {
        this.curriculoPath = curriculoPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
