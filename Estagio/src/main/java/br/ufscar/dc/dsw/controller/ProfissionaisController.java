package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.UsuarioDAO;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;

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
    private CandidaturaDAO candidaturaDAO;
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        profissionalDAO = new ProfissionalDAO();
        candidaturaDAO = new CandidaturaDAO();
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
                    deleteProfissional(request, response);
                    break;
                case "/list":
                    listProfissionais(request, response);
                    break;
                case "/minhasCandidaturas":
                    listCandidaturas(request, response);
                    break;
                case "/logout":
                    response.sendRedirect(request.getContextPath() + "/logout");
                    break;   
                default:
                    response.sendRedirect(request.getContextPath() + "/Logado/Profissionais/index.jsp");
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
                    insertProfissional(request, response);
                    break;
                case "/update":
                    updateProfissional(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/Logado/Profissionais/index.jsp");
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Profissionais/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Profissionais/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Profissional existingProfissional = profissionalDAO.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Logado/Profissionais/formulario.jsp");
        request.setAttribute("profissional", existingProfissional);
        dispatcher.forward(request, response);
    }

    private void insertProfissional(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("data_nascimento");

        Profissional novoProfissional = new Profissional(nome, email, senha, cpf, telefone, sexo, dataNascimento);
        int idProfissional = profissionalDAO.insert(novoProfissional);

        if (idProfissional > 0) {
            // Inserir na tabela Usuario
            Usuario novoUsuario = new Usuario(email, senha, "profissional", idProfissional, null);
            usuarioDAO.insert(novoUsuario);
            response.sendRedirect("list");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao criar profissional.");
        }
    }


    private void updateProfissional(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String nome = request.getParameter("nome");
    String email = request.getParameter("email");
    String senha = request.getParameter("senha");
    String cpf = request.getParameter("cpf");
    String telefone = request.getParameter("telefone");
    String sexo = request.getParameter("sexo");
    String dataNascimento = request.getParameter("data_nascimento");

    Profissional profissional = new Profissional(id, nome, email, senha, cpf, telefone, sexo, dataNascimento);
    boolean sucessoProfissional = profissionalDAO.update(profissional);

    if (sucessoProfissional) {
        // Atualizar na tabela Usuario
        Usuario usuario = usuarioDAO.findByProfissionalId(id);
        if (usuario != null) {
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuarioDAO.update(usuario);
        }
        response.sendRedirect("list");
    } else {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar profissional.");
    }
}


    private void deleteProfissional(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Usuario usuario = usuarioDAO.findByProfissionalId(id);
        if (usuario != null) {
        usuarioDAO.delete(usuario.getId());
    }

        profissionalDAO.delete(id);
        response.sendRedirect("list");
    }

    private void listCandidaturas(HttpServletRequest request, HttpServletResponse response)
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
