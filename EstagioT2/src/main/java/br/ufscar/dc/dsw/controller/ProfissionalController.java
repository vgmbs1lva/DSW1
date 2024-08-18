package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.ProfissionalService;
import br.ufscar.dc.dsw.service.UsuarioService;
import br.ufscar.dc.dsw.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("profissionais", profissionalService.buscarTodos());
        return "profissional/listar";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Profissional profissional) {
        return "profissional/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Profissional profissional, BindingResult result) {
        if (result.hasErrors()) {
            return "profissional/cadastrar";
        }

        // Cria um novo usuário associado ao profissional
        Usuario usuario = new Usuario();
        usuario.setEmail(profissional.getEmail());
        usuario.setSenha(passwordEncoder.encode(profissional.getSenha()));
        usuario.setRole("PROFISSIONAL");
        usuarioService.salvar(usuario);

        profissionalService.salvar(profissional);
        return "redirect:/profissional/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("profissional", profissionalService.buscarPorId(id));
        return "profissional/editar";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable("id") Long id, @Valid Profissional profissional, BindingResult result) {
        if (result.hasErrors()) {
            profissional.setId(id);
            return "profissional/editar";
        }
        profissionalService.salvar(profissional);
        return "redirect:/profissional/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") Long id) {
        profissionalService.deletar(id);
        return "redirect:/profissional/listar";
    }
}
