/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.model;

/**
 *
 * @author Usuario
 */
public class Jogador {
    
    int CdJogador;
    String Nome;
    String Camisa;
    String Idade;
    String Equipe;
    String Nacionalidade;
    String Posicao;
    String Altura;
    private int quantidadeAtual;
    
     public Jogador(){
    }

    public Jogador(int CdJogador, String Nome, String Camisa, String Idade, String Equipe, String Nacionalidade, String Posicao, String Altura, int quantidadeAtual) {
        this.CdJogador = CdJogador;
        this.Nome = Nome;
        this.Camisa = Camisa;
        this.Idade = Idade;
        this.Equipe = Equipe;
        this.Nacionalidade = Nacionalidade;
        this.Posicao = Posicao;
        this.Altura = Altura;
        this.quantidadeAtual = quantidadeAtual;
    }

    public String getEquipe() {
        return Equipe;
    }

    public void setEquipe(String Equipe) {
        this.Equipe = Equipe;
    }


    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCamisa() {
        return Camisa;
    }

    public void setCamisa(String Camisa) {
        this.Camisa = Camisa;
    }

    public String getIdade() {
        return Idade;
    }

    public void setIdade(String Idade) {
        this.Idade = Idade;
    }

    public String getNacionalidade() {
        return Nacionalidade;
    }

    public void setNacionalidade(String Nacionalidade) {
        this.Nacionalidade = Nacionalidade;
    }

    public String getPosicao() {
        return Posicao;
    }

    public void setPosicao(String Posicao) {
        this.Posicao = Posicao;
    }

    public String getAltura() {
        return Altura;
    }

    public void setAltura(String Altura) {
        this.Altura = Altura;
    }

    public int getCdJogador() {
        return CdJogador;
    }

    public void setCdJogador(int CdJogador) {
        this.CdJogador = CdJogador;
    }
    
    public int getQuantidadeAtual(){
        return quantidadeAtual;
    }
    
    public void setQuantidadeAtual(int quantidadeAtual){
        this.quantidadeAtual = quantidadeAtual;
    }
    
    
    @Override
    public String toString() {
        return Nome;
    }
    
    
}
