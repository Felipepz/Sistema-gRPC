/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.model.dao;

import cadastrojogo.model.Jogador;
import cadastrojogo.model.pJogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class pJogoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(pJogo jogo) {
        String sql = "INSERT INTO pJogo(timeFora, timeCasa, golFora, golCasa, Arbitro) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, jogo.getTimeFora());
            stmt.setString(2, jogo.getTimeCasa());
            stmt.setString(3, jogo.getGolFora());
            stmt.setString(4, jogo.getGolCasa());
            stmt.setString(5, jogo.getArbitro());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(pJogo jogo) {
        String sql = "UPDATE pJogo SET timeFora=?, timeCasa=?, golFora=?, golCasa=?, Arbitro=? WHERE cdjogo=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, jogo.getTimeFora());
            stmt.setString(2, jogo.getTimeCasa());
            stmt.setString(3, jogo.getGolFora());
            stmt.setString(4, jogo.getGolCasa());
            stmt.setString(5, jogo.getArbitro());
            stmt.setInt(6, jogo.getCdJogo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(pJogo jogo) {
        String sql = "DELETE FROM pJogo WHERE cdjogo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, jogo.getCdJogo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<pJogo> listar() {
        String sql = "SELECT * FROM pJogo";
        List<pJogo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                pJogo jogo = new pJogo();
                jogo.setCdJogo(resultado.getInt("cdJogo"));
                jogo.setTimeFora(resultado.getString("timeFora"));
                jogo.setTimeCasa(resultado.getString("timeCasa"));
                jogo.setGolFora(resultado.getString("golFora"));
                jogo.setGolCasa(resultado.getString("golCasa"));
                jogo.setArbitro(resultado.getString("Arbitro"));
                retorno.add(jogo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public pJogo buscar(pJogo jogo) {
        String sql = "SELECT * FROM pJogo WHERE timeFora=? AND timeCasa=? AND golFora=? AND golCasa=? AND Arbitro=?";
        pJogo retorno = new pJogo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, jogo.getTimeFora());
            stmt.setString(2, jogo.getTimeCasa());
            stmt.setString(3, jogo.getGolFora());
            stmt.setString(4, jogo.getGolCasa());
            stmt.setString(5, jogo.getArbitro());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                jogo.setTimeFora(resultado.getString("timeFora"));
                jogo.setTimeCasa(resultado.getString("timeCasa"));
                jogo.setGolFora(resultado.getString("golFora"));
                jogo.setGolCasa(resultado.getString("golCasa"));
                jogo.setArbitro(resultado.getString("Arbitro"));
                jogo.setCdJogo(resultado.getInt("cdjogo"));
                retorno = jogo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public pJogo buscarNovo(pJogo jogo) {
        String sql = "SELECT * FROM pJogo WHERE cdjogo=?";
        pJogo retorno = new pJogo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, jogo.getCdJogo());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                jogo.setTimeFora(resultado.getString("timeFora"));
                jogo.setTimeCasa(resultado.getString("timeCasa"));
                jogo.setGolFora(resultado.getString("golFora"));
                jogo.setGolCasa(resultado.getString("golCasa"));
                jogo.setArbitro(resultado.getString("Arbitro"));
                retorno = jogo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
