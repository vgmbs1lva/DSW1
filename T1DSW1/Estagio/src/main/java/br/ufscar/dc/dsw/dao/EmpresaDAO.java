package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Empresa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Empresa get(int id) {
        String sql = "SELECT * FROM Empresas WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
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

    public List<Empresa> getAll() {
        String sql = "SELECT * FROM Empresas";
        List<Empresa> listEmpresa = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setNome(rs.getString("nome"));
                empresa.setEmail(rs.getString("email"));
                empresa.setSenha(rs.getString("senha"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setDescricao(rs.getString("descricao"));
                empresa.setCidade(rs.getString("cidade"));
                listEmpresa.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listEmpresa;
    }

    public void update(Empresa empresa) {
        String sql = "UPDATE Empresas SET nome = ?, email = ?, senha = ?, cnpj = ?, descricao = ?, cidade = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, empresa.getNome());
            pst.setString(2, empresa.getEmail());
            pst.setString(3, empresa.getSenha());
            pst.setString(4, empresa.getCnpj());
            pst.setString(5, empresa.getDescricao());
            pst.setString(6, empresa.getCidade());
            pst.setInt(7, empresa.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Empresas WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
