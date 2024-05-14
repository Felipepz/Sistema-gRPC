
 
package cadastrojogo.model.dao;

import cadastrojogo.model.Arbitro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class ArbitroDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Arbitro arbitro) {
        String sql = "INSERT INTO arbitro(nome, cpf, data_nascimento, nacionalidade, formacao) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, arbitro.getNome());
            stmt.setString(2, arbitro.getCpf());
            stmt.setDate(3, Date.valueOf(arbitro.getDataDeNascimento()));
            stmt.setString(4, arbitro.getNacionalidade());
            stmt.setString(5, arbitro.getFormacao());
            

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ArbitroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Arbitro arbitro) {
        String sql = "UPDATE arbitro SET nome=?, cpf=?, data_nascimento=?, nacionalidade=?, formacao=? WHERE cdArbitro=?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, arbitro.getNome());
            stmt.setString(2, arbitro.getCpf());
            stmt.setDate(3, Date.valueOf(arbitro.getDataDeNascimento()));
            stmt.setString(4, arbitro.getNacionalidade());
            stmt.setString(5, arbitro.getFormacao());
            stmt.setInt(6, arbitro.getCdArbitro());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ArbitroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean remover(Arbitro arbitro) {
        String sql = "DELETE FROM arbitro WHERE cdArbitro=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, arbitro.getCdArbitro());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ArbitroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
    
    public List<Arbitro> listar() {
        String sql = "SELECT * FROM arbitro";
        List<Arbitro> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Arbitro arbitro = new Arbitro();
                arbitro.setCdArbitro(resultado.getInt("cdArbitro"));
                arbitro.setNome(resultado.getString("nome"));
                arbitro.setCpf(resultado.getString("cpf"));
//               Date dataNascimento = resultado.getDate("data_nascimento");
//                arbitro.setDataDeNascimento(dataNascimento.toLocalDate());
                arbitro.setDataDeNascimento(resultado.getDate("data_nascimento").toLocalDate());
                arbitro.setNacionalidade(resultado.getString("nacionalidade"));
                arbitro.setFormacao(resultado.getString("formacao"));
                retorno.add(arbitro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArbitroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    
    public Arbitro buscar(Arbitro arbitro) {
        String sql = "SELECT * FROM arbitro WHERE cdArbitro=?";
        Arbitro retorno = new Arbitro();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, arbitro.getCdArbitro());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                arbitro.setNome(resultado.getString("nome"));
                arbitro.setCpf(resultado.getString("cpf"));
    //            Date dataNascimento = resultado.getDate("data_nascimento");
     //           arbitro.setDataDeNascimento(dataNascimento.toLocalDate());
                arbitro.setDataDeNascimento(resultado.getDate("data_nascimento").toLocalDate());
                arbitro.setNacionalidade(resultado.getString("nacionalidade"));
                arbitro.setFormacao(resultado.getString("formacao"));
                retorno = arbitro;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArbitroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    
    
    
}
