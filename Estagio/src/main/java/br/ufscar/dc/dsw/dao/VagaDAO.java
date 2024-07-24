package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Vaga;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;

public class VagaDAO extends GenericDAO {
    

    public void insert(Vaga vaga) {
        String sql = "INSERT INTO Vaga (id_empresa, descricao, remuneracao, data_limite_inscricao, cidade) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
    
            pst.setInt(1, vaga.getEmpresa().getId());
            pst.setString(2, vaga.getDescricao());
            pst.setDouble(3, vaga.getRemuneracao());
            pst.setDate(4, Date.valueOf(vaga.getDataLimiteInscricao()));
            pst.setString(5, vaga.getCidade());
    
            int rowsAffected = pst.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected); // Log para verificar se a inserção foi bem-sucedida
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vaga> getAll() {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT v.*, e.nome AS empresa_nome, e.cidade AS empresa_cidade FROM Vaga v LEFT JOIN Empresas e ON v.id_empresa = e.id WHERE v.data_limite_inscricao >= CURDATE()";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id_empresa"));
                empresa.setNome(rs.getString("empresa_nome"));
                empresa.setCidade(rs.getString("empresa_cidade"));

                Vaga vaga = new Vaga();
                vaga.setId(rs.getInt("id"));
                vaga.setIdEmpresa(rs.getInt("id_empresa"));
                vaga.setDescricao(rs.getString("descricao"));
                vaga.setRemuneracao(rs.getDouble("remuneracao"));
                vaga.setDataLimiteInscricao(rs.getString("data_limite_inscricao"));
                vaga.setCidade(rs.getString("cidade"));
                vaga.setEmpresa(empresa);

                listaVagas.add(vaga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVagas;
    }

    public List<Vaga> getAllByEmpresa(int idEmpresa) {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT v.*, e.nome AS empresa_nome, e.cidade AS empresa_cidade FROM Vaga v LEFT JOIN Empresas e ON v.id_empresa = e.id WHERE v.id_empresa = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idEmpresa);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Empresa empresa = new Empresa();
                    empresa.setId(rs.getInt("id_empresa"));
                    empresa.setNome(rs.getString("empresa_nome"));
                    empresa.setCidade(rs.getString("empresa_cidade"));

                    Vaga vaga = new Vaga();
                    vaga.setId(rs.getInt("id"));
                    vaga.setIdEmpresa(rs.getInt("id_empresa"));
                    vaga.setDescricao(rs.getString("descricao"));
                    vaga.setRemuneracao(rs.getDouble("remuneracao"));
                    vaga.setDataLimiteInscricao(rs.getString("data_limite_inscricao"));
                    vaga.setCidade(rs.getString("cidade"));
                    vaga.setEmpresa(empresa);

                    listaVagas.add(vaga);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVagas;
    }

    public List<Vaga> getAllByCidade(String cidade) {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT v.*, e.nome AS empresa_nome, e.cidade AS empresa_cidade FROM Vaga v LEFT JOIN Empresas e ON v.id_empresa = e.id WHERE v.cidade = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, cidade);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Empresa empresa = new Empresa();
                    empresa.setId(rs.getInt("id_empresa"));
                    empresa.setNome(rs.getString("empresa_nome"));
                    empresa.setCidade(rs.getString("empresa_cidade"));

                    Vaga vaga = new Vaga();
                    vaga.setId(rs.getInt("id"));
                    vaga.setIdEmpresa(rs.getInt("id_empresa"));
                    vaga.setDescricao(rs.getString("descricao"));
                    vaga.setRemuneracao(rs.getDouble("remuneracao"));
                    vaga.setDataLimiteInscricao(rs.getString("data_limite_inscricao"));
                    vaga.setCidade(rs.getString("cidade"));
                    vaga.setEmpresa(empresa);

                    listaVagas.add(vaga);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVagas;
    }

    public Vaga getById(int id) {
        Vaga vaga = null;
        String sql = "SELECT v.*, e.nome AS empresa_nome, e.cidade AS empresa_cidade FROM Vaga v LEFT JOIN Empresas e ON v.id_empresa = e.id WHERE v.id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Empresa empresa = new Empresa();
                    empresa.setId(rs.getInt("id_empresa"));
                    empresa.setNome(rs.getString("empresa_nome"));
                    empresa.setCidade(rs.getString("empresa_cidade"));

                    vaga = new Vaga();
                    vaga.setId(rs.getInt("id"));
                    vaga.setIdEmpresa(rs.getInt("id_empresa"));
                    vaga.setDescricao(rs.getString("descricao"));
                    vaga.setRemuneracao(rs.getDouble("remuneracao"));
                    vaga.setDataLimiteInscricao(rs.getString("data_limite_inscricao"));
                    vaga.setCidade(rs.getString("cidade"));
                    vaga.setEmpresa(empresa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaga;
    }

    public void update(Vaga vaga) {
        String sql = "UPDATE Vaga SET descricao = ?, remuneracao = ?, data_limite_inscricao = ?, cidade = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, vaga.getDescricao());
            pst.setDouble(2, vaga.getRemuneracao());
            pst.setDate(3, Date.valueOf(vaga.getDataLimiteInscricao()));
            pst.setString(4, vaga.getCidade());
            pst.setInt(5, vaga.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Vaga WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public LocalDate getDataLimiteInscricao(int idVaga) {
        String sql = "SELECT data_limite_inscricao FROM Vaga WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idVaga);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Date dataLimite = rs.getDate("data_limite_inscricao");
                    return dataLimite != null ? dataLimite.toLocalDate() : null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   

}

