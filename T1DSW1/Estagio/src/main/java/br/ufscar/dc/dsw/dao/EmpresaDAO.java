package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Empresa;
import java.sql.*;

public class EmpresaDAO extends GenericDAO {

    public int insert(Empresa empresa) {
        String sql = "INSERT INTO Empresas (nome, email, senha, cnpj, descricao, cidade) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, empresa.getNome());
            pst.setString(2, empresa.getEmail());
            pst.setString(3, empresa.getSenha());
            pst.setString(4, empresa.getCnpj());
            pst.setString(5, empresa.getDescricao());
            pst.setString(6, empresa.getCidade());
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating company failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating company failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 se a inserção falhar
    }

    public Empresa findByEmail(String email) {
        String sql = "SELECT * FROM Empresas WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Empresa empresa = new Empresa();
                    empresa.setId(rs.getInt("id"));
                    empresa.setNome(rs.getString("nome"));
                    empresa.setEmail(rs.getString("email"));
                    empresa.setSenha(rs.getString("senha"));
                    empresa.setCnpj(rs.getString("cnpj"));
                    empresa.setDescricao(rs.getString("descricao"));
                    empresa.setCidade(rs.getString("cidade"));
                    return empresa;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
