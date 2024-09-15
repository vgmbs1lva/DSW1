package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.service.EmpresaRestService;
import br.ufscar.dc.dsw.service.UsuarioService;
import br.ufscar.dc.dsw.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaRestController {

    @Autowired
    private EmpresaRestService empresaRestService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CREATE - Cria uma nova empresa
    @PostMapping
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody Empresa empresa) {
        // Cria um novo usuário associado à empresa
        Usuario usuario = new Usuario();
        usuario.setEmail(empresa.getEmail());
        usuario.setSenha(passwordEncoder.encode(empresa.getSenha()));
        usuario.setRole("ROLE_EMPRESA");
        usuarioService.salvar(usuario);

        Empresa novaEmpresa = empresaRestService.salvar(empresa);
        return new ResponseEntity<>(novaEmpresa, HttpStatus.CREATED);
    }

    // READ - Retorna a lista de todas as empresas
    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        List<Empresa> empresas = empresaRestService.buscarTodos();
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    // READ - Retorna uma empresa específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaRestService.buscarPorId(id);
        if (empresa.isPresent()) {
            return new ResponseEntity<>(empresa.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // READ - Retorna a lista de empresas por cidade
    @GetMapping("/cidades/{nome}")
    public ResponseEntity<List<Empresa>> listarEmpresasPorCidade(@PathVariable String nome) {
        List<Empresa> empresas = empresaRestService.buscarPorCidade(nome);
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    // UPDATE - Atualiza uma empresa por ID
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        Optional<Empresa> empresaOptional = empresaRestService.buscarPorId(id);

        if (empresaOptional.isPresent()) {
            Empresa empresaExistente = empresaOptional.get();
            empresaExistente.setNome(empresaAtualizada.getNome());
            empresaExistente.setCnpj(empresaAtualizada.getCnpj());
            empresaExistente.setDescricao(empresaAtualizada.getDescricao());
            empresaExistente.setCidade(empresaAtualizada.getCidade());

            // Atualizar o usuário associado se o email ou senha forem alterados
            Usuario usuario = usuarioService.buscarPorEmail(empresaExistente.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o email: " + empresaExistente.getEmail()));

            if (!empresaAtualizada.getEmail().equals(usuario.getEmail())) {
                usuario.setEmail(empresaAtualizada.getEmail());
            }

            if (empresaAtualizada.getSenha() != null && !empresaAtualizada.getSenha().isEmpty()) {
                usuario.setSenha(passwordEncoder.encode(empresaAtualizada.getSenha()));
            }

            // Salvar o usuário atualizado
            usuarioService.salvar(usuario);

            // Atualizar a empresa
            empresaExistente.setEmail(empresaAtualizada.getEmail());

            Empresa atualizado = empresaRestService.salvar(empresaExistente);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Remove uma empresa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = empresaRestService.buscarPorId(id);

        if (empresaOptional.isPresent()) {
            Empresa empresa = empresaOptional.get();
            
            // Excluir o usuário associado à empresa
            usuarioService.buscarPorEmail(empresa.getEmail())
                .ifPresent(usuario -> usuarioService.deletar(usuario.getId()));

            // Excluir a empresa
            empresaRestService.deletar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
