package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Empresa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");

        if ("profissional".equals(tipo)) {
            registrarProfissional(request, response);
        } else if ("empresa".equals(tipo)) {
            registrarEmpresa(request, response);
        }
    }

    private void registrarProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("data_nascimento");

        Profissional profissional = new Profissional();
        profissional.setNome(nome);
        profissional.setEmail(email);
        profissional.setSenha(senha);
        profissional.setCpf(cpf);
        profissional.setTelefone(telefone);
        profissional.setSexo(sexo);
        profissional.setDataNascimento(dataNascimento);

        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        int idProfissional = profissionalDAO.insert(profissional);
        if (idProfissional != -1) {
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setTipo("profissional");
            usuario.setIdProfissional(idProfissional);

            usuarioDAO.insert(usuario);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            // Tratar erro de inserção
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=1");
        }
    }

    private void registrarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String descricao = request.getParameter("descricao");
        String cidade = request.getParameter("cidade");

        Empresa empresa = new Empresa();
        empresa.setNome(nome);
        empresa.setEmail(email);
        empresa.setSenha(senha);
        empresa.setCnpj(cnpj);
        empresa.setDescricao(descricao);
        empresa.setCidade(cidade);

        EmpresaDAO empresaDAO = new EmpresaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        int idEmpresa = empresaDAO.insert(empresa);
        if (idEmpresa != -1) {
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setTipo("empresa");
            usuario.setIdEmpresa(idEmpresa);

            usuarioDAO.insert(usuario);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            // Tratar erro de inserção
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=1");
        }
    }
}
