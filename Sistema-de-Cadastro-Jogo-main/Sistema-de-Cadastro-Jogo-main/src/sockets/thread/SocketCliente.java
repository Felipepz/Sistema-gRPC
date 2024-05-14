/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets.thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketCliente {

    public static List<String> conectar() {
        int numeroGrupo = 8; // Defina o número do grupo desejado

        String enderecoIP = "127.0.0.1"; // Defina o endereço IP do servidor
        int porta = 12345; // Defina a porta do servidor
        List<String> equipe = new ArrayList();

        try {
            Socket socket = new Socket(enderecoIP, porta);
            System.out.println("Conectado ao servidor");

            // Enviar o número do grupo para o servidor
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            saida.writeInt(numeroGrupo);
            System.out.println("depois do envio do número");

            // Receber a lista de strings do servidor
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            equipe = (List<String>) entrada.readObject();

            // Imprimir a lista de strings
            for (String membro : equipe) {
                System.out.println(membro);
            }

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return equipe;
    }
}
