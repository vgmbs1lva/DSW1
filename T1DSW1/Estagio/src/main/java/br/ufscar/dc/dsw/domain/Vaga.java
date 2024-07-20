package br.ufscar.dc.dsw.domain;

public class Vaga {
    private int id;
    private int idEmpresa;
    private String descricao;
    private double remuneracao;
    private String dataLimiteInscricao;

    // Construtor padrão
    public Vaga() {
    }

    // Construtor com todos os campos exceto o id
    public Vaga(int idEmpresa, String descricao, double remuneracao, String dataLimiteInscricao) {
        this.idEmpresa = idEmpresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    // Construtor com todos os campos
    public Vaga(int id, int idEmpresa, String descricao, double remuneracao, String dataLimiteInscricao) {
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    public Vaga(String descricao, double v, String dataLimiteInscricao) {
        this.descricao = descricao;
        this.remuneracao = v;
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    // Getters and Setters
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
}
