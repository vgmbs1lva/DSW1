package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Profissional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO extends GenericDAO {

    public int insert(Profissional profissional) {
        String sql = "INSERT INTO Profissionais (nome, cpf, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, profissional.getNome());
            pst.setString(2, profissional.getCpf());
            pst.setString(3, profissional.getTelefone());
            pst.setString(4, profissional.getSexo());
            pst.setDate(5, Date.valueOf(profissional.getDataNascimento()));
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Profissional> getAll() {
        List<Profissional> listaProfissionais = new ArrayList<>();
        String sql = "SELECT * FROM Profissionais";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Profissional profissional = new Profissional();
                profissional.setId(rs.getInt("id"));
                profissional.setNome(rs.getString("nome"));
                profissional.setCpf(rs.getString("cpf"));
                profissional.setTelefone(rs.getString("telefone"));
                profissional.setSexo(rs.getString("sexo"));
                profissional.setDataNascimento(rs.getDate("data_nascimento").toString());
                listaProfissionais.add(profissional);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProfissionais;
    }

    public Profissional getById(int id) {
        Profissional profissional = null;
        String sql = "SELECT * FROM Profissionais WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    profissional = new Profissional();
                    profissional.setId(rs.getInt("id"));
                    profissional.setNome(rs.getString("nome"));
                    profissional.setCpf(rs.getString("cpf"));
                    profissional.setTelefone(rs.getString("telefone"));
                    profissional.setSexo(rs.getString("sexo"));
                    profissional.setDataNascimento(rs.getDate("data_nascimento").toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profissional;
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE Profissionais SET nome = ?, cpf = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, profissional.getNome());
            pst.setString(2, profissional.getCpf());
            pst.setString(3, profissional.getTelefone());
            pst.setString(4, profissional.getSexo());
            pst.setDate(5, Date.valueOf(profissional.getDataNascimento()));
            pst.setInt(6, profissional.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Profissionais WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
