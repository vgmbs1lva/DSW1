package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICandidaturaDAO extends JpaRepository<Candidatura, Long> {
    List<Candidatura> findByProfissional(Profissional profissional);
    List<Candidatura> findByVaga(Vaga vaga);
    Optional<Candidatura> findByProfissionalAndVaga(Profissional profissional, Vaga vaga);
}
