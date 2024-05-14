/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.model.dao;
;
import cadastrojogo.model.Gol;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GolDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
      public boolean inserir(Gol gol) {
        String sql = "INSERT INTO gols(camisaJogador, nomeJogador, idJogo , Tempo, nomeEquipe) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
           // stmt.setInt(1, gol.getcamisaJogador().getCamisa());
            stmt.setInt(2, gol.getnomeJogador().getCdJogador());
            stmt.setInt(3, gol.getJogo().getCdJogo());
            //stmt.setString(4, gol.getnomeEquipe().getidCadastroTime());
            //stmt.setString(5, gol.setTempo().getTempo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
