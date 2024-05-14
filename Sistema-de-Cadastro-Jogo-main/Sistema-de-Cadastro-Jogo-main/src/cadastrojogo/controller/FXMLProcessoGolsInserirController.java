/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import cadastrojogo.model.Arbitro;
import cadastrojogo.model.Equipe;
import cadastrojogo.model.Jogador;
import cadastrojogo.model.dao.ArbitroDAO;
import cadastrojogo.model.dao.CartaoDAO;
import cadastrojogo.model.dao.JogadorDAO;
import cadastrojogo.model.dao.pJogoDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import cadastrojogo.model.pJogo;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import java.sql.Connection;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


public class FXMLProcessoGolsInserirController implements Initializable {

    @FXML
    private JFXComboBox<Jogador> comboBoxJogador;

    @FXML
    private JFXComboBox<Jogador> comboBoxCamisaJogador;

    @FXML
    private JFXComboBox<pJogo> comboBoxJogo;

    @FXML
    private JFXTimePicker timePickerTempo;

    @FXML
    private JFXComboBox<Equipe> comboBoxEquipe;
    
    @FXML
    private JFXButton buttonConfirmar;
    
    private List<Jogador> listJogador;
    private List<pJogo> listJogo;
    private List<Equipe> listEquipe;
    
    private ObservableList<Equipe> observableListEquipe;
    private ObservableList<Jogador> observableListJogador;
    private ObservableList<pJogo> observableListJogo;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    
    //private final EquipeDAO  equipeDAO = new GolsDAO();
    private final JogadorDAO jogadorDAO = new JogadorDAO();
    private final pJogoDAO jogoDAO = new pJogoDAO();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }    
    
}
