package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Candidatura;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/empresas/*")
public class EmpresasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmpresaDAO empresaDAO;
    private VagaDAO vagaDAO;
    private CandidaturaDAO candidaturaDAO;
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        empresaDAO = new EmpresaDAO();
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteEmpresa(request, response);
                    break;
                case "/list":
                    listEmpresas(request, response);
                    break;
                case "/minhasVagas":
                    listVagas(request, response);
                    break;
                case "/candidatos":
                    listCandidatos(request, response);
                    break;
                case "/logout":
                    response.sendRedirect(request.getContextPath() + "/logout");
                    break;   
                default:
                    response.sendRedirect(request.getContextPath() + "/Logado/Empresa/index.jsp");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/insert":
                    insertEmpresa(request, response);
                    break;
                case "/update":
                    updateEmpresa(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/Logado/Empresa/index.jsp");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listEmpresas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Empresa> listEmpresas = empresaDAO.getAll();
        request.setAttribute("listaEmpresas", listEmpresas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Empresas/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Empresas/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Empresa existingEmpresa = empresaDAO.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Empresas/formulario.jsp");
        request.setAttribute("empresa", existingEmpresa);
        dispatcher.forward(request, response);
    }

    private void insertEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String descricao = request.getParameter("descricao");
        String cidade = request.getParameter("cidade");

        Empresa novaEmpresa = new Empresa(nome, email, senha, cnpj, descricao, cidade);
        int idEmpresa = empresaDAO.insert(novaEmpresa);

        if (idEmpresa > 0) {
            // Inserir na tabela Usuario
            Usuario novoUsuario = new Usuario(email, senha, "empresa", null, idEmpresa);
            usuarioDAO.insert(novoUsuario);
            response.sendRedirect("list");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao criar empresa.");
        }
    }


    private void updateEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String descricao = request.getParameter("descricao");
        String cidade = request.getParameter("cidade");

        Empresa empresa = new Empresa(id, nome, email, senha, cnpj, descricao, cidade);
        boolean sucessoEmpresa = empresaDAO.update(empresa);

        if (sucessoEmpresa) {
            // Atualizar na tabela Usuario
            Usuario usuario = usuarioDAO.findByEmpresaId(id);
            if (usuario != null) {
                usuario.setEmail(email);
                usuario.setSenha(senha);
                usuarioDAO.update(usuario);
            }
            response.sendRedirect("list");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar empresa.");
        }
    }


    private void deleteEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Usuario usuario = usuarioDAO.findByEmpresaId(id);
        if (usuario != null) {
            usuarioDAO.delete(usuario.getId());
        }

        empresaDAO.delete(id);
        response.sendRedirect("list");
    }


    private void listVagas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Empresa empresaLogada = (Empresa) sessao.getAttribute("empresaLogada");
        List<Vaga> listaVagas = vagaDAO.getAllByEmpresa(empresaLogada.getId());
        request.setAttribute("listaVagas", listaVagas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Empresas/listaVagas.jsp");
        dispatcher.forward(request, response);
    }

    private void listCandidatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idVagaStr = request.getParameter("id");
        if (idVagaStr != null) {
            try {
                int idVaga = Integer.parseInt(idVagaStr);
                List<Candidatura> listaCandidaturas = candidaturaDAO.getCandidatosByVaga(idVaga);
                request.setAttribute("listaCandidaturas", listaCandidaturas);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Empresas/listaCandidatos.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/empresas/minhasVagas?error=invalidId");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/empresas/minhasVagas?error=missingId");
        }
    }
}
