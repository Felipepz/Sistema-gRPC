package cadastrojogo.model;




public class Gol {

    private int idGol;
    private Jogador camisaJogador;
    private Jogador nomeJogador;
    private Equipe nomeEquipe;
    private pJogo jogo;
    private String Tempo;

    public Gol() {
    }

    public Gol(int idGol, Jogador camisaJogador, Jogador nomeJogador, Equipe nomeEquipe, pJogo jogo, String Tempo) {

        this.idGol = idGol;
        this.camisaJogador = camisaJogador;
        this.nomeJogador = nomeJogador;
        this.nomeEquipe = nomeEquipe;
        this.jogo = jogo;
        this.Tempo = Tempo;
    }

    public int getidGol() {
        return idGol;
    }

    public void setidGol(int idGol) {
        this.idGol = idGol;
    }

    public Jogador getcamisaJogador() {
        return camisaJogador;
    }

    public void setcamisaJogador(Jogador camisaJogador) {
        this.camisaJogador = camisaJogador;
    }

    public Jogador getnomeJogador() {
        return camisaJogador;
    }

    public void setnomeJogador(Jogador nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public pJogo getJogo() {
        return jogo;
    }

    public void setPais(pJogo jogo) {
        this.jogo = jogo;
    }

    public String getTempo() {
        return Tempo;
    }

    public void setTempo(String Tempo) {
        this.Tempo = Tempo;
    }

    @Override
    public String toString() {
        return this.nomeJogador.getNome();
    }

}
