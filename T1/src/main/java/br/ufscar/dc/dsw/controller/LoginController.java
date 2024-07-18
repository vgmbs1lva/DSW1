package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        Erro erros = new Erro();

        if (login == null || login.isEmpty()) {
            erros.add("Login não informado!");
        }
        if (senha == null || senha.isEmpty()) {
            erros.add("Senha não informada!");
        }
        if (!erros.isExisteErros()) {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.getByLogin(login);
            if (usuario != null && usuario.getSenha().equals(senha)) {
                request.getSession().setAttribute("usuarioLogado", usuario);
                if ("ADMIN".equals(usuario.getPapel())) {
                    response.sendRedirect("admin/");
                } else if ("USER_EMPRESA".equals(usuario.getPapel())) {
                    response.sendRedirect("usuarios/");
                } else if ("USER_PROFISSIONAL".equals(usuario.getPapel())) {
                    response.sendRedirect("profissionais/");
                } else {
                    response.sendRedirect("home.jsp"); // Caso papel não seja reconhecido
                }
            } else {
                erros.add("Login ou senha inválidos.");
                request.setAttribute("mensagens", erros);
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}
