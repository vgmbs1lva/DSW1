package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.service.EmpresaService;
import br.ufscar.dc.dsw.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;


import jakarta.validation.Valid;
import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping("/vaga")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/listar")
    public String listar(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            Optional<Empresa> empresaOpt = empresaService.buscarPorEmail(userDetails.getUsername());
            if (empresaOpt.isPresent()) {
                Empresa empresa = empresaOpt.get();
                model.addAttribute("vagas", vagaService.buscarPorEmpresa(empresa));
                return "vaga/listar";
            } else {
                // Caso a empresa não seja encontrada, redirecionar ou exibir uma mensagem de erro
                return "redirect:/erro";
            }
        } else {
            // Caso UserDetails seja nulo, redireciona para a página de login
            return "redirect:/login";
        }
    }


    @GetMapping("/cadastrar")
    public String cadastrar(Vaga vaga, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Verifica se o UserDetails não é nulo
        if (userDetails != null) {
            Optional<Empresa> empresaOpt = empresaService.buscarPorEmail(userDetails.getUsername());
            if (empresaOpt.isPresent()) {
                Empresa empresa = empresaOpt.get();
                vaga.setEmpresa(empresa);
                model.addAttribute("vaga", vaga);
                return "vaga/cadastrar";
            } else {
                // Caso a empresa não seja encontrada
                return "redirect:/erro";
            }
        } else {
            // Caso UserDetails seja nulo, redireciona para a página de login
            return "redirect:/login";
        }
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Vaga vaga, BindingResult result, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "vaga/cadastrar";
        }
        if (userDetails != null) {
            Optional<Empresa> empresaOpt = empresaService.buscarPorEmail(userDetails.getUsername());
            if (empresaOpt.isPresent()) {
                Empresa empresa = empresaOpt.get();
                vaga.setEmpresa(empresa);
                vagaService.salvar(vaga);
                return "redirect:/vaga/listar";
            } else {
                // Caso a empresa não seja encontrada
                return "redirect:/erro";
            }
        } else {
            // Caso UserDetails seja nulo, redireciona para a página de login
            return "redirect:/login";
        }
    }
    
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            Optional<Empresa> empresaOpt = empresaService.buscarPorEmail(userDetails.getUsername());
            Optional<Vaga> vagaOpt = vagaService.buscarPorId(id);

            if (empresaOpt.isPresent() && vagaOpt.isPresent()) {
                Empresa empresa = empresaOpt.get();
                Vaga vaga = vagaOpt.get();

                // Verifica se a vaga pertence à empresa logada
                if (vaga.getEmpresa().equals(empresa)) {
                    vagaService.deletar(id);
                    return "redirect:/vaga/listar";
                } else {
                    // Caso a vaga não pertença à empresa logada
                    return "redirect:/erro";
                }
            } else {
                // Caso a empresa ou a vaga não sejam encontradas
                return "redirect:/erro";
            }
        } else {
            // Caso UserDetails seja nulo, redireciona para a página de login
            return "redirect:/login";
        }
    }

    // Novo método para listar todas as vagas em aberto e permitir filtrar por cidade
    @GetMapping("/listarTodas")
    public String listarTodas(
            @RequestParam(value = "cidade", required = false) String cidade, 
            Model model, 
            Authentication authentication) {
        
        List<Vaga> vagas;
        if (cidade != null && !cidade.isEmpty()) {
            vagas = vagaService.buscarPorCidade(cidade);
        } else {
            vagas = vagaService.buscarTodas(); // Já retorna somente vagas em aberto
        }

        model.addAttribute("vagas", vagas);
        model.addAttribute("cidade", cidade);

        // Verificar se o usuário está autenticado
        if (authentication != null && authentication.isAuthenticated()) {
            // Verificar se o usuário é um profissional
            boolean isProfissional = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_PROFISSIONAL"));

            if (isProfissional) {
                return "vaga/listarTodasProfissional"; // Página para profissionais
            }
        }

        return "vaga/listarTodas"; // Página para visitantes ou não-profissionais
    }
}
