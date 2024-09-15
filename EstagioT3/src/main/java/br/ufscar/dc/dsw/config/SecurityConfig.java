package br.ufscar.dc.dsw.config;

import br.ufscar.dc.dsw.service.UsuarioDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioDetailsService usuarioDetailsService;

    public SecurityConfig(UsuarioDetailsService usuarioDetailsService) {
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/register/**").permitAll() // Permitir todas as requisições para /register sem autenticação
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/empresa/**").hasRole("EMPRESA")
                .requestMatchers("/profissional/**").hasRole("PROFISSIONAL")
                .requestMatchers("/vaga/listarTodas").permitAll() // Permitir acesso público a todas as vagas
                .requestMatchers("/vaga/**").permitAll()  // Permitir acesso a todas as vagas
                .requestMatchers("/candidatura/listar").hasRole("PROFISSIONAL") // Apenas profissionais podem acessar
                .requestMatchers("/candidatura/candidatar**").hasRole("PROFISSIONAL") // Somente profissionais podem acessar
                .requestMatchers("/css/**", "/js/**", "/images/**", "/public/**").permitAll() // Permitir acesso a recursos estáticos
                .requestMatchers("/**").permitAll()
                .requestMatchers("/api/**").permitAll()  // Permitir acesso público às rotas da API

                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());
    
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(usuarioDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
