package br.ufscar.dc.dsw.domain;

import org.w3c.dom.Text;

public class Empresa {
    private Long id;
    private String email;
    private String senha;
    private String cnpj;
    private String nome;
    private Text descricao;
    private String cidade;

    public Empresa(Long id, String email, String senha, String cnpj, String nome, Text descricao, String cidade) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.nome = nome;
        this.descricao = descricao;
        this.cidade = cidade;
    }
    public Empresa(String email, String senha, String cnpj, String nome, Text descricao, String cidade) {
        this(null, email, senha, cnpj, nome, descricao, cidade);
    }

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
    public Text getDescricao() {
        return descricao;
    }
    public void setDescricao(Text descricao) {
        this.descricao = descricao;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    

    
}
