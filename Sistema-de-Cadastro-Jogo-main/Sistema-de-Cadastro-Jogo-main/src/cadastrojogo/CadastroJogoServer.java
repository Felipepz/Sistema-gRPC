/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo;

import cadastrojogo.javafx.mensageiro.CadastroJogoGrpc;
import cadastrojogo.model.DadosTabela;
import cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest;
import cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import javafx.stage.Stage;

public class CadastroJogoServer {
    public static void main(String[] args) throws Exception {
        // Iniciar o servidor gRPC na porta 8080
        Server server = ServerBuilder.forPort(8080)
                .addService(new CadastroJogoServiceImpl())
                .build();

        System.out.println("Servidor gRPC iniciado na porta 8080");
        server.start();
        server.awaitTermination();
    }

    static class CadastroJogoServiceImpl extends CadastroJogoGrpc.CadastroJogoImplBase {
        public void inserirDadosJogo(DadosJogoRequest request, StreamObserver<DadosJogoResponse> responseObserver) {
            // Imprimir os dados recebidos do cliente
            System.out.println("Dados Recebidos do Cliente:");
            System.out.println("Time Fora: " + request.getTimeFora());
            System.out.println("Time Casa: " + request.getTimeCasa());
            System.out.println("Gols Fora: " + request.getGolsFora());
            System.out.println("Gols Casa: " + request.getGolsCasa());
            System.out.println("Árbitro: " + request.getArbitro());
            //DadosTabela.exibirTabela(request.getTimeFora(), request.getTimeCasa(), request.getGolsFora(), request.getGolsCasa(), request.getArbitro());
            
            int GF = Integer.parseInt(request.getGolsFora());
            int GC = Integer.parseInt(request.getGolsCasa());
            String mensagem = "teste";

            if(GF > GC){
                mensagem = request.getTimeFora();
            }else if(GF < GC){
                mensagem = request.getTimeCasa();
            }else if(GF == GC){
                mensagem = "Empate";
            }
            
            // Construir a resposta
            DadosJogoResponse response = DadosJogoResponse.newBuilder()
                    .setMensagem(mensagem)
                    .build();

            // Enviar a resposta ao cliente
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
