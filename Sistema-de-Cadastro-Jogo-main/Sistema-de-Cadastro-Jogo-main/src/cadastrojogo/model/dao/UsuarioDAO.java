/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.model.dao;

import cadastrojogo.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class UsuarioDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario(email, nomecompleto, nomeusuario, senha) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getNomeCompleto());
            stmt.setString(3, usuario.getNomeUsuario());
            stmt.setString(4, usuario.getSenha());
            

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ArbitroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
