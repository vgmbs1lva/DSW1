package br.ufscar.dc.dsw.util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String lang = req.getParameter("lang");
        if (lang != null) {
            session.setAttribute("locale", new Locale(lang));
        } else {
            Locale locale = (Locale) session.getAttribute("locale");
            if (locale == null) {
                locale = req.getLocale();
                session.setAttribute("locale", locale);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
