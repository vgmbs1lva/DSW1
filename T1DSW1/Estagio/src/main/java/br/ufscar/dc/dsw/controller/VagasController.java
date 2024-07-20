package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Vaga;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/vagas/*")
public class VagasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VagaDAO vagaDAO;

    @Override
    public void init() {
        vagaDAO = new VagaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertVaga(request, response);
                    break;
                case "/delete":
                    deleteVaga(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateVaga(request, response);
                    break;
                case "/list":
                    listVagas(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/logado/Vagas/lista.jsp");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listVagas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Vaga> listVagas = vagaDAO.getAll();
        request.setAttribute("listaVagas", listVagas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/Vagas/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/Vagas/formularios.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Vaga existingVaga = vagaDAO.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/Vagas/formularios.jsp");
        request.setAttribute("vaga", existingVaga);
        dispatcher.forward(request, response);
    }

    private void insertVaga(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String descricao = request.getParameter("descricao");
        String remuneracao = request.getParameter("remuneracao");
        String dataLimiteInscricao = request.getParameter("dataLimiteInscricao");

        Vaga vaga = new Vaga(descricao, Double.parseDouble(remuneracao), dataLimiteInscricao);
        vagaDAO.insert(vaga);
        response.sendRedirect("list");
    }

    private void updateVaga(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descricao = request.getParameter("descricao");
        String remuneracao = request.getParameter("remuneracao");
        String dataLimiteInscricao = request.getParameter("dataLimiteInscricao");

        Vaga vaga = new Vaga(id, descricao, Double.parseDouble(remuneracao), dataLimiteInscricao);
        vagaDAO.update(vaga);
        response.sendRedirect("list");
    }

    private void deleteVaga(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        vagaDAO.delete(id);
        response.sendRedirect("list");
    }
}
