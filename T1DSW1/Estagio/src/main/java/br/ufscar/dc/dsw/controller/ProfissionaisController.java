package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
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

@WebServlet("/profissionais/*")
public class ProfissionaisController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProfissionalDAO profissionalDAO;

    @Override
    public void init() {
        profissionalDAO = new ProfissionalDAO();
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
                    insertProfissional(request, response);
                    break;
                case "/delete":
                    deleteProfissional(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateProfissional(request, response);
                    break;
                case "/list":
                    listProfissionais(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/logado/Profissionais/lista.jsp");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listProfissionais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Profissional> listProfissionais = profissionalDAO.getAll();
        request.setAttribute("listaProfissionais", listProfissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/Profissionais/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/Profissionais/formularios.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Profissional existingProfissional = profissionalDAO.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/Profissionais/formularios.jsp");
        request.setAttribute("profissional", existingProfissional);
        dispatcher.forward(request, response);
    }

    private void insertProfissional(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("dataNascimento");

        Profissional profissional = new Profissional(nome, cpf, telefone, sexo, dataNascimento);
        profissionalDAO.insert(profissional);
        response.sendRedirect("list");
    }

    private void updateProfissional(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("dataNascimento");

        Profissional profissional = new Profissional(id, nome, cpf, telefone, sexo, dataNascimento);
        profissionalDAO.update(profissional);
        response.sendRedirect("list");
    }

    private void deleteProfissional(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        profissionalDAO.delete(id);
        response.sendRedirect("list");
    }
}
