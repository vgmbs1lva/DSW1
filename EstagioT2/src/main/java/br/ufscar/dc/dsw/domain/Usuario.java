package br.ufscar.dc.dsw.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 64)
    private String senha;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String role;

    public Usuario(){
        
    }

    public Usuario(String email, String senha, String role) {
        this.email = email;
        this.senha = senha;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) && email.equals(usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    // toString

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
