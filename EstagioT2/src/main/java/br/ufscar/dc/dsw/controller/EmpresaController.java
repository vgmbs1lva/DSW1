package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.service.EmpresaService;
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
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("empresas", empresaService.buscarTodos());
        return "empresa/listar";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Empresa empresa) {
        return "empresa/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Empresa empresa, BindingResult result) {
        if (result.hasErrors()) {
            return "empresa/cadastrar";
        }

        // Cria um novo usuário associado à empresa
        Usuario usuario = new Usuario();
        usuario.setEmail(empresa.getEmail());
        usuario.setSenha(passwordEncoder.encode(empresa.getSenha()));
        usuario.setRole("EMPRESA");
        usuarioService.salvar(usuario);

        empresaService.salvar(empresa);
        return "redirect:/empresa/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("empresa", empresaService.buscarPorId(id));
        return "empresa/editar";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable("id") Long id, @Valid Empresa empresa, BindingResult result) {
        if (result.hasErrors()) {
            empresa.setId(id);
            return "empresa/editar";
        }
        empresaService.salvar(empresa);
        return "redirect:/empresa/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") Long id) {
        empresaService.deletar(id);
        return "redirect:/empresa/listar";
    }
}
