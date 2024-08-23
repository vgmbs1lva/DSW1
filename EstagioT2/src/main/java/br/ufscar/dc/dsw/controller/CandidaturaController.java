package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.CandidaturaService;
import br.ufscar.dc.dsw.service.ProfissionalService;
import br.ufscar.dc.dsw.service.UsuarioService;
import br.ufscar.dc.dsw.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import jakarta.validation.Valid;

import java.util.Optional;
import java.util.Date;

@Controller
@RequestMapping("/candidatura")
public class CandidaturaController {

    @Autowired
    private CandidaturaService candidaturaService;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private UsuarioService usuarioService; // Injetando UsuarioService
    

    @Autowired
    private VagaService vagaService;

    @GetMapping("/listar")
    public String listar(Authentication authentication, Model model) {

        // Verificar se o usuário está autenticado
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login?error=not_authenticated";
        }

        // Obter o e-mail do usuário logado
        String email = authentication.getName();

        // Buscar o profissional pelo e-mail
        Optional<Profissional> profissionalOpt = profissionalService.buscarPorEmail(email);
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
    public String candidatar(@PathVariable("vagaId") Long vagaId, Model model, Authentication authentication) {
        // Verificar se o usuário está logado
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login?error=not_authenticated";
        }
        // Verificar se o usuário é um profissional
        boolean isProfissional = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_PROFISSIONAL"));

        if (!isProfissional) {
            return "redirect:/login?error=not_authenticated";
        }
        // Buscar a vaga pelo ID
        Optional<Vaga> vagaOpt = vagaService.buscarPorId(vagaId);
        if (vagaOpt.isPresent()) {
            Vaga vaga = vagaOpt.get();
            
            // Obter o e-mail do usuário logado
            String email = authentication.getName();
            Optional<Profissional> profissionalOpt = profissionalService.buscarPorEmail(email);

            if (profissionalOpt.isPresent()) {
                Profissional profissional = profissionalOpt.get();

                // Verificar se o profissional já se candidatou a esta vaga
                Optional<Candidatura> candidaturaExistente = candidaturaService.buscarPorProfissionalEVaga(profissional, vaga);
                if (candidaturaExistente.isPresent()) {
                    // Redirecionar para uma página de erro ou exibir mensagem de erro
                    model.addAttribute("errorMessage", "Você já se candidatou a esta vaga.");
                    return "candidatura/erroCandidaturaDuplicada"; // Página de erro específica
                }

                Candidatura candidatura = new Candidatura();
                candidatura.setVaga(vaga);
                model.addAttribute("candidatura", candidatura);
                model.addAttribute("vaga", vaga);  // Adiciona a vaga ao modelo
                return "candidatura/cadastrar";
            } else {
                return "redirect:/erro";
            }
        } else {
            return "redirect:/erro";
        }
    }





    @PostMapping("/salvar")
    public String salvar(@Valid Candidatura candidatura,
                        BindingResult result,
                        @RequestParam("vagaId") Long vagaId,
                        @RequestParam("curriculo") String curriculo,
                        Model model,
                        Authentication authentication) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "candidatura/cadastrar";
        }
        // Verifique se o usuário está autenticado
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login?error=not_authenticated";
        }

        // Obter o e-mail do usuário logado
        String email = authentication.getName();
        // Buscar o usuário logado no banco de dados
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorEmail(email);
        if (usuarioOptional.isEmpty()) {
            return "redirect:/login?error=user_not_found";
        }
        Usuario usuario = usuarioOptional.get();

        // Verificar se o usuário logado é um profissional
        if (!"ROLE_PROFISSIONAL".equals(usuario.getRole())) {
            return "redirect:/login?error=not_professional";
        }
        // Buscar a vaga pelo ID
        Optional<Vaga> vagaOpt = vagaService.buscarPorId(vagaId);
        if (vagaOpt.isEmpty()) {
            model.addAttribute("errorMessage", "Vaga não encontrada.");
            return "error";
        }
        Vaga vaga = vagaOpt.get();

        // Preencher os detalhes da candidatura
        Optional<Profissional> profissionalOptional = profissionalService.buscarPorEmail(email);
        if (profissionalOptional.isPresent()) {
            Profissional profissional = profissionalOptional.get();
            candidatura.setProfissional(profissional);
        } else {
            return "redirect:/login?error=not_professional";
        }
        System.out.println("7777");
        candidatura.setVaga(vaga);
        candidatura.setDataCandidatura(new Date());
        candidatura.setStatus("Pendente");

        // Definir o conteúdo do currículo a partir do texto fornecido
        candidatura.setCurriculo(curriculo);

        // Salvar candidatura
        candidaturaService.salvar(candidatura);

        return "redirect:/candidatura/listar";
    }



    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") Long id) {
        candidaturaService.deletar(id);
        return "redirect:/candidatura/listar";
    }
}
