package br.ufscar.dc.dsw.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "Profissionais")
public class Profissional {

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
    @Size(min = 11, max = 11)
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String nome;

    public Profissional(){ 
    }
    
    public Profissional(String email, String senha, String cpf, String nome) {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profissional that = (Profissional) o;
        return id.equals(that.id) && email.equals(that.email) && cpf.equals(that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, cpf);
    }

    // toString

    @Override
    public String toString() {
        return "Profissional{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
