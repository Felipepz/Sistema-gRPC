/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.model.dao;

import cadastrojogo.model.Equipe;
import cadastrojogo.model.Jogador;
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
 * @author Usuario
 */
public class JogadorDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Jogador jogador) {
        String sql = "INSERT INTO jogador(nome, camisa, idade, nacionalidade, posicao, altura, quantidadeAtual, equipe) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getCamisa());
            stmt.setString(3, jogador.getIdade());
            stmt.setString(4, jogador.getNacionalidade());
            stmt.setString(5, jogador.getPosicao());
            stmt.setString(6, jogador.getAltura());
            stmt.setInt(7, jogador.getQuantidadeAtual());
            stmt.setString(8, jogador.getEquipe());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deletar(Jogador jogador) {
        String sql = "DELETE FROM jogador WHERE cdjogador = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, jogador.getCdJogador());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deletarNovo(Jogador jogador) {
        String sql = "DELETE FROM jogador(nome, Camisa, Idade, Nacionalidade, Posicao, Altura, quantidadeAtual) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getCamisa());
            stmt.setString(3, jogador.getIdade());
            stmt.setString(4, jogador.getNacionalidade());
            stmt.setString(5, jogador.getPosicao());
            stmt.setString(6, jogador.getAltura());
            stmt.setInt(7, jogador.getQuantidadeAtual());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Jogador jogador) {
        String sql = "UPDATE jogador SET nome=?, camisa=?, idade=?, nacionalidade=?, posicao=?, altura=?, quantidadeAtual=?, equipe=? WHERE cdjogador=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getCamisa());
            stmt.setString(3, jogador.getIdade());
            stmt.setString(4, jogador.getNacionalidade());
            stmt.setString(5, jogador.getPosicao());
            stmt.setString(6, jogador.getAltura());
            stmt.setInt(7, jogador.getQuantidadeAtual());
            stmt.setString(8, jogador.getEquipe());
            stmt.setInt(9, jogador.getCdJogador());
 
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterarNovo(Jogador jogador) {
        String sql = "UPDATE jogador SET nome=?, camisa=?, Idade=?, nacionalidade=?, posicao=?, altura=?, quantidadeAtual=? WHERE cdjogador=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getCamisa());
            stmt.setString(3, jogador.getIdade());
            stmt.setString(4, jogador.getNacionalidade());
            stmt.setString(5, jogador.getPosicao());
            stmt.setString(6, jogador.getAltura());
            stmt.setInt(7, jogador.getQuantidadeAtual());
            stmt.setInt(8, jogador.getCdJogador());
 
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }


    public boolean remover(Jogador jogador) {
        String sql = "DELETE FROM jogador WHERE cdjogador=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, jogador.getCdJogador());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Jogador> listar() {
        String sql = "SELECT * FROM jogador";
        List<Jogador> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Jogador jogador = new Jogador();
                jogador.setCdJogador(resultado.getInt("cdJogador"));
                jogador.setNome(resultado.getString("nome"));
                jogador.setAltura(resultado.getString("altura"));
                jogador.setCamisa(resultado.getString("camisa"));
                jogador.setIdade(resultado.getString("idade"));
                jogador.setNacionalidade(resultado.getString("nacionalidade"));
                jogador.setPosicao(resultado.getString("posicao"));
                jogador.setQuantidadeAtual(resultado.getInt("quantidadeAtual"));
                jogador.setEquipe(resultado.getString("equipe"));
                retorno.add(jogador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Jogador buscar(Jogador jogador) {
        String sql = "SELECT * FROM jogador WHERE nome=?";
        Jogador retorno = new Jogador();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, jogador.getNome());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                jogador.setNome(resultado.getString("nome"));
                jogador.setAltura(resultado.getString("altura"));
                jogador.setCamisa(resultado.getString("camisa"));
                jogador.setIdade(resultado.getString("idade"));
                jogador.setNacionalidade(resultado.getString("nacionalidade"));
                jogador.setPosicao(resultado.getString("posicao"));
                jogador.setQuantidadeAtual(resultado.getInt("quantidadeAtual"));
                jogador.setEquipe(resultado.getString("equipe"));
                jogador.setCdJogador(resultado.getInt("cdjogador"));
                retorno = jogador;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Jogador buscarNovo(Jogador jogador) {
        String sql = "SELECT * FROM jogador WHERE cdjogador=?";
        Jogador retorno = new Jogador();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, jogador.getCdJogador());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                jogador.setNome(resultado.getString("nome"));
                jogador.setAltura(resultado.getString("altura"));
                jogador.setCamisa(resultado.getString("camisa"));
                jogador.setIdade(resultado.getString("idade"));
                jogador.setNacionalidade(resultado.getString("nacionalidade"));
                jogador.setPosicao(resultado.getString("posicao"));
                jogador.setQuantidadeAtual(resultado.getInt("quantidadeAtual"));
                retorno = jogador;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
