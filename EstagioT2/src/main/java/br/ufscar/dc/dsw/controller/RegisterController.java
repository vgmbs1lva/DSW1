package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.EmpresaService;
import br.ufscar.dc.dsw.service.ProfissionalService;
import br.ufscar.dc.dsw.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @GetMapping("/empresa")
    public String showRegisterEmpresaForm(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "register/registerEmpresa"; // Aponta para o template correto na pasta register
    }

    @PostMapping("/empresa")
    public String registerEmpresa(@Valid @ModelAttribute("empresa") Empresa empresa, BindingResult result) {
        logger.info("Received registration request for Empresa");
        if (result.hasErrors()) {
            logger.error("Validation errors found for Empresa: {}", result.getAllErrors());
            return "register/registerEmpresa"; // Retorna ao template correto em caso de erro
        }
        Usuario usuario = new Usuario(empresa.getEmail(), passwordEncoder.encode(empresa.getSenha()), "ROLE_EMPRESA");
        usuarioService.salvar(usuario);
        empresaService.salvar(empresa);
        return "redirect:/login";
    }

    @GetMapping("/profissional")
    public String showRegisterProfissionalForm(Model model) {
        model.addAttribute("profissional", new Profissional());
        return "register/registerProfissional"; // Aponta para o template correto na pasta register
    }

    @PostMapping("/profissional")
    public String registerProfissional(@Valid @ModelAttribute("profissional") Profissional profissional, BindingResult result) {
        logger.info("Received registration request for Profissional");
        if (result.hasErrors()) {
            logger.error("Validation errors found for Profissional: {}", result.getAllErrors());
            return "register/registerProfissional"; // Retorna ao template correto em caso de erro
        }
        Usuario usuario = new Usuario(profissional.getEmail(), passwordEncoder.encode(profissional.getSenha()), "ROLE_PROFISSIONAL");
        usuarioService.salvar(usuario);
        profissionalService.salvar(profissional);
        return "redirect:/login";
    }


    @GetMapping
    public String redirectToEmpresa() {
        return "redirect:/register/empresa";
    }
}
