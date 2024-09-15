package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface IEmpresaDAO extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByEmail(String email);
    List<Empresa> findByCidade(String cidade);
}
