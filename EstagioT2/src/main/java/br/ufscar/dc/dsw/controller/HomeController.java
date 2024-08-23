package br.ufscar.dc.dsw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String homeRedirect(Authentication authentication, HttpServletResponse response) {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        try {
            if (roles.contains("ROLE_ADMIN")) {
                response.sendRedirect("/admin/home");
            } else if (roles.contains("ROLE_EMPRESA")) {
                response.sendRedirect("/empresa/home");
            } else if (roles.contains("ROLE_PROFISSIONAL")) {
                response.sendRedirect("/profissional/listar");
            } else {
                response.sendRedirect("/default");  // Página padrão caso nenhuma role seja encontrada
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return null; // Retornando null para garantir que não haja mais processamento.
    }
}
