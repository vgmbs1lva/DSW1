package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO extends GenericDAO {

    public Empresa login(String email, String senha) {
        Empresa empresa = null;
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Empresa WHERE email = ? AND senha = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                empresa = new Empresa();
                empresa.setId(resultSet.getLong("id"));
                empresa.setNome(resultSet.getString("nome"));
                empresa.setEmail(resultSet.getString("email"));
                empresa.setSenha(resultSet.getString("senha"));
                empresa.setCnpj(resultSet.getString("cnpj"));
                empresa.setDescricao(resultSet.getString("descricao"));
                empresa.setCidade(resultSet.getString("cidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresa;
    }

    public void saveOrUpdate(Empresa empresa) {
        try (Connection conn = this.getConnection()) {
            String sql;
            if (empresa.getId() == null) {
                sql = "INSERT INTO Empresa (nome, email, senha, cnpj, descricao, cidade) VALUES (?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE Empresa SET nome = ?, email = ?, senha = ?, cnpj = ?, descricao = ?, cidade = ? WHERE id = ?";
            }
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, empresa.getNome());
            statement.setString(2, empresa.getEmail());
            statement.setString(3, empresa.getSenha());
            statement.setString(4, empresa.getCnpj());
            statement.setString(5, empresa.getDescricao());
            statement.setString(6, empresa.getCidade());
            if (empresa.getId() != null) {
                statement.setLong(7, empresa.getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Empresa empresa) {
        saveOrUpdate(empresa);
    }

    public void update(Empresa empresa) {
        saveOrUpdate(empresa);
    }

    public void delete(Long id) {
        try (Connection conn = this.getConnection()) {
            String sql = "DELETE FROM Empresa WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Empresa get(Long id) {
        Empresa empresa = null;
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Empresa WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                empresa = new Empresa();
                empresa.setId(resultSet.getLong("id"));
                empresa.setNome(resultSet.getString("nome"));
                empresa.setEmail(resultSet.getString("email"));
                empresa.setSenha(resultSet.getString("senha"));
                empresa.setCnpj(resultSet.getString("cnpj"));
                empresa.setDescricao(resultSet.getString("descricao"));
                empresa.setCidade(resultSet.getString("cidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresa;
    }

    public List<Empresa> getAll() {
        List<Empresa> empresas = new ArrayList<>();
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Empresa";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(resultSet.getLong("id"));
                empresa.setNome(resultSet.getString("nome"));
                empresa.setEmail(resultSet.getString("email"));
                empresa.setSenha(resultSet.getString("senha"));
                empresa.setCnpj(resultSet.getString("cnpj"));
                empresa.setDescricao(resultSet.getString("descricao"));
                empresa.setCidade(resultSet.getString("cidade"));
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresas;
    }
}
