package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.CandidaturaService;
import br.ufscar.dc.dsw.service.ProfissionalService;
import br.ufscar.dc.dsw.service.VagaService;
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
@RequestMapping("/candidatura")
public class CandidaturaController {

    @Autowired
    private CandidaturaService candidaturaService;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private VagaService vagaService;

    @GetMapping("/listar")
    public String listar(@AuthenticationPrincipal Principal principal, Model model) {
        Optional<Profissional> profissionalOpt = profissionalService.buscarPorEmail(principal.getName());
        if (profissionalOpt.isPresent()) {
            Profissional profissional = profissionalOpt.get();
            model.addAttribute("candidaturas", candidaturaService.buscarPorProfissional(profissional));
        } else {
            // Caso o profissional não seja encontrado, redirecionar ou exibir uma mensagem de erro
            return "redirect:/erro";
        }
        return "candidatura/listar";
    }

    @GetMapping("/candidatar/{vagaId}")
    public String candidatar(@PathVariable("vagaId") Long vagaId, @AuthenticationPrincipal Principal principal, Model model) {
        Optional<Profissional> profissionalOpt = profissionalService.buscarPorEmail(principal.getName());
        if (profissionalOpt.isPresent()) {
            Profissional profissional = profissionalOpt.get();
            Optional<Vaga> vagaOpt = vagaService.buscarPorId(vagaId);
            if (vagaOpt.isPresent()) {
                Vaga vaga = vagaOpt.get();
                Candidatura candidatura = new Candidatura();
                candidatura.setProfissional(profissional);
                candidatura.setVaga(vaga);
                model.addAttribute("candidatura", candidatura);
            } else {
                // Caso a vaga não seja encontrada, redirecionar ou exibir uma mensagem de erro
                return "redirect:/erro";
            }
        } else {
            // Caso o profissional não seja encontrado, redirecionar ou exibir uma mensagem de erro
            return "redirect:/erro";
        }
        return "candidatura/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Candidatura candidatura, BindingResult result) {
        if (result.hasErrors()) {
            return "candidatura/cadastrar";
        }
        candidaturaService.salvar(candidatura);
        return "redirect:/candidatura/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") Long id) {
        candidaturaService.deletar(id);
        return "redirect:/candidatura/listar";
    }
}
