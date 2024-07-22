package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/vagas/*")
public class VagasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VagaDAO vagaDAO;
    private CandidaturaDAO candidaturaDAO;

    @Override
    public void init() {
        vagaDAO = new VagaDAO();
        candidaturaDAO = new CandidaturaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

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
                    listVagas(request, response, usuarioLogado);
                    break;
                case "/listByEmpresa":
                    listVagasByEmpresa(request, response);
                    break;
                case "/candidatos":
                    listCandidatos(request, response);
                    break;
                case "/updateStatus":
                    updateStatusCandidatura(request, response);
                    break;    
                default:
                    if ("admin".equals(usuarioLogado.getTipo())) {
                        response.sendRedirect(request.getContextPath() + "/Logado/Admin/index.jsp");
                    } else if ("empresa".equals(usuarioLogado.getTipo())) {
                        response.sendRedirect(request.getContextPath() + "/Logado/Empresas/index.jsp");
                    }
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listVagas(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
            throws ServletException, IOException {
        List<Vaga> listVagas;
        if ("admin".equals(usuario.getTipo())) {
            listVagas = vagaDAO.getAll();
        } else {
            Empresa empresaLogada = (Empresa) request.getSession().getAttribute("empresaLogada");
            listVagas = vagaDAO.getAllByEmpresa(empresaLogada.getId());
        }
        request.setAttribute("listaVagas", listVagas);
        String destino = "admin".equals(usuario.getTipo()) ? "/Logado/Admin/listaVagas.jsp" : "/Logado/Empresas/lista.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }

    private void listVagasByEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Empresa empresaLogada = (Empresa) session.getAttribute("empresaLogada");
        List<Vaga> listaVagas = vagaDAO.getAllByEmpresa(empresaLogada.getId());
        request.setAttribute("listaVagas", listaVagas);
        request.getRequestDispatcher("/Logado/Vagas/listaByEmpresa.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Vagas/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Vaga existingVaga = vagaDAO.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Vagas/formulario.jsp");
        request.setAttribute("vaga", existingVaga);
        dispatcher.forward(request, response);
    }

    private void insertVaga(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession sessao = request.getSession();
        Empresa empresaLogada = (Empresa) sessao.getAttribute("empresaLogada");

        String descricao = request.getParameter("descricao");
        String remuneracaoStr = request.getParameter("remuneracao");
        String dataLimiteInscricao = request.getParameter("dataLimiteInscricao");

        double remuneracao;
        try {
            remuneracao = Double.parseDouble(remuneracaoStr);
        } catch (NumberFormatException e) {
            request.setAttribute("mensagemErro", "Valor de remuneração inválido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Vagas/formulario.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Vaga vaga = new Vaga(empresaLogada, descricao, remuneracao, dataLimiteInscricao, empresaLogada.getCidade());
        vagaDAO.insert(vaga);
        response.sendRedirect(request.getContextPath() + "/Logado/Empresas/index.jsp");
    }

    private void updateVaga(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession sessao = request.getSession();
        Empresa empresaLogada = (Empresa) sessao.getAttribute("empresaLogada");

        int id = Integer.parseInt(request.getParameter("id"));
        String descricao = request.getParameter("descricao");
        String remuneracaoStr = request.getParameter("remuneracao");
        String dataLimiteInscricao = request.getParameter("dataLimiteInscricao");
        String cidade = request.getParameter("cidade");

        double remuneracao;
        try {
            remuneracao = Double.parseDouble(remuneracaoStr);
        } catch (NumberFormatException e) {
            request.setAttribute("mensagemErro", "Valor de remuneração inválido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Vagas/formulario.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Vaga vaga = new Vaga(id, empresaLogada, descricao, remuneracao, dataLimiteInscricao, cidade);
        vagaDAO.update(vaga);
        response.sendRedirect(request.getContextPath() + "/vagas/listByEmpresa");
    }

    private void deleteVaga(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        vagaDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/vagas/listByEmpresa");
    }

    private void listCandidatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idVagaStr = request.getParameter("idVaga");
        
        if (idVagaStr == null || idVagaStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da vaga não fornecido.");
            return;
        }

        int idVaga = 0;
        try {
            idVaga = Integer.parseInt(idVagaStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da vaga inválido.");
            return;
        }

        List<Candidatura> listaCandidaturas = candidaturaDAO.getCandidatosByVaga(idVaga);
        request.setAttribute("listaCandidaturas", listaCandidaturas);
        request.getRequestDispatcher("/Logado/Empresas/listaCandidatos.jsp").forward(request, response);
    }

    private void updateStatusCandidatura(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String idCandidaturaStr = request.getParameter("idCandidatura");
        String idStatusStr = request.getParameter("status");
        String entrevistaLink = request.getParameter("entrevistaLink");
        String entrevistaDataHoraStr = request.getParameter("entrevistaDataHora");

        if (idCandidaturaStr == null || idCandidaturaStr.isEmpty() || idStatusStr == null || idStatusStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros inválidos.");
            return;
        }

        int idCandidatura = 0;
        int idStatus = 0;
        try {
            idCandidatura = Integer.parseInt(idCandidaturaStr);
            idStatus = Integer.parseInt(idStatusStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            return;
        }

        LocalDateTime entrevistaDataHora = null;
        if (entrevistaDataHoraStr != null && !entrevistaDataHoraStr.isEmpty()) {
            entrevistaDataHora = LocalDateTime.parse(entrevistaDataHoraStr);
        }

        candidaturaDAO.updateStatus(idCandidatura, idStatus, entrevistaLink, entrevistaDataHora);

        response.sendRedirect(request.getContextPath() + "/vagas/candidatos?idVaga=" + request.getParameter("idVaga"));
    }



}
