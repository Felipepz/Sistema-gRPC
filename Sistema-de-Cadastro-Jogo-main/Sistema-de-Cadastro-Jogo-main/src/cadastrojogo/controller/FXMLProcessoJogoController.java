/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import cadastrojogo.model.Equipe;
import cadastrojogo.model.Jogador;
import cadastrojogo.model.dao.EquipeDAO;
import cadastrojogo.model.dao.JogadorDAO;
import cadastrojogo.model.dao.pJogoDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import cadastrojogo.model.pJogo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class FXMLProcessoJogoController implements Initializable {

    @FXML
    private JFXComboBox<Equipe> cbTimeFora;

    @FXML
    private JFXComboBox<Equipe> cbTimeCasa;

    @FXML
    private JFXTextField textfieldArbitro;

    @FXML
    private JFXTextField textfieldGF;

    @FXML
    private JFXTextField textfieldGC;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnAlterar;

    @FXML
    private JFXButton btnDeletar;

    @FXML
    private TableView<pJogo> tabela;

    @FXML
    private TableColumn<pJogo, String> colunaTimeFora;

    @FXML
    private TableColumn<pJogo, String> colunaTimeCasa;

    @FXML
    private TableColumn<pJogo, String> colunaGolFora;

    @FXML
    private TableColumn<pJogo, String> colunaGolCasa;

    @FXML
    private TableColumn<pJogo, String> colunaArbitro;
    
    private List<pJogo> jogo;
    private ObservableList<pJogo> observableListJogo;
    
    private List<Equipe> listTimeCasa;
    private ObservableList<Equipe> observableListTC;
    
    private List<Equipe> listTimeFora;
    private ObservableList<Equipe> observableListTF;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboBoxTC();
        carregarComboBoxTF();
        carregarTableViewJogo();
        
        tabela.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewJogo(newValue));

    }
    
    public void carregarComboBoxTC() {
        Database database = DatabaseFactory.getDatabase("postgresql");
        Connection connection = database.conectar();
        EquipeDAO equipeDAO = new EquipeDAO();
        equipeDAO.setConnection(connection);
        listTimeCasa = equipeDAO.listar();
        observableListTC = FXCollections.observableArrayList(listTimeCasa);
        cbTimeCasa.setItems(observableListTC);
    }
    
    public void carregarComboBoxTF() {
        Database database = DatabaseFactory.getDatabase("postgresql");
        Connection connection = database.conectar();
        EquipeDAO equipeDAO = new EquipeDAO();
        equipeDAO.setConnection(connection);
        listTimeFora = equipeDAO.listar();
        observableListTF = FXCollections.observableArrayList(listTimeFora);
        cbTimeFora.setItems(observableListTF);
    }
    
    public void carregarTableViewJogo() {
        Database database = DatabaseFactory.getDatabase("postgresql");
        Connection connection = database.conectar();
        
        pJogoDAO pjogoDAO = new pJogoDAO();
        pjogoDAO.setConnection(connection);
        colunaTimeFora.setCellValueFactory(new PropertyValueFactory<>("timeFora"));
        colunaTimeCasa.setCellValueFactory(new PropertyValueFactory<>("timeCasa"));
        colunaGolFora.setCellValueFactory(new PropertyValueFactory<>("golFora"));
        colunaGolCasa.setCellValueFactory(new PropertyValueFactory<>("golCasa"));
        colunaArbitro.setCellValueFactory(new PropertyValueFactory<>("Arbitro"));

        jogo = pjogoDAO.listar();

        observableListJogo = FXCollections.observableArrayList(jogo);
        tabela.setItems(observableListJogo);
        
    }  
    
    public void selecionarItemTableViewJogo(pJogo jogo){
        if(jogo != null){
        cbTimeFora.setPromptText(jogo.getTimeFora());
        cbTimeCasa.setPromptText(jogo.getTimeCasa());
        textfieldGC.setText(jogo.getGolCasa());
        textfieldGF.setText(jogo.getGolFora());
        textfieldArbitro.setText(jogo.getArbitro());
        } else {
            
        cbTimeFora.setPromptText(jogo.getTimeFora());
        cbTimeCasa.setPromptText(jogo.getTimeCasa());
        textfieldGC.setText(jogo.getGolCasa());
        textfieldGF.setText(jogo.getGolFora());
        textfieldArbitro.setText(jogo.getArbitro());
            
        }
    }

    @FXML
    void alterar(ActionEvent event) throws SQLException {
        Database database = DatabaseFactory.getDatabase("postgresql");
        try (Connection connection = database.conectar()) {
            pJogo jogo = new pJogo();
            pJogo resultado = new pJogo();
            pJogoDAO pjogoDAO = new pJogoDAO();
            
            jogo.setArbitro(textfieldArbitro.getText());
            jogo.setGolCasa(textfieldGC.getText());
            jogo.setGolFora(textfieldGF.getText());
            jogo.setTimeCasa(cbTimeCasa.getValue().toString());
            jogo.setTimeFora(cbTimeFora.getValue().toString());
            
            pjogoDAO.setConnection(connection);
            resultado = pjogoDAO.buscar(jogo);
            pjogoDAO.alterar(resultado);
            tabela.refresh();
        }
    }

    @FXML
    void deletar(ActionEvent event) throws SQLException {
        Database database = DatabaseFactory.getDatabase("postgresql");
        try (Connection connection = database.conectar()) {
            pJogo jogo = new pJogo();
            pJogo resultado = new pJogo();
            pJogoDAO pjogoDAO = new pJogoDAO();
            
            jogo.setArbitro(textfieldArbitro.getText());
            jogo.setGolCasa(textfieldGC.getText());
            jogo.setGolFora(textfieldGF.getText());
            jogo.setTimeCasa(cbTimeCasa.getValue().toString());
            jogo.setTimeFora(cbTimeFora.getValue().toString());
            
            pjogoDAO.setConnection(connection);
            resultado = pjogoDAO.buscar(jogo);
            pjogoDAO.remover(resultado);
            tabela.refresh();
        }
    }

    @FXML
    void salvar(ActionEvent event) throws SQLException, IOException {
        Database database = DatabaseFactory.getDatabase("postgresql");
        try (Connection connection = database.conectar()) {
            pJogo jogo = new pJogo();
            pJogoDAO pjogoDAO = new pJogoDAO();
            
            jogo.setArbitro(textfieldArbitro.getText());
            jogo.setGolCasa(textfieldGC.getText());
            jogo.setGolFora(textfieldGF.getText());
            jogo.setTimeCasa(cbTimeCasa.getValue().toString());
            jogo.setTimeFora(cbTimeFora.getValue().toString());
            
            if(cbTimeCasa.getValue().toString() == null ? cbTimeFora.getValue().toString() == null : cbTimeCasa.getValue().toString().equals(cbTimeFora.getValue().toString())){
                Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro na Inserção");
		alert.setHeaderText("Erro:");
		alert.setContentText("Dois times iguais não podem competir. Por favor "
                        + "selecione times Diferentes");

		alert.showAndWait();
            }else{
                pjogoDAO.setConnection(connection);
                pjogoDAO.inserir(jogo);
                tabela.refresh();
            }
        }
    }

}
