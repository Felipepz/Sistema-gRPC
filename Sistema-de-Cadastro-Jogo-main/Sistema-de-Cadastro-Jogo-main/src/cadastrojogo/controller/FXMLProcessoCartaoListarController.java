/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import cadastrojogo.model.Arbitro;
import cadastrojogo.model.Cartao;
import cadastrojogo.model.Jogador;
import cadastrojogo.model.dao.CartaoDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import cadastrojogo.model.pJogo;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLProcessoCartaoListarController implements Initializable {

    @FXML
    private TableView<Cartao> tableViewCartoes;

    @FXML
    private TableColumn<Cartao, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Cartao, Arbitro> tableColumnArbitro;

    @FXML
    private TableColumn<Cartao, Jogador> tableColumnJogador;
    
    @FXML
    private TableColumn<Cartao, pJogo> tableColumnJogo; 
    
    @FXML
    private TableColumn<Cartao, LocalDate> tableColumnData;

    @FXML
    private TableColumn<Cartao, String> tableColumnCorCartao;

    @FXML
    private TableColumn<Cartao, String> tableColumnQuantidade;

    
    private List<Cartao> listCartao;
    private ObservableList<Cartao> observableListCartao;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final CartaoDAO cartaoDAO = new CartaoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cartaoDAO.setConnection(connection);
        carregarTableView();
    }    
    
    public void carregarTableView(){
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("cdCartao"));
        tableColumnArbitro.setCellValueFactory(new PropertyValueFactory<>("arbitro"));
        tableColumnJogador.setCellValueFactory(new PropertyValueFactory<>("nomeJogador"));
        tableColumnJogo.setCellValueFactory(new PropertyValueFactory<>("jogo"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnCorCartao.setCellValueFactory(new PropertyValueFactory<>("corCartao"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
 
        
        listCartao = cartaoDAO.listar();
        observableListCartao = FXCollections.observableArrayList(listCartao);
        tableViewCartoes.setItems(observableListCartao);
    }
    
}
