
package cadastrojogo.model;



import java.io.Serializable;
import java.time.LocalDate;


/**
 *
 * @author Felipe
 */
public class Cartao implements Serializable{
    
    private int cdCartao;
    private Arbitro arbitro;
    private Jogador nomeJogador;
    private pJogo jogo;
    private String corCartao;
    private LocalDate data;
    private int quantidade;
    private int quantidadeAtual;

    public Cartao(){
    }
    
    public Cartao(int cdCartao, String corCartao,int quantidade, int quantidadeAtual, Arbitro arbitro, Jogador nomeJogador, pJogo jogo,LocalDate data ) {     
      this.cdCartao = cdCartao;
      this.arbitro = arbitro;
      this.nomeJogador = nomeJogador;
      this.jogo = jogo;
      this.corCartao = corCartao;
      this.quantidade = quantidade;   
      this.quantidadeAtual = quantidadeAtual;
      this.data = data;
    }

    /**
     * @return the cdCartao
     */
    public int getCdCartao() {
        return cdCartao;
    }

    /**
     * @param cdCartao the cdCartao to set
     */
    public void setCdCartao(int cdCartao) {
        this.cdCartao = cdCartao;
    }

    /**
     * @return the arbitro
     */
    public Arbitro getArbitro() {
        return arbitro;
    }

    /**
     * @param arbitro the arbitro to set
     */
    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    /**
     * @return the nomeJogador
     */
    public Jogador getNomeJogador() {
        return nomeJogador;
    }

    /**
     * @param nomeJogador the nomeJogador to set
     */
    public void setNomeJogador(Jogador nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    /**
     * @return the corCartao
     */
    public String getCorCartao() {
        return corCartao;
    }

    /**
     * @param corCartao the corCartao to set
     */
    public void setCorCartao(String corCartao) {
        this.corCartao = corCartao;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public int getQuantidadeAtual(){
        return quantidadeAtual;
    }
    
    public void setQuantidadeAtual(int quantidadeAtual){
        this.quantidadeAtual = quantidadeAtual;
    }
    
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
  
    
     public pJogo getJogo() {
        return jogo;
    }

 
    public void setJogo(pJogo jogo) {
        this.jogo = jogo;
    }
    
    
    @Override
    public String toString() {
      return nomeJogador.getNome();
    }
    
    

}
