package br.ufscar.dc.dsw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "br.ufscar.dc.dsw")
public class AppConfig {

    @Bean
    public String applicationName() {
        return "Sistema de Vagas";
    }
}
