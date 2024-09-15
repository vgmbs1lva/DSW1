package br.ufscar.dc.dsw.service;

import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.domain.Vaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaRestService {

    @Autowired
    private IVagaDAO dao;

    public List<Vaga> buscarTodos() {
        return dao.findAll();
    }

    public Optional<Vaga> buscarPorId(Long id) {
        return dao.findById(id);
    }

    public Vaga salvar(Vaga vaga) {
        return dao.save(vaga); // Retorna a vaga salva
    }

    public void deletar(Long id) {
        dao.deleteById(id);
    }

    // Busca todas as vagas associadas a uma empresa
    public List<Vaga> buscarPorEmpresaId(Long empresaId) {
        return dao.findByEmpresaId(empresaId);
    }
}
