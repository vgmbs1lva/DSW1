package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Vaga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VagaDAO extends GenericDAO {

    public void saveOrUpdate(Vaga vaga) {
        try (Connection conn = this.getConnection()) {
            String sql;
            if (vaga.getId() == null) {
                sql = "INSERT INTO Vaga (cnpj, descricao, remuneracao, data_limite_inscricao) VALUES (?, ?, ?, ?)";
            } else {
                sql = "UPDATE Vaga SET cnpj = ?, descricao = ?, remuneracao = ?, data_limite_inscricao = ? WHERE id = ?";
            }
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, vaga.getCnpj());
            statement.setString(2, vaga.getDescricao());
            statement.setFloat(3, vaga.getRemuneracao());
            statement.setString(4, vaga.getDataLimiteInscricao());
            if (vaga.getId() != null) {
                statement.setLong(5, vaga.getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Vaga vaga) {
        saveOrUpdate(vaga);
    }

    public void update(Vaga vaga) {
        saveOrUpdate(vaga);
    }

    public void delete(Long id) {
        try (Connection conn = this.getConnection()) {
            String sql = "DELETE FROM Vaga WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vaga get(Long id) {
        Vaga vaga = null;
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Vaga WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                vaga = new Vaga();
                vaga.setId(resultSet.getLong("id"));
                vaga.setCnpj(resultSet.getString("cnpj"));
                vaga.setDescricao(resultSet.getString("descricao"));
                vaga.setRemuneracao(resultSet.getFloat("remuneracao"));
                vaga.setDataLimiteInscricao(resultSet.getString("data_limite_inscricao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaga;
    }

    public List<Vaga> getAll() {
        List<Vaga> vagas = new ArrayList<>();
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Vaga";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Vaga vaga = new Vaga();
                vaga.setId(resultSet.getLong("id"));
                vaga.setCnpj(resultSet.getString("cnpj"));
                vaga.setDescricao(resultSet.getString("descricao"));
                vaga.setRemuneracao(resultSet.getFloat("remuneracao"));
                vaga.setDataLimiteInscricao(resultSet.getString("data_limite_inscricao"));
                vagas.add(vaga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vagas;
    }

    public List<Vaga> getOpenVagas() {
        List<Vaga> vagas = new ArrayList<>();
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Vaga WHERE data_limite_inscricao >= CURRENT_DATE";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Vaga vaga = new Vaga();
                vaga.setId(resultSet.getLong("id"));
                vaga.setCnpj(resultSet.getString("cnpj"));
                vaga.setDescricao(resultSet.getString("descricao"));
                vaga.setRemuneracao(resultSet.getFloat("remuneracao"));
                vaga.setDataLimiteInscricao(resultSet.getString("data_limite_inscricao"));
                vagas.add(vaga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vagas;
    }
}
