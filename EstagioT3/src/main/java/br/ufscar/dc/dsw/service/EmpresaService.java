package br.ufscar.dc.dsw.service;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private IEmpresaDAO dao;

    public List<Empresa> buscarTodos() {
        return dao.findAll();
    }

    public Optional<Empresa> buscarPorId(Long id) {
        return dao.findById(id);
    }
    
    @Transactional
    public void salvar(Empresa empresa) {
        dao.save(empresa);
    }

    public void deletar(Long id) {
        dao.deleteById(id);
    }

    public Optional<Empresa> buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }
}
