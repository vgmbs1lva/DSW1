package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Profissional;
import java.sql.*;

public class ProfissionalDAO extends GenericDAO {

    public int insert(Profissional profissional) {
        String sql = "INSERT INTO Profissionais (nome, email, senha, cpf, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, profissional.getNome());
            pst.setString(2, profissional.getEmail());
            pst.setString(3, profissional.getSenha());
            pst.setString(4, profissional.getCpf());
            pst.setString(5, profissional.getTelefone());
            pst.setString(6, profissional.getSexo());
            pst.setString(7, profissional.getDataNascimento());
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating professional failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating professional failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 se a inserção falhar
    }

    public Profissional findByEmail(String email) {
        String sql = "SELECT * FROM Profissionais WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Profissional profissional = new Profissional();
                    profissional.setId(rs.getInt("id"));
                    profissional.setNome(rs.getString("nome"));
                    profissional.setEmail(rs.getString("email"));
                    profissional.setSenha(rs.getString("senha"));
                    profissional.setCpf(rs.getString("cpf"));
                    profissional.setTelefone(rs.getString("telefone"));
                    profissional.setSexo(rs.getString("sexo"));
                    profissional.setDataNascimento(rs.getString("data_nascimento"));
                    return profissional;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
