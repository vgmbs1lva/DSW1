package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Usuario;
import java.sql.*;

public class UsuarioDAO extends GenericDAO {

    public void insert(Usuario usuario) {
        String sql = "INSERT INTO Usuario (email, senha, tipo, id_profissional, id_empresa) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getSenha());
            pst.setString(3, usuario.getTipo());
            if (usuario.getIdProfissional() != null) {
                pst.setInt(4, usuario.getIdProfissional());
            } else {
                pst.setNull(4, Types.INTEGER);
            }
            if (usuario.getIdEmpresa() != null) {
                pst.setInt(5, usuario.getIdEmpresa());
            } else {
                pst.setNull(5, Types.INTEGER);
            }
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario findByEmail(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setTipo(rs.getString("tipo"));
                    usuario.setIdProfissional(rs.getObject("id_profissional", Integer.class));
                    usuario.setIdEmpresa(rs.getObject("id_empresa", Integer.class));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
