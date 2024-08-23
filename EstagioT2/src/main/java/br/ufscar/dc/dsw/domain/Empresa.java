package br.ufscar.dc.dsw.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false, length = 64)
    private String senha;

    @NotBlank
    @Size(min = 14, max = 14)
    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String descricao;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String cidade;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vaga> vagas;

    public Empresa(){
        
    }

    public Empresa(String email, String senha, String cnpj, String nome, String descricao, String cidade) {
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.nome = nome;
        this.descricao = descricao;
        this.cidade = cidade;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }

    // Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return id.equals(empresa.id) && email.equals(empresa.email) && cnpj.equals(empresa.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, cnpj);
    }

    // toString

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
