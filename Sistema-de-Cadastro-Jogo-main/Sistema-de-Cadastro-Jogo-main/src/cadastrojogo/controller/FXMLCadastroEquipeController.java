package cadastrojogo.controller;


import cadastrojogo.model.Equipe;
import cadastrojogo.model.Jogador;
import cadastrojogo.model.dao.EquipeDAO;
import cadastrojogo.model.dao.JogadorDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLCadastroEquipeController implements Initializable {

    @FXML
    private TableView<Equipe> tableViewCadastroEquipe;

    @FXML
    private TableColumn<Equipe, String> tableColumnCadastroEquipeNome;

    @FXML
    private TableColumn<Equipe, String> tableColumnCadastroEquipeID;

    
    @FXML
    private Label labelJogadores;
    
    @FXML
    private Label labelIDEquipe;

    @FXML
    private Label labelEquipePais;

    @FXML
    private Label labelEquipeLiga;

    @FXML
    private Label labelEquipeTecnico;

    @FXML
    private Label labelEquipeAnoFundacao;

    @FXML
    private JFXComboBox<Jogador> comboBoxJogadoresCadastroEquipe;

    @FXML
    private JFXButton buttonInserir;

    @FXML
    private JFXButton buttonAlterar;

    @FXML
    private JFXButton buttonRemover;

    @FXML
    private List<Equipe> listEquipe;
    private List<Jogador> listJogador;
    private ObservableList<Equipe> observableListEquipe;
     private ObservableList<Jogador> observableListJogador;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final EquipeDAO equipeDAO = new EquipeDAO();
    private final JogadorDAO jogadorDAO = new JogadorDAO(); 
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        equipeDAO.setConnection(connection);
        carregarTableViewEquipes();
        carregarComboBoxJogador();
        
        tableViewCadastroEquipe.getSelectionModel().selectedItemProperty().addListener(
               (observable, oldValue, newValue) -> selecionarItemTableViewEquipe(newValue));

    }

    public void carregarTableViewEquipes() {
        
        tableColumnCadastroEquipeNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCadastroEquipeID.setCellValueFactory(new PropertyValueFactory<>("idCadastroEquipe"));

        
        listEquipe = equipeDAO.listar();
        
       observableListEquipe = FXCollections.observableArrayList(listEquipe);
       tableViewCadastroEquipe.setItems(observableListEquipe);

    } 
    public void carregarComboBoxJogador() {
        jogadorDAO.setConnection(connection);
        listJogador = jogadorDAO.listar();
        observableListJogador = FXCollections.observableArrayList(listJogador);
        comboBoxJogadoresCadastroEquipe.setItems(observableListJogador);
    }
    
    
    
     public void selecionarItemTableViewEquipe(Equipe equipe) {
        if (equipe != null) {
            labelIDEquipe.setText(String.valueOf(equipe.getidCadastroEquipe()));
            //labelJogadores.setText(Jogador.getNome());
            labelEquipePais.setText(equipe.getPais());
            labelEquipeLiga.setText(equipe.getLiga());
            labelEquipeTecnico.setText(equipe.getTecnico());
            labelEquipeAnoFundacao.setText(String.valueOf(equipe.getanofundacao()));
        } else {
            labelIDEquipe.setText("");
            labelEquipePais.setText("");
            labelEquipeLiga.setText("");
            labelEquipeTecnico.setText("");
            labelEquipeAnoFundacao.setText("");
            
        } 
    }

    @FXML
    public void handleCadastrosEquipeInserir() throws IOException {
        Equipe equipe = new Equipe();
        boolean buttonConfirmarClicked = showFXMLCadastroEquipeInserir(equipe);
        if (buttonConfirmarClicked) {
            equipeDAO.inserir(equipe);
            carregarTableViewEquipes();
        }

    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Equipe equipe = tableViewCadastroEquipe.getSelectionModel().getSelectedItem();
        if (equipe != null) {
            boolean buttonConfirmarClicked = showFXMLCadastroEquipeInserir(equipe);
            if (buttonConfirmarClicked) {
                equipeDAO.alterar(equipe);
                carregarTableViewEquipes();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma equipe na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Equipe equipe = tableViewCadastroEquipe.getSelectionModel().getSelectedItem();
        if (equipe != null) {
            equipeDAO.remover(equipe);
            carregarTableViewEquipes();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma equipe na Tabela!");
            alert.show();
        }
    }

    private boolean showFXMLCadastroEquipeInserir(Equipe equipe) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroEquipeInserirController.class.getResource("/cadastrojogo/view/FXMLCadastroEquipeInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Equipe");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLCadastroEquipeInserirController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEquipe(equipe);
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
    }

}
