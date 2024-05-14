package cadastrojogo.controller;

import cadastrojogo.javafx.mensageiro.CadastroJogoGrpc;
import cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest;
import cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse;
import cadastrojogo.model.Equipe;
import cadastrojogo.model.dao.pJogoDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import cadastrojogo.model.pJogo;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLInformacoesGrpcController implements Initializable {

    @FXML
    private TableView<pJogo> tableViewDados;

    @FXML
    private TableColumn<pJogo, String> tableColumnTimeCasa;

    @FXML
    private TableColumn<pJogo, String> tableColumnTimeFora;

    @FXML
    private TableColumn<pJogo, String> tableColumnArbitro;

    @FXML
    private TableColumn<pJogo, String> tableColumnGolCasa;

    @FXML
    private TableColumn<pJogo, String> tableColumnGolFora;
    
    @FXML
    private TableColumn<pJogo, String> tableColumnTimeVencedor;
    
    private List<pJogo> jogo;
    private ObservableList<pJogo> observableListJogo;
    
    private List<Equipe> listTimeCasa;
    private ObservableList<Equipe> observableListTC;
    
    private List<Equipe> listTimeFora;
    private ObservableList<Equipe> observableListTF;
    
    // Método para inicializar o servidor gRPC
    public static String inicializarServidor(String TF, String TC, String GF, String GC, String arbitro) {
        // Criar um canal gRPC para se conectar ao servidor na porta 8080
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()  // Usar comunicação não segura (apenas para exemplo)
                .build();

        // Criar um stub (cliente) gRPC
        CadastroJogoGrpc.CadastroJogoBlockingStub blockingStub = CadastroJogoGrpc.newBlockingStub(channel);

        // Criar uma mensagem de requisição
        DadosJogoRequest request = DadosJogoRequest.newBuilder()
                .setTimeFora(TF)
                .setTimeCasa(TC)
                .setGolsFora(GF)
                .setGolsCasa(GC)
                .setArbitro(arbitro)
                .build();

        // Chamar o serviço remoto
        DadosJogoResponse response = blockingStub.inserirDadosJogo(request);

        // Processar a resposta
        System.out.println("Resposta do Servidor: " + response.getMensagem());
        String resultado = response.getMensagem();

        // Fechar o canal gRPC ao finalizar
        channel.shutdown();
        return resultado;
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialização lógica do controlador, se necessário
        carregarTableViewJogo();
    }
    
    public void carregarTableViewJogo() {
        Database database = DatabaseFactory.getDatabase("postgresql");
        Connection connection = database.conectar();
        
        pJogoDAO pjogoDAO = new pJogoDAO();
        pjogoDAO.setConnection(connection);
        tableColumnTimeFora.setCellValueFactory(new PropertyValueFactory<>("timeFora"));
        tableColumnTimeCasa.setCellValueFactory(new PropertyValueFactory<>("timeCasa"));
        tableColumnGolFora.setCellValueFactory(new PropertyValueFactory<>("golFora"));
        tableColumnGolCasa.setCellValueFactory(new PropertyValueFactory<>("golCasa"));
        tableColumnArbitro.setCellValueFactory(new PropertyValueFactory<>("Arbitro"));
        tableColumnTimeVencedor.setCellValueFactory(new PropertyValueFactory<>("Vencedor"));

        jogo = pjogoDAO.listar();

        observableListJogo = FXCollections.observableArrayList(jogo);
        tableViewDados.setItems(observableListJogo);
        
        
        int rowCount = tableViewDados.getItems().size();
        List<pJogo> temp = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            String TF = tableColumnTimeFora.getCellData(i).toString();
            String TC = tableColumnTimeCasa.getCellData(i).toString();
            String GF = tableColumnGolFora.getCellData(i).toString();
            String GC = tableColumnGolCasa.getCellData(i).toString();
            String AR = tableColumnArbitro.getCellData(i).toString();
            String resultado = inicializarServidor(TF, TC, GF, GC, AR);
            temp.add(new pJogo(TF, TC, GF, GC, AR, resultado));
        }

        ObservableList<pJogo> observableListTemp = FXCollections.observableArrayList(temp);
        tableViewDados.setItems(observableListTemp);

    }  

}
