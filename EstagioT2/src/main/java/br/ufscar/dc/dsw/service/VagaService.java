package br.ufscar.dc.dsw.service;

import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaService {

    @Autowired
    private IVagaDAO dao;

    public List<Vaga> buscarTodas() {
        return dao.findAll();
    }

    public Optional<Vaga> buscarPorId(Long id) {
        return dao.findById(id);
    }

    public void salvar(Vaga vaga) {
        dao.save(vaga);
    }

    public void deletar(Long id) {
        dao.deleteById(id);
    }

    public List<Vaga> buscarPorEmpresa(Empresa empresa) {
        return dao.findByEmpresa(empresa);
    }

    public List<Vaga> buscarPorCidade(String cidade) {
        return dao.findByCidade(cidade);
    }
}
