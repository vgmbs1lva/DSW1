package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmpresaDAO empresaDAO;
    private ProfissionalDAO profissionalDAO;
    private VagaDAO vagaDAO;

    @Override
    public void init() {
        empresaDAO = new EmpresaDAO();
        profissionalDAO = new ProfissionalDAO();
        vagaDAO = new VagaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/empresas":
                    listEmpresas(request, response);
                    break;
                case "/profissionais":
                    listProfissionais(request, response);
                    break;
                case "/vagas":
                    listVagas(request, response);
                    break;
                case "/logout":
                    response.sendRedirect(request.getContextPath() + "/logout");
                    break;       
                default:
                    showAdminPage(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void showAdminPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Admin/index.jsp");
        dispatcher.forward(request, response);
    }

    private void listEmpresas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Empresa> listEmpresas = empresaDAO.getAll();
        request.setAttribute("listaEmpresas", listEmpresas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Admin/listaEmpresas.jsp");
        dispatcher.forward(request, response);
    }

    private void listProfissionais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Profissional> listProfissionais = profissionalDAO.getAll();
        request.setAttribute("listaProfissionais", listProfissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Admin/listaProfissionais.jsp");
        dispatcher.forward(request, response);
    }

    private void listVagas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Vaga> listVagas = vagaDAO.getAll();
        request.setAttribute("listaVagas", listVagas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Admin/listaVagas.jsp");
        dispatcher.forward(request, response);
    }
}
