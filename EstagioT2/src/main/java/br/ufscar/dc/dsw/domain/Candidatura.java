package br.ufscar.dc.dsw.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Candidaturas")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String qualificacoes;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataCandidatura;

    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false)
    private String curriculo; // Caminho para o arquivo PDF do currículo

    @Size(max = 255)
    private String linkEntrevista;

    public Candidatura(){
    }

    public Candidatura(Profissional profissional, Vaga vaga, String qualificacoes, Date dataCandidatura, String status, String curriculo, String linkEntrevista) {
        this.profissional = profissional;
        this.vaga = vaga;
        this.qualificacoes = qualificacoes;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
        this.curriculo = curriculo;
        this.linkEntrevista = linkEntrevista;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getQualificacoes() {
        return qualificacoes;
    }

    public void setQualificacoes(String qualificacoes) {
        this.qualificacoes = qualificacoes;
    }

    public Date getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(Date dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public String getLinkEntrevista() {
        return linkEntrevista;
    }

    public void setLinkEntrevista(String linkEntrevista) {
        this.linkEntrevista = linkEntrevista;
    }

    // Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidatura that = (Candidatura) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString

    @Override
    public String toString() {
        return "Candidatura{" +
                "id=" + id +
                ", profissional=" + profissional +
                ", vaga=" + vaga +
                ", qualificacoes='" + qualificacoes + '\'' +
                ", dataCandidatura=" + dataCandidatura +
                ", status='" + status + '\'' +
                ", curriculo='" + curriculo + '\'' +
                '}';
    }
}
