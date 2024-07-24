package br.ufscar.dc.dsw.domain;

public class Usuario {
    private int id;
    private String email;
    private String senha;
    private String tipo;
    private Integer idProfissional;
    private Integer idEmpresa;

    public Usuario() {
    }
    
    // Construtor com todos os parâmetros
    public Usuario(int id, String email, String senha, String tipo, Integer idProfissional, Integer idEmpresa) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.idProfissional = idProfissional;
        this.idEmpresa = idEmpresa;
    }

    // Construtor sem ID (para inserção)
    public Usuario(String email, String senha, String tipo, Integer idProfissional, Integer idEmpresa) {
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.idProfissional = idProfissional;
        this.idEmpresa = idEmpresa;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Integer idProfissional) {
        this.idProfissional = idProfissional;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
