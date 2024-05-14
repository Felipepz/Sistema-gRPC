/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import cadastrojogo.model.Equipe;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import cadastrojogo.model.Jogador;
import cadastrojogo.model.dao.EquipeDAO;
import cadastrojogo.model.dao.JogadorDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;

public class FXMLCadastroJogadorController implements Initializable {

    @FXML
    private JFXTextField nomeJogador;

    @FXML
    private JFXTextField NumeroCamisa;

    @FXML
    private JFXTextField Idade;

    @FXML
    private JFXTextField Nacionalidade;

    @FXML
    private JFXTextField Posicao;

    @FXML
    private JFXTextField Altura;

    @FXML
    private JFXComboBox<Equipe> Time;

    @FXML
    private TableView<Jogador> tabelaJogador;

    @FXML
    private TableColumn<Jogador, String> colunaNome;

    @FXML
    private TableColumn<Jogador, String> colunaCamisa;

    @FXML
    private TableColumn<Jogador, String> colunaIdade;

    @FXML
    private TableColumn<Jogador, String> colunaAltura;

    @FXML
    private TableColumn<Jogador, String> colunaNacionalidade;

    @FXML
    private TableColumn<Jogador, String> colunaPosicao;

    @FXML
    private JFXButton btndeletar;

    @FXML
    private JFXButton btnalterar;

    @FXML
    private JFXButton btnSalvar;
    
    private List<Equipe> listEquipe;
    private ObservableList<Equipe> observableListEquipe;
    
    private List<Jogador> listJogador;
    private ObservableList<Jogador> observableListJogador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboBoxEquipe();
        carregarTableViewJogador();
        
        tabelaJogador.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewJogador(newValue));

    }
    
    public void carregarComboBoxEquipe() {
        Database database = DatabaseFactory.getDatabase("postgresql");
        Connection connection = database.conectar();
        EquipeDAO equipeDAO = new EquipeDAO();
        equipeDAO.setConnection(connection);
        listEquipe = equipeDAO.listar();
        observableListEquipe = FXCollections.observableArrayList(listEquipe);
        Time.setItems(observableListEquipe);
    }
    
    public void carregarTableViewJogador() {
        Database database = DatabaseFactory.getDatabase("postgresql");
        Connection connection = database.conectar();
        
        JogadorDAO jogadorDAO = new JogadorDAO();
        jogadorDAO.setConnection(connection);
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colunaCamisa.setCellValueFactory(new PropertyValueFactory<>("Camisa"));
        colunaIdade.setCellValueFactory(new PropertyValueFactory<>("Idade"));
        colunaAltura.setCellValueFactory(new PropertyValueFactory<>("Altura"));
        colunaNacionalidade.setCellValueFactory(new PropertyValueFactory<>("Nacionalidade"));
        colunaPosicao.setCellValueFactory(new PropertyValueFactory<>("Posicao"));

        listJogador = jogadorDAO.listar();

        observableListJogador = FXCollections.observableArrayList(listJogador);
        tabelaJogador.setItems(observableListJogador);
        
    }  
    
    public void selecionarItemTableViewJogador(Jogador jogador){
        if(jogador != null){
        nomeJogador.setText(jogador.getNome());
        NumeroCamisa.setText(jogador.getCamisa());
        Idade.setText(jogador.getIdade());
        Nacionalidade.setText(jogador.getNacionalidade());
        Posicao.setText(jogador.getPosicao());
        Altura.setText(jogador.getAltura());
        } else {
            
        nomeJogador.setText(jogador.getNome());
        NumeroCamisa.setText(jogador.getCamisa());
        Idade.setText(jogador.getIdade());
        Nacionalidade.setText(jogador.getNacionalidade());
        Posicao.setText(jogador.getPosicao());
        Altura.setText(jogador.getAltura());
            
        }
    }

    @FXML
    void salvarJogador(ActionEvent event) throws SQLException {
        Database database = DatabaseFactory.getDatabase("postgresql");
        try (Connection connection = database.conectar()) {
            Jogador jogador = new Jogador();
            JogadorDAO jogadorDAO = new JogadorDAO();
            
            jogador.setNome(nomeJogador.getText());
            jogador.setCamisa(NumeroCamisa.getText());
            jogador.setIdade(Idade.getText());
            jogador.setAltura(Altura.getText());
            jogador.setNacionalidade(Nacionalidade.getText());
            jogador.setPosicao(Posicao.getText());
            jogador.setEquipe(Time.getPromptText());
            jogador.setQuantidadeAtual(0);
            
            jogadorDAO.setConnection(connection);
            jogadorDAO.inserir(jogador);
            tabelaJogador.refresh();
        }
    }
    
    @FXML
    void alterar(ActionEvent event) throws SQLException {

        Database database = DatabaseFactory.getDatabase("postgresql");
        try (Connection connection = database.conectar()) {
            Jogador jogador = new Jogador();
            Jogador resultado = new Jogador();
            JogadorDAO jogadorDAO = new JogadorDAO();
            
            jogador.setNome(nomeJogador.getText());
            jogador.setCamisa(NumeroCamisa.getText());
            jogador.setIdade(Idade.getText());
            jogador.setAltura(Altura.getText());
            jogador.setNacionalidade(Nacionalidade.getText());
            jogador.setPosicao(Posicao.getText());
            jogador.setEquipe(Time.getPromptText());
            jogador.setQuantidadeAtual(0);
            
            jogadorDAO.setConnection(connection);
            resultado = jogadorDAO.buscar(jogador);
            jogadorDAO.alterar(resultado);
            tabelaJogador.refresh();
        }
    }
    
    @FXML
    void deletar(ActionEvent event) throws SQLException {

        Database database = DatabaseFactory.getDatabase("postgresql");
        try (Connection connection = database.conectar()) {
            Jogador jogador = new Jogador();
            Jogador resultado = new Jogador();
            JogadorDAO jogadorDAO = new JogadorDAO();
            
            jogador.setNome(nomeJogador.getText());
            jogador.setCamisa(NumeroCamisa.getText());
            jogador.setIdade(Idade.getText());
            jogador.setAltura(Altura.getText());
            jogador.setNacionalidade(Nacionalidade.getText());
            jogador.setPosicao(Posicao.getText());
            jogador.setEquipe(Time.getPromptText());
            jogador.setQuantidadeAtual(0);
            
            jogadorDAO.setConnection(connection);
            resultado = jogadorDAO.buscar(jogador);
            jogadorDAO.remover(resultado);
            tabelaJogador.refresh();
        }
    }

}
