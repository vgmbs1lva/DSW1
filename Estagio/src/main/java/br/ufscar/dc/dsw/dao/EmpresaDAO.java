package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Empresa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO extends GenericDAO {

    public int insert(Empresa empresa) {
        String sqlEmpresa = "INSERT INTO Empresas (nome, email, senha, cnpj, descricao, cidade) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlUsuario = "INSERT INTO Usuario (email, senha, tipo, id_empresa) VALUES (?, ?, 'empresa', ?)";
    
        try (Connection conn = getConnection();
             PreparedStatement pstEmpresa = conn.prepareStatement(sqlEmpresa, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstUsuario = conn.prepareStatement(sqlUsuario)) {
    
            // Inserir empresa
            pstEmpresa.setString(1, empresa.getNome());
            pstEmpresa.setString(2, empresa.getEmail());
            pstEmpresa.setString(3, empresa.getSenha());
            pstEmpresa.setString(4, empresa.getCnpj());
            pstEmpresa.setString(5, empresa.getDescricao());
            pstEmpresa.setString(6, empresa.getCidade());
            int affectedRowsEmpresa = pstEmpresa.executeUpdate();
    
            if (affectedRowsEmpresa == 0) {
                throw new SQLException("Creating company failed, no rows affected.");
            }
    
            try (ResultSet generatedKeys = pstEmpresa.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idEmpresa = generatedKeys.getInt(1);
    
                    // Inserir usuário
                    pstUsuario.setString(1, empresa.getEmail());
                    pstUsuario.setString(2, empresa.getSenha());
                    pstUsuario.setInt(3, idEmpresa);
                    int affectedRowsUsuario = pstUsuario.executeUpdate();
    
                    if (affectedRowsUsuario == 0) {
                        throw new SQLException("Creating user failed, no rows affected.");
                    }
    
                    return idEmpresa;
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

    public boolean update(Empresa empresa) {
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
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
