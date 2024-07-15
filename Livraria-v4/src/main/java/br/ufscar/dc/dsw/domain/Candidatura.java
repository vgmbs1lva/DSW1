package br.ufscar.dc.dsw.domain;

public class Candidatura {
    private Long id;
    private Long vaga_id;
    private Long profissional_id;
    private String data_candidatura;
    private String status;
    private String curriculo_path;
    private String entrevista_horario;
    private String entrevista_link;

    public Candidatura(Long id, Long vaga_id, Long profissional_id, String data_candidatura, String status, String curriculo_path, String entrevista_horario, String entrevista_link) {
        this.id = id;
        this.vaga_id = vaga_id;
        this.profissional_id = profissional_id;
        this.data_candidatura = data_candidatura;
        this.status = status;
        this.curriculo_path = curriculo_path;
        this.entrevista_horario = entrevista_horario;
        this.entrevista_link = entrevista_link;
    }
    
    public Candidatura(Long vaga_id, Long profissional_id, String data_candidatura, String status, String curriculo_path, String entrevista_horario, String entrevista_link) {
        this(null, vaga_id, profissional_id, data_candidatura, status, curriculo_path, entrevista_horario, entrevista_link);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getVaga_id() {
        return vaga_id;
    }
    public void setVaga_id(Long vaga_id) {
        this.vaga_id = vaga_id;
    }
    public Long getProfissional_id() {
        return profissional_id;
    }
    public void setProfissional_id(Long profissional_id) {
        this.profissional_id = profissional_id;
    }
    public String getData_candidatura() {
        return data_candidatura;
    }
    public void setData_candidatura(String data_candidatura) {
        this.data_candidatura = data_candidatura;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCurriculo_path() {
        return curriculo_path;
    }
    public void setCurriculo_path(String curriculo_path) {
        this.curriculo_path = curriculo_path;
    }
    public String getEntrevista_horario() {
        return entrevista_horario;
    }
    public void setEntrevista_horario(String entrevista_horario) {
        this.entrevista_horario = entrevista_horario;
    }
    public String getEntrevista_link() {
        return entrevista_link;
    }
    public void setEntrevista_link(String entrevista_link) {
        this.entrevista_link = entrevista_link;
    }
    

    
}
