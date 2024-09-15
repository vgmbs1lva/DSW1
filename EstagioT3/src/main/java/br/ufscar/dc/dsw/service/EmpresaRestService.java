package br.ufscar.dc.dsw.service;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaRestService {

    @Autowired
    private IEmpresaDAO dao;

    public List<Empresa> buscarTodos() {
        return dao.findAll();
    }

    public Optional<Empresa> buscarPorId(Long id) {
        return dao.findById(id);
    }

    public Empresa salvar(Empresa empresa) {
        return dao.save(empresa);  // Retorna o objeto salvo
    }

    public void deletar(Long id) {
        dao.deleteById(id);
    }

    public List<Empresa> buscarPorCidade(String cidade) {
        return dao.findByCidade(cidade);
    }

    public Optional<Empresa> buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }
}
