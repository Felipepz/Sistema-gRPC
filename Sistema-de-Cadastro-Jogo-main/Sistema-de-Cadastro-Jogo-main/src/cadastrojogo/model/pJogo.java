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
public class pJogo {
    
    int cdJogo;
    String timeFora;
    String timeCasa;
    String golFora;
    String golCasa;
    String Arbitro;
    String Vencedor;
    
    

    public pJogo(int cdJogo, String timeFora, String timeCasa, String golFora, String golCasa, String Arbitro, String Vencedor) {
        this.cdJogo = cdJogo;
        this.timeFora = timeFora;
        this.timeCasa = timeCasa;
        this.golFora = golFora;
        this.golCasa = golCasa;
        this.Arbitro = Arbitro;
        this.Vencedor = Vencedor;
    }

    public pJogo(String timeFora, String timeCasa, String golFora, String golCasa, String Arbitro, String Vencedor) {
        this.timeFora = timeFora;
        this.timeCasa = timeCasa;
        this.golFora = golFora;
        this.golCasa = golCasa;
        this.Arbitro = Arbitro;
        this.Vencedor = Vencedor;
    }
    
    

    public pJogo() {
        
    }

    public int getCdJogo() {
        return cdJogo;
    }

    public void setCdJogo(int cdJogo) {
        this.cdJogo = cdJogo;
    }

    public String getTimeFora() {
        return timeFora;
    }

    public void setTimeFora(String timeFora) {
        this.timeFora = timeFora;
    }

    public String getTimeCasa() {
        return timeCasa;
    }

    public void setTimeCasa(String timeCasa) {
        this.timeCasa = timeCasa;
    }

    public String getGolFora() {
        return golFora;
    }

    public void setGolFora(String golFora) {
        this.golFora = golFora;
    }

    public String getGolCasa() {
        return golCasa;
    }

    public void setGolCasa(String golCasa) {
        this.golCasa = golCasa;
    }

    public String getArbitro() {
        return Arbitro;
    }

    public void setArbitro(String Arbitro) {
        this.Arbitro = Arbitro;
    }

    public String getVencedor() {
        return Vencedor;
    }

    public void setVencedor(String Vencedor) {
        this.Vencedor = Vencedor;
    }
    
    
    
    @Override
    public String toString() {
        return timeFora + " x " + timeCasa;
    }

    
    
    
    
}
