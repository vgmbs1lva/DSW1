package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/candidaturas/*")
public class CandidaturaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CandidaturaDAO dao;

    @Override
    public void init() {
        dao = new CandidaturaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.getPapel().equals("PROFISSIONAL")) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [PROFISSIONAL] tem acesso a essa página");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Candidatura> listaCandidaturas = dao.getAll();
        request.setAttribute("listaCandidaturas", listaCandidaturas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/candidatura/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/candidatura/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Candidatura candidatura = dao.get(id);
        request.setAttribute("candidatura", candidatura);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/candidatura/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long vagaId = Long.parseLong(request.getParameter("vaga_id"));
        Long profissionalId = Long.parseLong(request.getParameter("profissional_id"));
        Date dataCandidatura = new Date();
        String curriculoPath = request.getParameter("curriculo_path");
        String status = request.getParameter("status");

        Candidatura candidatura = new Candidatura(vagaId, profissionalId, dataCandidatura, curriculoPath, status);
        dao.insert(candidatura);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        Long vagaId = Long.parseLong(request.getParameter("vaga_id"));
        Long profissionalId = Long.parseLong(request.getParameter("profissional_id"));
        Date dataCandidatura = new Date();
        String curriculoPath = request.getParameter("curriculo_path");
        String status = request.getParameter("status");

        Candidatura candidatura = new Candidatura(id, vagaId, profissionalId, dataCandidatura, curriculoPath, status);
        dao.update(candidatura);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        dao.delete(id);
        response.sendRedirect("lista");
    }
}
