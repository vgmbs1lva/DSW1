package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface IVagaDAO extends JpaRepository<Vaga, Long> {
    List<Vaga> findByEmpresa(Empresa empresa);
    List<Vaga> findByCidade(String cidade);
    List<Vaga> findByDataLimiteInscricaoAfter(Date date); // Busca vagas em aberto
    List<Vaga> findByCidadeAndDataLimiteInscricaoAfter(String cidade, Date date); // Filtra vagas por cidade e em aberto
    List<Vaga> findByEmpresaId(Long empresaId);
    List<Vaga> findByEmpresaAndDataLimiteInscricaoAfter(Empresa empresa, Date dataLimiteInscricao);
}

