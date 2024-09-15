package br.ufscar.dc.dsw.service;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioDAO dao;

    public List<Usuario> buscarTodos() {
        return dao.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return dao.findById(id);
    }

    public void salvar(Usuario usuario) {
        dao.save(usuario);
    }

    public void deletar(Long id) {
        dao.deleteById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }
}
