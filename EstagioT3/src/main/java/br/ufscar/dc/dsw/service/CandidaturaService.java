package br.ufscar.dc.dsw.service;

import br.ufscar.dc.dsw.dao.ICandidaturaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidaturaService {

    @Autowired
    private ICandidaturaDAO dao;

    public List<Candidatura> buscarPorProfissional(Profissional profissional) {
        return dao.findByProfissional(profissional);
    }

    public List<Candidatura> buscarPorVaga(Vaga vaga) {
        return dao.findByVaga(vaga);
    }

    public Candidatura buscarPorId(Long id) {
        return dao.findById(id).orElse(null);
    }

    public Optional<Candidatura> buscarPorProfissionalEVaga(Profissional profissional, Vaga vaga) {
        return dao.findByProfissionalAndVaga(profissional, vaga);
    }

    public void salvar(Candidatura candidatura) {
        dao.save(candidatura);
    }

    public void deletar(Long id) {
        dao.deleteById(id);
    }
}
