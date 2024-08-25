package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.service.CandidaturaService;
import br.ufscar.dc.dsw.service.EmpresaService;
import br.ufscar.dc.dsw.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.Valid;
import java.util.Optional;
import java.util.List;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/vaga")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private CandidaturaService candidaturaService;

    @GetMapping("/listar")
    public String listar(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            Optional<Empresa> empresaOpt = empresaService.buscarPorEmail(userDetails.getUsername());
            if (empresaOpt.isPresent()) {
                Empresa empresa = empresaOpt.get();
                List<Vaga> vagas = vagaService.buscarPorEmpresa(empresa);

                model.addAttribute("vagas", vagas);
                return "vaga/listar";
            } else {
                return "redirect:/erro";
            }
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/candidatos/{id}")
    public String listarCandidatos(@PathVariable("id") Long id, Model model) {
        Optional<Vaga> vagaOpt = vagaService.buscarPorId(id);
        if (vagaOpt.isPresent()) {
            Vaga vaga = vagaOpt.get();
            List<Candidatura> candidaturas = vaga.getCandidaturas();
            Date hoje = new Date();
            boolean podeEditar = vaga.getDataLimiteInscricao().before(hoje);

            model.addAttribute("vaga", vaga);
            model.addAttribute("candidaturas", candidaturas);
            model.addAttribute("podeEditar", podeEditar); // Passa a informação se pode editar ou não
            return "vaga/listarCandidatos";
        } else {
            return "redirect:/erro";
        }
    }

    @PostMapping("/candidatos/{id}/editar")
    public String updateCandidaturaStatus(@PathVariable("id") Long id, @RequestParam Map<String, String> allParams) {
        Optional<Vaga> vagaOpt = vagaService.buscarPorId(id);
        if (vagaOpt.isPresent()) {
            Vaga vaga = vagaOpt.get();
            Date hoje = new Date();

            if (vaga.getDataLimiteInscricao().before(hoje)) {
                for (Candidatura candidatura : vaga.getCandidaturas()) {
                    String status = allParams.get("status_" + candidatura.getId());
                    if (status != null) {
                        candidatura.setStatus(status);
                        candidaturaService.salvar(candidatura);
                    }
                }
            }
            return "redirect:/vaga/candidatos/" + id;
        }
        return "redirect:/vaga/listar";
    }


    @GetMapping("/cadastrar")
    public String cadastrar(Vaga vaga, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            Optional<Empresa> empresaOpt = empresaService.buscarPorEmail(userDetails.getUsername());
            if (empresaOpt.isPresent()) {
                Empresa empresa = empresaOpt.get();
                vaga.setEmpresa(empresa);
                model.addAttribute("vaga", vaga);
                return "vaga/cadastrar";
            } else {
                return "redirect:/erro";
            }
        } else {
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
                return "redirect:/erro";
            }
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model,
                               @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Vaga> vagaOpt = vagaService.buscarPorId(id);
        if (userDetails != null && vagaOpt.isPresent()) {
            Optional<Empresa> empresaOpt = empresaService.buscarPorEmail(userDetails.getUsername());
            Vaga vaga = vagaOpt.get();
            if (empresaOpt.isPresent() && vaga.getEmpresa().equals(empresaOpt.get())) {
                model.addAttribute("vaga", vaga);
                return "vaga/editar";
            } else {
                return "redirect:/erro";
            }
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/editar/{id}")
    public String updateVaga(@PathVariable("id") Long id, @Valid Vaga vaga, BindingResult result,
                             @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "vaga/editar";
        }
        if (userDetails != null) {
            Optional<Empresa> empresaOpt = empresaService.buscarPorEmail(userDetails.getUsername());
            Optional<Vaga> vagaExistenteOpt = vagaService.buscarPorId(id);

            if (empresaOpt.isPresent() && vagaExistenteOpt.isPresent()) {
                Vaga vagaExistente = vagaExistenteOpt.get();
                Empresa empresa = empresaOpt.get();
                vaga.setEmpresa(empresa);

                // Preservar a lista de candidaturas da vaga existente
                vaga.setCandidaturas(vagaExistente.getCandidaturas());

                vagaService.salvar(vaga);
                return "redirect:/vaga/listar";
            } else {
                return "redirect:/erro";
            }
        } else {
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

                if (vaga.getEmpresa().equals(empresa)) {
                    vagaService.deletar(id);
                    return "redirect:/vaga/listar";
                } else {
                    return "redirect:/erro";
                }
            } else {
                return "redirect:/erro";
            }
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/listarTodas")
    public String listarTodas(
            @RequestParam(value = "cidade", required = false) String cidade,
            Model model,
            Authentication authentication) {

        List<Vaga> vagas;
        if (cidade != null && !cidade.isEmpty()) {
            vagas = vagaService.buscarPorCidade(cidade);
        } else {
            vagas = vagaService.buscarTodas();
        }

        model.addAttribute("vagas", vagas);
        model.addAttribute("cidade", cidade);

        if (authentication != null && authentication.isAuthenticated()) {
            boolean isProfissional = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_PROFISSIONAL"));

            if (isProfissional) {
                return "vaga/listarTodasProfissional";
            }
        }

        return "vaga/listarTodas";
    }
}
