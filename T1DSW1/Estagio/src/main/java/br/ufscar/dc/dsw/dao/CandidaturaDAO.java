package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;

import java.io.FileInputStream;
import java.io.InputStream;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidaturaDAO extends GenericDAO {

    public void insert(Candidatura candidatura) {
        String sql = "INSERT INTO Candidatura (id_profissional, id_vaga, id_status, curriculo, data_candidatura) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, candidatura.getProfissional().getId());
            pst.setInt(2, candidatura.getVaga().getId());
            pst.setInt(3, candidatura.getStatus().getId());
            pst.setString(4, candidatura.getCurriculo());
            pst.setDate(5, Date.valueOf(candidatura.getDataCandidatura()));
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Candidatura> getAll() {
        List<Candidatura> listaCandidaturas = new ArrayList<>();
        String sql = "SELECT * FROM Candidatura";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Candidatura candidatura = new Candidatura();
                candidatura.setId(rs.getInt("id"));
                candidatura.setProfissional(new ProfissionalDAO().getById(rs.getInt("id_profissional")));
                candidatura.setVaga(new VagaDAO().getById(rs.getInt("id_vaga")));
                candidatura.setStatus(new StatusCandidaturaDAO().getById(rs.getInt("id_status")));
                candidatura.setDataCandidatura(rs.getDate("data_candidatura").toLocalDate());
                candidatura.setCurriculo(rs.getString("curriculo"));
                listaCandidaturas.add(candidatura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCandidaturas;
    }

    public Candidatura getById(int id) {
        Candidatura candidatura = null;
        String sql = "SELECT * FROM Candidatura WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    candidatura = new Candidatura();
                    candidatura.setId(rs.getInt("id"));
                    candidatura.setProfissional(new ProfissionalDAO().getById(rs.getInt("id_profissional")));
                    candidatura.setVaga(new VagaDAO().getById(rs.getInt("id_vaga")));
                    candidatura.setStatus(new StatusCandidaturaDAO().getById(rs.getInt("id_status")));
                    candidatura.setDataCandidatura(rs.getDate("data_candidatura").toLocalDate());
                    candidatura.setCurriculo(rs.getString("curriculo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidatura;
    }

    public void update(Candidatura candidatura, InputStream curriculo) {
        String sql = "UPDATE Candidatura SET id_profissional = ?, id_vaga = ?, curriculo = ?, id_status = ?, data_candidatura = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, candidatura.getProfissional().getId());
            pst.setInt(2, candidatura.getVaga().getId());
            pst.setBlob(3, curriculo);
            pst.setInt(4, candidatura.getStatus().getId());
            pst.setDate(5, Date.valueOf(candidatura.getDataCandidatura()));
            pst.setInt(6, candidatura.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Candidatura WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean jaCandidatado(int idProfissional, int idVaga) {
        String sql = "SELECT COUNT(*) FROM Candidatura WHERE id_profissional = ? AND id_vaga = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idProfissional);
            pst.setInt(2, idVaga);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Candidatura> getCandidatosByVaga(int idVaga) {
        List<Candidatura> listaCandidaturas = new ArrayList<>();
        String sql = "SELECT p.nome, p.email, c.data_candidatura, c.curriculo "
                   + "FROM Candidatura c "
                   + "JOIN Profissional p ON c.id_profissional = p.id "
                   + "WHERE c.id_vaga = ?";
    
        try (Connection conn = this.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
    
            pst.setInt(1, idVaga);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Candidatura candidatura = new Candidatura();
                    Profissional profissional = new Profissional();
                    
                    profissional.setNome(rs.getString("nome"));
                    profissional.setEmail(rs.getString("email"));
                    candidatura.setProfissional(profissional);
                    
                    candidatura.setDataCandidatura(rs.getDate("data_candidatura").toLocalDate());
                    candidatura.setCurriculo(rs.getString("curriculo"));
                    listaCandidaturas.add(candidatura);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCandidaturas;
    }
    
    

    public List<Candidatura> getAllByVaga(int idVaga) {
        List<Candidatura> lista = new ArrayList<>();
        String sql = "SELECT c.id, c.curriculo, p.id as prof_id, p.nome, p.email "
                   + "FROM Candidatura c "
                   + "JOIN Profissional p ON c.id_profissional = p.id "
                   + "WHERE c.id_vaga = ?";
    
        try (Connection conn = this.getConnection(); 
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idVaga);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Candidatura candidatura = new Candidatura();
                    candidatura.setId(rs.getInt("id"));
                    candidatura.setCurriculo(rs.getString("curriculo"));
                    
                    Profissional profissional = new Profissional();
                    profissional.setId(rs.getInt("prof_id"));
                    profissional.setNome(rs.getString("nome"));
                    profissional.setEmail(rs.getString("email"));
                    
                    candidatura.setProfissional(profissional);
                    
                    lista.add(candidatura);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
