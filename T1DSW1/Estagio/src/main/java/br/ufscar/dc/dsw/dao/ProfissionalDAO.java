package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Profissional;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
            pst.setDate(7, Date.valueOf(profissional.getDataNascimento()));
            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 se a inserção falhar
    }

    public Profissional get(int id) {
        String sql = "SELECT * FROM Profissionais WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
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
                    profissional.setDataNascimento(rs.getDate("data_nascimento").toString());
                    return profissional;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Profissional> getAll() {
        String sql = "SELECT * FROM Profissionais";
        List<Profissional> listProfissionais = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Profissional profissional = new Profissional();
                profissional.setId(rs.getInt("id"));
                profissional.setNome(rs.getString("nome"));
                profissional.setEmail(rs.getString("email"));
                profissional.setSenha(rs.getString("senha"));
                profissional.setCpf(rs.getString("cpf"));
                profissional.setTelefone(rs.getString("telefone"));
                profissional.setSexo(rs.getString("sexo"));
                profissional.setDataNascimento(rs.getDate("data_nascimento").toString());
                listProfissionais.add(profissional);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProfissionais;
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE Profissionais SET nome = ?, email = ?, senha = ?, cpf = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, profissional.getNome());
            pst.setString(2, profissional.getEmail());
            pst.setString(3, profissional.getSenha());
            pst.setString(4, profissional.getCpf());
            pst.setString(5, profissional.getTelefone());
            pst.setString(6, profissional.getSexo());
            pst.setDate(7, Date.valueOf(profissional.getDataNascimento()));
            pst.setInt(8, profissional.getId());
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

    public Profissional findByEmail(String email) {
        Profissional profissional = null;
        String sql = "SELECT * FROM Profissionais WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    profissional = new Profissional();
                    profissional.setId(rs.getInt("id"));
                    profissional.setNome(rs.getString("nome"));
                    profissional.setEmail(rs.getString("email"));
                    profissional.setSenha(rs.getString("senha"));
                    profissional.setCpf(rs.getString("cpf"));
                    profissional.setTelefone(rs.getString("telefone"));
                    profissional.setSexo(rs.getString("sexo"));
                    String dataNascimentoStr = rs.getDate("data_nascimento").toLocalDate().format(DateTimeFormatter.ISO_DATE);
                    profissional.setDataNascimento(dataNascimentoStr);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profissional;
    }

    public Profissional getById(int id) {
        Profissional profissional = null;
        String sql = "SELECT * FROM Profissional WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    profissional = new Profissional();
                    profissional.setId(rs.getInt("id"));
                    profissional.setNome(rs.getString("nome"));
                    profissional.setEmail(rs.getString("email"));
                    profissional.setSenha(rs.getString("senha"));
                    profissional.setCpf(rs.getString("cpf"));
                    profissional.setTelefone(rs.getString("telefone"));
                    profissional.setSexo(rs.getString("sexo"));
                    String dataNascimentoStr = rs.getDate("data_nascimento").toLocalDate().format(DateTimeFormatter.ISO_DATE);
                    profissional.setDataNascimento(dataNascimentoStr);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profissional;
    }
}
