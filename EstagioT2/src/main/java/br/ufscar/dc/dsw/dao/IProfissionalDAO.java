package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProfissionalDAO extends JpaRepository<Profissional, Long> {
    Optional<Profissional> findByEmail(String email);
}
