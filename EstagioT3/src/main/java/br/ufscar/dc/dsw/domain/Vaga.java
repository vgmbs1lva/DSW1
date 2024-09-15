package br.ufscar.dc.dsw.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "Vagas")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String descricao;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true) // O valor mínimo deve ser maior que 0
    @Digits(integer = 6, fraction = 2)  // Até 6 dígitos inteiros e 2 dígitos fracionários
    @Column(nullable = false, precision = 8, scale = 2)  // Precision define o número total de dígitos, scale define a quantidade de dígitos fracionários
    private BigDecimal remuneracao;


    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataLimiteInscricao;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Candidatura> candidaturas;

    public Vaga() {
        this.candidaturas = new ArrayList<>();
    }

    public Vaga(String descricao, BigDecimal remuneracao, Date dataLimiteInscricao, String cidade, Empresa empresa) {
        this();
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
        this.cidade = cidade;
        this.empresa = empresa;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(BigDecimal remuneracao) {
        this.remuneracao = remuneracao;
    }

    public Date getDataLimiteInscricao() {
        return dataLimiteInscricao;
    }

    public void setDataLimiteInscricao(Date dataLimiteInscricao) {
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public void setCandidaturas(List<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }

    public String getCnpjEmpresa() {
        return empresa != null ? empresa.getCnpj() : null;
    }

    // Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return id.equals(vaga.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString

    @Override
    public String toString() {
        return "Vaga{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", remuneracao=" + remuneracao +
                ", dataLimiteInscricao=" + dataLimiteInscricao +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
