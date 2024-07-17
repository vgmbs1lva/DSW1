package br.ufscar.dc.dsw.domain;

public class Vaga {
    private Long id;
    private String cnpj;
    private String descricao;
    private Float remuneracao;
    private String dataLimiteInscricao;

    public Vaga() {
    }

    public Vaga(Long id) {
        this.id = id;
    }

    public Vaga(String cnpj, String descricao, Float remuneracao, String dataLimiteInscricao) {
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    public Vaga(Long id, String cnpj, String descricao, Float remuneracao, String dataLimiteInscricao) {
        this.id = id;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Float getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(Float remuneracao) {
        this.remuneracao = remuneracao;
    }

    public String getDataLimiteInscricao() {
        return dataLimiteInscricao;
    }

    public void setDataLimiteInscricao(String dataLimiteInscricao) {
        this.dataLimiteInscricao = dataLimiteInscricao;
    }
}
