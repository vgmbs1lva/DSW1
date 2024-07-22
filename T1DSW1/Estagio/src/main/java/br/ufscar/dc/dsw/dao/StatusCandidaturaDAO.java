package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.StatusCandidatura;

import java.sql.*;

public class StatusCandidaturaDAO extends GenericDAO {

    public StatusCandidatura getById(int id) {
        StatusCandidatura status = null;
        String sql = "SELECT * FROM StatusCandidatura WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    status = new StatusCandidatura();
                    status.setId(rs.getInt("id"));
                    status.setDescricao(rs.getString("descricao"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
