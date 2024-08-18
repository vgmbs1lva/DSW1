package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.service.VagaService;
import br.ufscar.dc.dsw.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/vaga")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("vagas", vagaService.buscarTodas());
        return "vaga/listar";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Vaga vaga, Model model, @AuthenticationPrincipal Principal principal) {
        Optional<Empresa> empresaOpt = empresaService.buscarPorEmail(principal.getName());
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            vaga.setEmpresa(empresa);
            model.addAttribute("vaga", vaga);
            return "vaga/cadastrar";
        } else {
            // Caso a empresa não seja encontrada, redirecionar ou exibir uma mensagem de erro
            return "redirect:/erro";
        }
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Vaga vaga, BindingResult result, @AuthenticationPrincipal Principal principal) {
        if (result.hasErrors()) {
            return "vaga/cadastrar";
        }
        Optional<Empresa> empresaOpt = empresaService.buscarPorEmail(principal.getName());
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            vaga.setEmpresa(empresa);
            vagaService.salvar(vaga);
            return "redirect:/vaga/listar";
        } else {
            // Caso a empresa não seja encontrada, redirecionar ou exibir uma mensagem de erro
            return "redirect:/erro";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("vaga", vagaService.buscarPorId(id));
        return "vaga/editar";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable("id") Long id, @Valid Vaga vaga, BindingResult result) {
        if (result.hasErrors()) {
            vaga.setId(id);
            return "vaga/editar";
        }
        vagaService.salvar(vaga);
        return "redirect:/vaga/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") Long id) {
        vagaService.deletar(id);
        return "redirect:/vaga/listar";
    }
}
