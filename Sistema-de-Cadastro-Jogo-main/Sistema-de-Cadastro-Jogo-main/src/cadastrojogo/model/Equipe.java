/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.model;

import java.time.LocalDate;


/**
 *
 * @author 20211si039
 */
public class Equipe {
    private int idCadastroEquipe;
    private String Liga;
    
    private String Pais;
    private String Tecnico;
    private String nome;
     private LocalDate anofundacao;

    public Equipe(){
    }
    
    public Equipe(int idCadastroEquipe, String Liga, String Pais, String Tecnico,String nome,LocalDate anofundacao) {
        this.idCadastroEquipe = idCadastroEquipe;
        this.Liga = Liga;
        this.Pais = Pais;
        this.Tecnico = Tecnico;
        this.nome = nome; 
        this.anofundacao = anofundacao;
    }

    public int getidCadastroEquipe() {
        return idCadastroEquipe;
    }

    public void setidCadastroEquipe(int idCadastroEquipe) {
        this.idCadastroEquipe = idCadastroEquipe;
    }

    public String getLiga() {
        return Liga;
    }

    public void setLiga(String Liga) {
        this.Liga = Liga;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }
    
    public String getnome(){
        return nome;
    }
    
    public void setnome(String nome){
        this.nome = nome;        
    } 
    
    public LocalDate getanofundacao(){
        return anofundacao;
    }
    
    public void setanofundacao(LocalDate anofundacao){
        this.anofundacao = anofundacao;
    }
    
    public String getTecnico(){
        return Tecnico;
    }
    
    public void setTecnico(String Tecnico){
        this.Tecnico = Tecnico;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}


