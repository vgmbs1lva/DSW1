package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVagaDAO extends JpaRepository<Vaga, Long> {
    List<Vaga> findByEmpresa(Empresa empresa);
    List<Vaga> findByCidade(String cidade);
}
