package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Profissional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO extends GenericDAO{

    public Profissional login(String email, String senha) {
        Profissional profissional = null;
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Profissional WHERE email = ? AND senha = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                profissional = new Profissional();
                profissional.setId(resultSet.getLong("id"));
                profissional.setNome(resultSet.getString("nome"));
                profissional.setEmail(resultSet.getString("email"));
                profissional.setSenha(resultSet.getString("senha"));
                profissional.setCpf(resultSet.getString("cpf"));
                profissional.setTelefone(resultSet.getString("telefone"));
                profissional.setSexo(resultSet.getString("sexo"));
                profissional.setDataNascimento(resultSet.getString("data_nascimento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profissional;
    }

    public void saveOrUpdate(Profissional profissional) {
        try (Connection conn = this.getConnection()) {
            String sql;
            if (profissional.getId() == null) {
                sql = "INSERT INTO Profissional (nome, email, senha, cpf, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE Profissional SET nome = ?, email = ?, senha = ?, cpf = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE id = ?";
            }
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, profissional.getNome());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getSenha());
            statement.setString(4, profissional.getCpf());
            statement.setString(5, profissional.getTelefone());
            statement.setString(6, profissional.getSexo());
            statement.setString(7, profissional.getDataNascimento());
            if (profissional.getId() != null) {
                statement.setLong(8, profissional.getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Profissional profissional) {
        saveOrUpdate(profissional);
    }

    public void update(Profissional profissional) {
        saveOrUpdate(profissional);
    }

    public void delete(Long id) {
        try (Connection conn = this.getConnection()) {
            String sql = "DELETE FROM Profissional WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Profissional get(Long id) {
        Profissional profissional = null;
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Profissional WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                profissional = new Profissional();
                profissional.setId(resultSet.getLong("id"));
                profissional.setNome(resultSet.getString("nome"));
                profissional.setEmail(resultSet.getString("email"));
                profissional.setSenha(resultSet.getString("senha"));
                profissional.setCpf(resultSet.getString("cpf"));
                profissional.setTelefone(resultSet.getString("telefone"));
                profissional.setSexo(resultSet.getString("sexo"));
                profissional.setDataNascimento(resultSet.getString("data_nascimento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profissional;
    }

    public List<Profissional> getAll() {
        List<Profissional> profissionais = new ArrayList<>();
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Profissional";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Profissional profissional = new Profissional();
                profissional.setId(resultSet.getLong("id"));
                profissional.setNome(resultSet.getString("nome"));
                profissional.setEmail(resultSet.getString("email"));
                profissional.setSenha(resultSet.getString("senha"));
                profissional.setCpf(resultSet.getString("cpf"));
                profissional.setTelefone(resultSet.getString("telefone"));
                profissional.setSexo(resultSet.getString("sexo"));
                profissional.setDataNascimento(resultSet.getString("data_nascimento"));
                profissionais.add(profissional);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profissionais;
    }
}
