package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.ProfissionalRestService;
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
@RequestMapping("/api/profissionais")
public class ProfissionalRestController {

    @Autowired
    private ProfissionalRestService profissionalRestService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CREATE - Cria um novo profissional
    @PostMapping
    public ResponseEntity<Profissional> criarProfissional(@RequestBody Profissional profissional) {
        // Cria um novo usuário associado ao profissional
        Usuario usuario = new Usuario();
        usuario.setEmail(profissional.getEmail());
        usuario.setSenha(passwordEncoder.encode(profissional.getSenha()));
        usuario.setRole("ROLE_PROFISSIONAL");
        usuarioService.salvar(usuario);

        Profissional novoProfissional = profissionalRestService.salvar(profissional);
        return new ResponseEntity<>(novoProfissional, HttpStatus.CREATED);
    }

    // READ - Retorna a lista de todos os profissionais
    @GetMapping
    public ResponseEntity<List<Profissional>> listarProfissionais() {
        List<Profissional> profissionais = profissionalRestService.buscarTodos();
        return new ResponseEntity<>(profissionais, HttpStatus.OK);
    }

    // READ - Retorna um profissional específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Profissional> getProfissional(@PathVariable Long id) {
        Optional<Profissional> profissional = profissionalRestService.buscarPorId(id);
        if (profissional.isPresent()) {
            return new ResponseEntity<>(profissional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE - Atualiza um profissional por ID
    @PutMapping("/{id}")
    public ResponseEntity<Profissional> atualizarProfissional(@PathVariable Long id, @RequestBody Profissional profissionalAtualizado) {
        Optional<Profissional> profissionalOptional = profissionalRestService.buscarPorId(id);

        if (profissionalOptional.isPresent()) {
            Profissional profissionalExistente = profissionalOptional.get();
            profissionalExistente.setNome(profissionalAtualizado.getNome());
            profissionalExistente.setCpf(profissionalAtualizado.getCpf());
            profissionalExistente.setTelefone(profissionalAtualizado.getTelefone());
            profissionalExistente.setSexo(profissionalAtualizado.getSexo());
            profissionalExistente.setDataNascimento(profissionalAtualizado.getDataNascimento());

            // Atualizar o usuário associado se o email ou senha forem alterados
            Usuario usuario = usuarioService.buscarPorEmail(profissionalExistente.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o email: " + profissionalExistente.getEmail()));

            if (!profissionalAtualizado.getEmail().equals(usuario.getEmail())) {
                usuario.setEmail(profissionalAtualizado.getEmail());
            }

            if (profissionalAtualizado.getSenha() != null && !profissionalAtualizado.getSenha().isEmpty()) {
                usuario.setSenha(passwordEncoder.encode(profissionalAtualizado.getSenha()));
            }

            // Salvar o usuário atualizado
            usuarioService.salvar(usuario);

            // Atualizar o profissional
            profissionalExistente.setEmail(profissionalAtualizado.getEmail());

            Profissional atualizado = profissionalRestService.salvar(profissionalExistente);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Remove um profissional por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfissional(@PathVariable Long id) {
        Optional<Profissional> profissionalOptional = profissionalRestService.buscarPorId(id);

        if (profissionalOptional.isPresent()) {
            Profissional profissional = profissionalOptional.get();
            
            // Excluir o usuário associado ao profissional
            usuarioService.buscarPorEmail(profissional.getEmail())
                .ifPresent(usuario -> usuarioService.deletar(usuario.getId()));

            // Excluir o profissional
            profissionalRestService.deletar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
