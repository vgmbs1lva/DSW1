package br.ufscar.dc.dsw;

import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Optional;

@SpringBootApplication
public class EstagioApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(EstagioApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProfissionalService profissionalService, 
                                  EmpresaService empresaService, 
                                  VagaService vagaService, 
                                  UsuarioService usuarioService) {
        return (args) -> {
            // Inserir dados iniciais usando construtores completos
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Criar e salvar um usuário administrador
            verificarESalvarUsuario(new Usuario(
                "admin@domain.com", 
                passwordEncoder.encode("admin123"), 
                "ROLE_ADMIN"), 
            usuarioService);

            // Verificando e salvando profissionais com os novos campos
            Profissional profissional = new Profissional(
                "profissional@domain.com", 
                passwordEncoder.encode("prof123"), 
                "12345678901", 
                "João Silva",
                "1234567890", // Telefone
                'M',         // Sexo
                sdf.parse("1990-01-01")); // Data de Nascimento
            
            verificarESalvarProfissional(profissional, profissionalService);
            
            // Adiciona o profissional também como usuário
            verificarESalvarUsuario(new Usuario(
                profissional.getEmail(), 
                profissional.getSenha(), 
                "ROLE_PROFISSIONAL"), 
            usuarioService);

            // Verificando e salvando empresas
            Empresa empresa = new Empresa(
                "empresa@domain.com", 
                passwordEncoder.encode("empresa123"), 
                "12345678000100", 
                "Empresa XYZ", 
                "Empresa de tecnologia focada em desenvolvimento de software.", 
                "São Paulo");
            
            verificarESalvarEmpresa(empresa, empresaService);
            
            // Adiciona a empresa também como usuário
            verificarESalvarUsuario(new Usuario(
                empresa.getEmail(), 
                empresa.getSenha(), 
                "ROLE_EMPRESA"), 
            usuarioService);
        };
    }

    private void verificarESalvarUsuario(Usuario usuario, UsuarioService usuarioService) {
        Optional<Usuario> existente = usuarioService.buscarPorEmail(usuario.getEmail());
        if (existente.isEmpty()) {
            usuarioService.salvar(usuario);
        } else {
            System.out.println("Usuário com email " + usuario.getEmail() + " já existe.");
        }
    }

    private void verificarESalvarProfissional(Profissional profissional, ProfissionalService profissionalService) {
        Optional<Profissional> existente = profissionalService.buscarPorEmail(profissional.getEmail());
        if (existente.isEmpty()) {
            profissionalService.salvar(profissional);
        } else {
            System.out.println("Profissional com email " + profissional.getEmail() + " já existe.");
        }
    }

    private void verificarESalvarEmpresa(Empresa empresa, EmpresaService empresaService) {
        Optional<Empresa> existente = empresaService.buscarPorEmail(empresa.getEmail());
        if (existente.isEmpty()) {
            empresaService.salvar(empresa);
        } else {
            System.out.println("Empresa com email " + empresa.getEmail() + " já existe.");
        }
    }
}
