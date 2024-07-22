package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/profissionais/candidaturas")
public class CandidaturasProfissionalController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CandidaturaDAO candidaturaDAO;

    @Override
    public void init() {
        candidaturaDAO = new CandidaturaDAO();
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

        List<Candidatura> listaCandidaturas = candidaturaDAO.getCandidaturasByProfissional(profissionalLogado.getId());
        request.setAttribute("listaCandidaturas", listaCandidaturas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Profissionais/listaCandidaturas.jsp");
        dispatcher.forward(request, response);
    }
}
