package cadastrojogo.model.dao;

import cadastrojogo.model.Arbitro;
import cadastrojogo.model.Equipe;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EquipeDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Equipe equipe) {
        String sql = "INSERT INTO equipe(Liga, Pais, Tecnico, nome, anofundacao) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, equipe.getLiga());
            stmt.setString(2, equipe.getPais());
            stmt.setString(3, equipe.getTecnico());
            stmt.setString(4, equipe.getnome());
            stmt.setDate(5,Date.valueOf(equipe.getanofundacao()));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Equipe equipe) {
        String sql = "UPDATE equipe SET nome=?, Pais=?, Liga=?, Tecnico=?, anofundacao=? WHERE idCadastroEquipe=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, equipe.getnome());
            stmt.setString(2, equipe.getPais());
            stmt.setString(3, equipe.getLiga());
            stmt.setString(4, equipe.getTecnico());
            stmt.setDate(5,Date.valueOf(equipe.getanofundacao()));
            stmt.setInt(6, equipe.getidCadastroEquipe());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Equipe equipe) {
        String sql = "DELETE FROM equipe WHERE idCadastroEquipe=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, equipe.getidCadastroEquipe());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Equipe> listar() {
        String sql = "SELECT * FROM equipe";
        List<Equipe> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Equipe equipe = new Equipe();
                equipe.setidCadastroEquipe(resultado.getInt("idCadastroEquipe"));
                equipe.setnome(resultado.getString("nome"));
                equipe.setPais(resultado.getString("Pais"));
                equipe.setLiga(resultado.getString("Liga"));
                equipe.setTecnico(resultado.getString("Tecnico"));
                equipe.setanofundacao(resultado.getDate("anofundacao").toLocalDate());
                retorno.add(equipe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Equipe buscar(Equipe equipe) {
        String sql = "SELECT * FROM equipe WHERE idCadastroEquipe=?";
        Equipe retorno = new Equipe();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, equipe.getidCadastroEquipe());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                equipe.setnome(resultado.getString("nome"));
                equipe.setPais(resultado.getString("Pais"));
                equipe.setLiga(resultado.getString("Liga"));
                equipe.setTecnico(resultado.getString("Tecnico"));
                equipe.setanofundacao(resultado.getDate("anofundacao").toLocalDate());
                retorno = equipe;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    
}
