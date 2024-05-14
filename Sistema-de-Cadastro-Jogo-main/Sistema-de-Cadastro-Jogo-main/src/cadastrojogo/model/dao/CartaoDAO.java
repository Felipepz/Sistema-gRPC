
 
package cadastrojogo.model.dao;

import cadastrojogo.model.Arbitro;
import cadastrojogo.model.Cartao;
import cadastrojogo.model.Jogador;
import cadastrojogo.model.pJogo;
import cadastrojogo.model.dao.pJogoDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class CartaoDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Cartao cartao) {
        String sql = "INSERT INTO cartao(cdArbitro, cdJogador, cdJogo, data, corCartao, quantidade) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cartao.getArbitro().getCdArbitro());
            stmt.setInt(2, cartao.getNomeJogador().getCdJogador());
            stmt.setInt(3, cartao.getJogo().getCdJogo());
            stmt.setDate(4, Date.valueOf(cartao.getData()));
            stmt.setString(5, cartao.getCorCartao());
            stmt.setInt(6, cartao.getQuantidade());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean alterar(Cartao cartao) {
        String sql = "UPDATE cartao SET cdArbitro=?, cdJogador=?, cdJogo=?, data=?, corCartao=?, quantidade=? WHERE cdCartao=?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cartao.getArbitro().getCdArbitro());
            stmt.setInt(2, cartao.getNomeJogador().getCdJogador());
            stmt.setInt(3, cartao.getJogo().getCdJogo());
            stmt.setDate(4, Date.valueOf(cartao.getData()));
            stmt.setString(5, cartao.getCorCartao());
            stmt.setInt(6, cartao.getQuantidade());
            stmt.setInt(7, cartao.getCdCartao());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean remover(Cartao cartao) {
        String sql = "DELETE FROM cartao  WHERE cdCartao=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cartao.getCdCartao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
       
    
    public List<Cartao> listar() {
    String sql = "SELECT * FROM cartao";
    List<Cartao> retorno = new ArrayList<>();
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            ArbitroDAO arbitroDAO = new ArbitroDAO();
            arbitroDAO.setConnection(connection);
            Arbitro arbitro = new Arbitro();
            JogadorDAO jogadorDAO = new JogadorDAO();
            jogadorDAO.setConnection(connection);
            Jogador jogador = new Jogador();
            pJogoDAO jogoDAO = new pJogoDAO();
            jogoDAO.setConnection(connection);
            pJogo jogo = new pJogo(); 
            
            Cartao cartao = new Cartao();
            cartao.setCdCartao(resultado.getInt("cdCartao"));
            cartao.setCorCartao(resultado.getString("corCartao"));
            cartao.setQuantidade(resultado.getInt("quantidade"));
            cartao.setData(resultado.getDate("data").toLocalDate());
            
            arbitro.setCdArbitro(resultado.getInt("cdArbitro"));
            jogador.setCdJogador(resultado.getInt("cdJogador"));
            jogo.setCdJogo(resultado.getInt("cdJogo"));
            
            arbitro = arbitroDAO.buscar(arbitro);
            jogador = jogadorDAO.buscarNovo(jogador);
            jogo = jogoDAO.buscarNovo(jogo);
            
            cartao.setArbitro(arbitro);
            cartao.setNomeJogador(jogador);
            cartao.setJogo(jogo);
            retorno.add(cartao);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return retorno;
}
    


    
    public Cartao buscar(Cartao cartao) {
        String sql = "SELECT * FROM cartao WHERE cdCartao=?";
        Cartao retorno = new Cartao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cartao.getCdCartao());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                cartao.setCorCartao(resultado.getString("corCartao"));
                cartao.setQuantidade(resultado.getInt("quantidade"));               
            
                retorno = cartao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    } 

    
    public Map<Integer, ArrayList> listarQuantidadeCartaoPorMes() {
        String sql = "select count(cdCartao), extract(year from data) as ano, extract(month from data) as mes from cartao group by ano, mes order by ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(resultado.getInt("ano")))
                {
                    linha.add(resultado.getInt("mes"));
                    linha.add(resultado.getInt("count"));
                    retorno.put(resultado.getInt("ano"), linha);
                }else{
                    ArrayList linhaNova = retorno.get(resultado.getInt("ano"));
                    linhaNova.add(resultado.getInt("mes"));
                    linhaNova.add(resultado.getInt("count"));
                }
            }
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
     
    
    
}

