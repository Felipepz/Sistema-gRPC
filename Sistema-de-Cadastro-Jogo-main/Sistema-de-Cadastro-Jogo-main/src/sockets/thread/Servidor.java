
package sockets.thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("A porta 12345 foi aberta");
        while (true) {
            Socket socket = servidor.accept();
            System.out.println("Um cliente se conectou");
            ThreadSockets thread = new ThreadSockets(socket);
            thread.start();
        }
    }

    private static class ThreadSockets extends Thread {

        private final Socket clienteSocket;

        List<String> equipe = new ArrayList<>();

        public ThreadSockets(Socket s) throws IOException {
            this.clienteSocket = s;

            // Entrada de Dados no Servidor
            DataInputStream entrada = new DataInputStream(s.getInputStream());
            Integer id = entrada.readInt();
            System.out.println("id: " + id);

            switch (id) {
                case 1:
                    this.equipe.add("Magno Leal De Brito Junior");
                    this.equipe.add("Renan Gomes Poggian");
                    this.equipe.add("Vitor Miller de Toledo");
                    break;
                case 2:
                    this.equipe.add("Milena Melo Linhares");
                    this.equipe.add("Mirielle Rosa de Souza");
                    this.equipe.add("Yuri Fabres de Jesus Figueira");
                    break;
                case 3:
                    this.equipe.add("Gabriely Machado Carrari");
                    this.equipe.add("Alda Maria Norbiato Torres");
                    this.equipe.add("Fernanda De Oliveira Ramos");
                    break;
                case 4:
                    this.equipe.add("Marcelo Alves Santos");
                    this.equipe.add("Livia Guimarães De Jesus");
                    this.equipe.add("Gabriel Cardoso Girarde");
                    break;
                case 5:
                    this.equipe.add("Matheus da Silva Módolo");
                    this.equipe.add("Sofia Pereira Bachetti Sartorio");
                    this.equipe.add("Larissa Paganini");
                    break;
                case 6:
                    this.equipe.add("Silvio Lopes Ribeiro");
                    this.equipe.add("Murillo Carlete Ribeiro");
                    this.equipe.add("Róger Corrente Pinto");
                    break;
                case 7:
                    this.equipe.add("Douglas Altoé Stein da Silva");
                    this.equipe.add("Diego Chaves Ravani");
                    this.equipe.add("Carlos Eduardo de Menezes Pacheco");
                    break;
                case 8:
                    this.equipe.add("Eduardo Souto Siqueira Santana");
                    this.equipe.add("Rafael Savignon de Resende");
                    this.equipe.add("Felipe Moreira da Paz");
                    break;
                case 9:
                    this.equipe.add("Wesley Pereira da Silva");
                    this.equipe.add("João Victor de Salles Mapeli Couzzi");
                    this.equipe.add("João Victor Maciel Campos");
                    break;
                case 10:
                    this.equipe.add("Igor Almeida da Silva");
                    this.equipe.add("Raphael Pavani Manhães Bersot");
                    this.equipe.add("Júlia Borges Santos");
                    break;
                default:
                    this.equipe.add("Número da equipe informado está incorreto!");
            }

        }

        public void run() {

            System.out.println(Thread.currentThread().getName());
            try {
                //Saída de Dados do Servidor
                ObjectOutputStream saida = new ObjectOutputStream(clienteSocket.getOutputStream());
                saida.writeObject(equipe); //Enviando notícias para Cliente
                clienteSocket.close();
            } catch (IOException ioe) {
                System.out.println("Erro: " + ioe.toString());
            }
        }

    }
}
