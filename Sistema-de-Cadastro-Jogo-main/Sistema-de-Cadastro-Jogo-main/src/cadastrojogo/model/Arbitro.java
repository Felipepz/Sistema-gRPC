
package cadastrojogo.model;

import java.time.LocalDate;

/**
 *
 * @author Felipe
 */
public class Arbitro {
    
    private int cdArbitro;
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private String nacionalidade;
    private String formacao;
    

    public Arbitro(){
    }
    
    public Arbitro(int cdArbitro, String nome, String cpf, LocalDate dataDeNascimento,String nacionalidade,String formacao) {
        this.cdArbitro = cdArbitro;
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.nacionalidade = nacionalidade; 
        this.formacao = formacao;
    }

    public int getCdArbitro() {
        return cdArbitro;
    }

    public void setCdArbitro(int cdArbitro) {
        this.cdArbitro = cdArbitro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public LocalDate getDataDeNascimento(){
        return dataDeNascimento;
    }
    
    public void setDataDeNascimento(LocalDate dataDeNascimento){
        this.dataDeNascimento = dataDeNascimento;        
    } 
    
    public String getNacionalidade(){
        return nacionalidade;
    }
    
    public void setNacionalidade(String nacionalidade){
        this.nacionalidade = nacionalidade;
    }
    
    public String getFormacao(){
        return formacao;
    }
    
    public void setFormacao(String formacao){
        this.formacao = formacao;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
    
}
