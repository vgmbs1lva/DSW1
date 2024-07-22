package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.StatusCandidaturaDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.StatusCandidatura;
import br.ufscar.dc.dsw.domain.Vaga;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/candidatarVaga")
public class CandidaturaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CandidaturaDAO candidaturaDAO;
    private ProfissionalDAO profissionalDAO;
    private VagaDAO vagaDAO;
    private StatusCandidaturaDAO statusCandidaturaDAO;

    @Override
    public void init() {
        candidaturaDAO = new CandidaturaDAO();
        profissionalDAO = new ProfissionalDAO();
        vagaDAO = new VagaDAO();
        statusCandidaturaDAO = new StatusCandidaturaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Profissional profissionalLogado = (Profissional) sessao.getAttribute("profissionalLogado");
        if (profissionalLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int idVaga = Integer.parseInt(request.getParameter("id"));
        if (candidaturaDAO.jaCandidatado(profissionalLogado.getId(), idVaga)) {
            response.sendRedirect(request.getContextPath() + "/listarVagas?error=2");
        } else {
            Vaga vaga = vagaDAO.getById(idVaga);
            request.setAttribute("vaga", vaga);
            request.getRequestDispatcher("/Logado/Vagas/candidatarVaga.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Profissional profissionalLogado = (Profissional) sessao.getAttribute("profissionalLogado");
        if (profissionalLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int idVaga = 0;
        try {
            idVaga = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarVagas?error=1");
            return;
        }

        Vaga vaga = vagaDAO.getById(idVaga);
        StatusCandidatura status = statusCandidaturaDAO.getById(1); // Status padrão: Enviado
        String curriculo = request.getParameter("curriculo");

        if (curriculo != null && !curriculo.isEmpty()) {
            Candidatura candidatura = new Candidatura(profissionalLogado, vaga, status, curriculo);
            candidaturaDAO.insert(candidatura);
            response.sendRedirect(request.getContextPath() + "/listarVagas");
        } else {
            response.sendRedirect(request.getContextPath() + "/listarVagas?error=1");
        }
    }
}
