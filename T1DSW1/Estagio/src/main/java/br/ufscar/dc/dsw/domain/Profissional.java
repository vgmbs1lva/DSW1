package br.ufscar.dc.dsw.domain;

public class Profissional {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private String sexo;
    private String dataNascimento;

    // Construtores
    public Profissional() {
    }

    public Profissional(String nome, String email, String senha, String cpf, String telefone, String sexo, String dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Profissional(int id, String nome, String email, String senha, String cpf, String telefone, String sexo, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Profissional(String nome, String cpf, String telefone, String sexo, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Profissional(int id, String nome, String cpf, String telefone, String sexo, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
