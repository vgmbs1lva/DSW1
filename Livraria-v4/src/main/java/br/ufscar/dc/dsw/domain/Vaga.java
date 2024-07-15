package br.ufscar.dc.dsw.domain;

import java.text.DecimalFormat;

import org.w3c.dom.Text;

public class Vaga {
    private Long id;
    private Long empresa_id;
    private Text descricao;
    private DecimalFormat remuneracao;
    private String data_limite_inscricao;

    public Vaga(Long id, Long empresa_id, Text descricao, DecimalFormat remuneracao, String data_limite_inscricao) {
        this.id = id;
        this.empresa_id = empresa_id;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.data_limite_inscricao = data_limite_inscricao;
    
        DecimalFormat df = new DecimalFormat("#,##0.00");
        remuneracao.setDecimalFormatSymbols(df.getDecimalFormatSymbols());
        remuneracao.applyPattern("#,##0.00");

    }
    public Vaga(Long empresa_id, Text descricao, DecimalFormat remuneracao, String data_limite_inscricao) {
        this(null, empresa_id, descricao, remuneracao, data_limite_inscricao);
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getEmpresa_id() {
        return empresa_id;
    }
    public void setEmpresa_id(Long empresa_id) {
        this.empresa_id = empresa_id;
    }
    public Text getDescricao() {
        return descricao;
    }
    public void setDescricao(Text descricao) {
        this.descricao = descricao;
    }
    public DecimalFormat getRemuneracao() {
        return remuneracao;
    }
    public void setRemuneracao(DecimalFormat remuneracao) {
        this.remuneracao = remuneracao;
    }
    public String getData_limite_inscricao() {
        return data_limite_inscricao;
    }
    public void setData_limite_inscricao(String data_limite_inscricao) {
        this.data_limite_inscricao = data_limite_inscricao;
    }
    
        
    
}
