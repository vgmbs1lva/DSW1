package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Candidatura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidaturaDAO extends GenericDAO {

    public void saveOrUpdate(Candidatura candidatura) {
        try (Connection conn = this.getConnection()) {
            String sql;
            if (candidatura.getId() == null) {
                sql = "INSERT INTO Candidatura (vaga_id, profissional_id, data_candidatura, curriculo_path, status) VALUES (?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE Candidatura SET vaga_id = ?, profissional_id = ?, data_candidatura = ?, curriculo_path = ?, status = ? WHERE id = ?";
            }
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, candidatura.getVagaId());
            statement.setLong(2, candidatura.getProfissionalId());
            statement.setDate(3, new java.sql.Date(candidatura.getDataCandidatura().getTime()));
            statement.setString(4, candidatura.getCurriculoPath());
            statement.setString(5, candidatura.getStatus());
            if (candidatura.getId() != null) {
                statement.setLong(6, candidatura.getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Candidatura candidatura) {
        saveOrUpdate(candidatura);
    }

    public void update(Candidatura candidatura) {
        saveOrUpdate(candidatura);
    }

    public void delete(Long id) {
        try (Connection conn = this.getConnection()) {
            String sql = "DELETE FROM Candidatura WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Candidatura get(Long id) {
        Candidatura candidatura = null;
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Candidatura WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                candidatura = new Candidatura();
                candidatura.setId(resultSet.getLong("id"));
                candidatura.setVagaId(resultSet.getLong("vaga_id"));
                candidatura.setProfissionalId(resultSet.getLong("profissional_id"));
                candidatura.setDataCandidatura(resultSet.getDate("data_candidatura"));
                candidatura.setCurriculoPath(resultSet.getString("curriculo_path"));
                candidatura.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidatura;
    }

    public List<Candidatura> getAll() {
        List<Candidatura> candidaturas = new ArrayList<>();
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Candidatura";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Candidatura candidatura = new Candidatura();
                candidatura.setId(resultSet.getLong("id"));
                candidatura.setVagaId(resultSet.getLong("vaga_id"));
                candidatura.setProfissionalId(resultSet.getLong("profissional_id"));
                candidatura.setDataCandidatura(resultSet.getDate("data_candidatura"));
                candidatura.setCurriculoPath(resultSet.getString("curriculo_path"));
                candidatura.setStatus(resultSet.getString("status"));
                candidaturas.add(candidatura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidaturas;
    }

    public List<Candidatura> getByProfissionalId(Long profissionalId) {
        List<Candidatura> candidaturas = new ArrayList<>();
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM Candidatura WHERE profissional_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, profissionalId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Candidatura candidatura = new Candidatura();
                candidatura.setId(resultSet.getLong("id"));
                candidatura.setVagaId(resultSet.getLong("vaga_id"));
                candidatura.setProfissionalId(resultSet.getLong("profissional_id"));
                candidatura.setDataCandidatura(resultSet.getDate("data_candidatura"));
                candidatura.setCurriculoPath(resultSet.getString("curriculo_path"));
                candidatura.setStatus(resultSet.getString("status"));
                candidaturas.add(candidatura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidaturas;
    }
}
