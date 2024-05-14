package cadastrojogo.controller;

import cadastrojogo.model.Equipe;
import cadastrojogo.model.dao.CartaoDAO;
import cadastrojogo.model.dao.EquipeDAO;
import cadastrojogo.model.dao.pJogoDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import cadastrojogo.model.pJogo;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class FXMLGraficoJogoController implements Initializable {

    @FXML
    private BarChart<?, ?> grafico;

    @FXML
    private CategoryAxis barraTimes;

    @FXML
    private NumberAxis barraNumerodeJogos;
    
    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final EquipeDAO equipeDAO = new EquipeDAO();
    private final pJogoDAO jogoDAO = new pJogoDAO();
    
    private List<Equipe> equipes = new ArrayList();
    private List<pJogo> jogo = new ArrayList();
    private List<String> Times = new ArrayList();
    private List<String> jogos = new ArrayList();
    private List<Integer> frequencia = new ArrayList();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Equipe retornoEquipe;
        pJogo retornoJogo;
        Integer retornoFrequencia;
        
        equipeDAO.setConnection(connection);
        jogoDAO.setConnection(connection);
        equipes = equipeDAO.listar();
        jogo = jogoDAO.listar();
        
        for(int i = 0; i < equipes.size(); i++){
            retornoEquipe = equipes.get(i);
            Times.add(retornoEquipe.toString());
        }
        
        for(int i = 0; i < jogo.size(); i++){
            retornoJogo = jogo.get(i);
            jogos.add(retornoJogo.getTimeCasa());
        }
        
        for(int i = 0; i < jogo.size(); i++){
            retornoJogo = jogo.get(i);
            jogos.add(retornoJogo.getTimeFora());
        }
        
        for(int i = 0; i < Times.size(); i++){
            retornoFrequencia = Collections.frequency(jogos, Times.get(i));
            frequencia.add(retornoFrequencia);
        }
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Jogos Por Time");
        
        for(int i = 0; i < Times.size(); i++){
            series1.getData().add(new XYChart.Data(Times.get(i), frequencia.get(i)));
        }
        
        grafico.getData().add(series1);
    }
}
