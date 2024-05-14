/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cadastrojogo.controller;

import cadastrojogo.model.Arbitro;
import cadastrojogo.model.dao.ArbitroDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Felipe
 */

public class FXMLCadastroArbitroController implements Initializable {
      
    @FXML
    private JFXButton  buttonInserir;
    
    @FXML
    private JFXButton  buttonAlterar;
    
    @FXML
    private JFXButton  buttonRemover;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Label labelArbitroCPF;

    @FXML
    private Label labelArbitroCodigo;

    @FXML
    private Label labelArbitroDataNascimento;

    @FXML
    private Label labelArbitroFormacao;

    @FXML
    private Label labelArbitroNacionalidade;

    @FXML
    private Label labelArbitroNome;

    @FXML
    private TableColumn<Arbitro, String> tableColumnArbitroCPF;

    @FXML
    private TableColumn<Arbitro, String> tableColumnArbitroNome;

    @FXML
    private TableView<Arbitro> tableViewArbitro;
    
    private List<Arbitro> listArbitro;
    private ObservableList<Arbitro> observableListArbitro;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ArbitroDAO arbitroDAO = new ArbitroDAO(); 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        arbitroDAO.setConnection(connection);
        carregarTableViewArbitro();
        
        tableViewArbitro.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewArbitro(newValue));
        
        
    }  
    
    public void carregarTableViewArbitro() {
        tableColumnArbitroNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnArbitroCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listArbitro = arbitroDAO.listar();

        observableListArbitro = FXCollections.observableArrayList(listArbitro);
        tableViewArbitro.setItems(observableListArbitro);
        
    }  
    
    public void selecionarItemTableViewArbitro(Arbitro arbitro){
        if(arbitro != null){
        labelArbitroCodigo.setText(String.valueOf(arbitro.getCdArbitro()));
        labelArbitroNome.setText(arbitro.getNome());
        labelArbitroCPF.setText(arbitro.getCpf());
        labelArbitroFormacao.setText(arbitro.getFormacao());
        labelArbitroNacionalidade.setText(arbitro.getNacionalidade());
        labelArbitroDataNascimento.setText(String.valueOf(arbitro.getDataDeNascimento()));
        } else {
            
        labelArbitroCodigo.setText("");
        labelArbitroNome.setText("");
        labelArbitroCPF.setText("");
        labelArbitroFormacao.setText("");
        labelArbitroNacionalidade.setText("");
        labelArbitroDataNascimento.setText("");
            
        }
    }
    
    
 
    
    @FXML
    public void handleCadastrosArbitroInserir() throws IOException {
        Arbitro arbitro = new Arbitro();
        boolean buttonConfirmarClicked = showFXMLCadastroArbitroInserir(arbitro);
        if (buttonConfirmarClicked) {
            arbitroDAO.inserir(arbitro);
            carregarTableViewArbitro();
        }
        
    } 
    
    @FXML
    public void handleButtonAlterar() throws IOException {
        Arbitro arbitro = tableViewArbitro.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
        if (arbitro != null) {
            boolean buttonConfirmarClicked = showFXMLCadastroArbitroInserir(arbitro);
            if (buttonConfirmarClicked) {
                arbitroDAO.alterar(arbitro);
                carregarTableViewArbitro();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um arbitro na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Arbitro arbitro = tableViewArbitro.getSelectionModel().getSelectedItem();
        if (arbitro != null) {
            arbitroDAO.remover(arbitro);
            carregarTableViewArbitro();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }
    
    
    private boolean showFXMLCadastroArbitroInserir(Arbitro arbitro ) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroArbitroInserirController.class.getResource("/cadastrojogo/view/FXMLCadastroArbitroInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro do Arbitro");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLCadastroArbitroInserirController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setArbitro(arbitro);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
        
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cadastrojogo/view/FXMLCadastroArbitroInserir.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stageArbitroInserir = new Stage();
        stageArbitroInserir.setScene(scene);
        stageArbitroInserir.setWidth(630); 
        stageArbitroInserir.setHeight(450); 
        stageArbitroInserir.show();*/
    }
     

    
}


