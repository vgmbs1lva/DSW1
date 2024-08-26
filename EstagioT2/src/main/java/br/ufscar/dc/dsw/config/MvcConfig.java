package br.ufscar.dc.dsw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final LocaleChangeInterceptor localeChangeInterceptor;

    public MvcConfig(LocaleChangeInterceptor localeChangeInterceptor) {
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/admin").setViewName("admin/home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
    }
}
