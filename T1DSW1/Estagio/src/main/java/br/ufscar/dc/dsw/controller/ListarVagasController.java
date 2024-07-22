package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Vaga;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;


@WebServlet("/listarVagas")
public class ListarVagasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VagaDAO vagaDAO;

    @Override
    public void init() {
        vagaDAO = new VagaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cidade = request.getParameter("cidade");
        List<Vaga> listaVagas;

        if (cidade != null && !cidade.isEmpty()) {
            listaVagas = vagaDAO.getAllByCidade(cidade);
        } else {
            listaVagas = vagaDAO.getAll();
        }

        request.setAttribute("listaVagas", listaVagas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listarVagas.jsp");
        dispatcher.forward(request, response);
    }
}

