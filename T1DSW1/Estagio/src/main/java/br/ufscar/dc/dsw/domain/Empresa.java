package br.ufscar.dc.dsw.domain;

public class Empresa {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cnpj;
    private String descricao;
    private String cidade;

    // Construtor padrão
    public Empresa() {
    }

    // Construtor com todos os campos exceto o id
    public Empresa(String nome, String email, String senha, String cnpj, String descricao, String cidade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.cidade = cidade;
    }

    // Construtor com todos os campos
    public Empresa(int id, String nome, String email, String senha, String cnpj, String descricao, String cidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.cidade = cidade;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
}
