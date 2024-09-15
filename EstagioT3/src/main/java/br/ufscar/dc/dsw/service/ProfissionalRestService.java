package br.ufscar.dc.dsw.service;

import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalRestService {

    @Autowired
    private IProfissionalDAO dao;

    public List<Profissional> buscarTodos() {
        return dao.findAll();
    }

    public Optional<Profissional> buscarPorId(Long id) {
        return dao.findById(id);
    }

    public Profissional salvar(Profissional profissional) {
        return dao.save(profissional);  // Retorna o objeto salvo
    }

    public void deletar(Long id) {
        dao.deleteById(id);
    }

    public Optional<Profissional> buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }
}
