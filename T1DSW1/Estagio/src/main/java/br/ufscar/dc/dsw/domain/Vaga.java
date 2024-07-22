package br.ufscar.dc.dsw.domain;

public class Vaga {
    private int id;
    private int idEmpresa;
    private String descricao;
    private double remuneracao;
    private String dataLimiteInscricao;
    private String cidade;
    private Empresa empresa;

    public Vaga() {
    }

    public Vaga(int id, int idEmpresa, String descricao, double remuneracao, String dataLimiteInscricao, String cidade) {
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
        this.cidade = cidade;
    }

    public Vaga(Empresa empresa, String descricao, double remuneracao, String dataLimiteInscricao, String cidade) {
        this.empresa = empresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
        this.cidade = cidade;
    }

    public Vaga(int id, Empresa empresa, String descricao, double remuneracao, String dataLimiteInscricao, String cidade) {
        this.id = id;
        this.empresa = empresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
        this.cidade = cidade;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(double remuneracao) {
        this.remuneracao = remuneracao;
    }

    public String getDataLimiteInscricao() {
        return dataLimiteInscricao;
    }

    public void setDataLimiteInscricao(String dataLimiteInscricao) {
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
}
