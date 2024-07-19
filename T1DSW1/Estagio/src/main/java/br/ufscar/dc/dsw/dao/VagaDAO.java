package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Vaga;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VagaDAO extends GenericDAO {

    public void insert(Vaga vaga) {
        String sql = "INSERT INTO Vaga (id_empresa, descricao, remuneracao, data_limite_inscricao) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, vaga.getIdEmpresa());
            pst.setString(2, vaga.getDescricao());
            pst.setDouble(3, vaga.getRemuneracao());
            pst.setString(4, vaga.getDataLimiteInscricao());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vaga> getAll() {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM Vaga";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setId(rs.getInt("id"));
                vaga.setIdEmpresa(rs.getInt("id_empresa"));
                vaga.setDescricao(rs.getString("descricao"));
                vaga.setRemuneracao(rs.getDouble("remuneracao"));
                vaga.setDataLimiteInscricao(rs.getString("data_limite_inscricao"));
                listaVagas.add(vaga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVagas;
    }

    public Vaga getById(int id) {
        Vaga vaga = null;
        String sql = "SELECT * FROM Vaga WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    vaga = new Vaga();
                    vaga.setId(rs.getInt("id"));
                    vaga.setIdEmpresa(rs.getInt("id_empresa"));
                    vaga.setDescricao(rs.getString("descricao"));
                    vaga.setRemuneracao(rs.getDouble("remuneracao"));
                    vaga.setDataLimiteInscricao(rs.getString("data_limite_inscricao"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaga;
    }

    public void update(Vaga vaga) {
        String sql = "UPDATE Vaga SET id_empresa = ?, descricao = ?, remuneracao = ?, data_limite_inscricao = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, vaga.getIdEmpresa());
            pst.setString(2, vaga.getDescricao());
            pst.setDouble(3, vaga.getRemuneracao());
            pst.setString(4, vaga.getDataLimiteInscricao());
            pst.setInt(5, vaga.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Vaga WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
