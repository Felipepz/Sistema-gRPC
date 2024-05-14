package sockets.thread;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class ThreadCliente implements Runnable {

    private List<String> equipe;
    private Label labelIntegrantes; // Supondo que você tenha uma instância de Label

    public ThreadCliente(List<String> equipe, Label labelIntegrantes) {
        this.equipe = equipe;
        this.labelIntegrantes = labelIntegrantes;
    }

    

    public void run() {
        while (true) {
            for (String nome : equipe) {
                Platform.runLater(() -> labelIntegrantes.setText(nome));

                try {
                    Thread.sleep(1000); // Pausa de 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



