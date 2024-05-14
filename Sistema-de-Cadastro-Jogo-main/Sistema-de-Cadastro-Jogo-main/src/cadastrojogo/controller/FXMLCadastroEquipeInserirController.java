/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

//import cadastrojogo.model.Jogador;
import cadastrojogo.model.Equipe;
import cadastrojogo.model.Jogador;
import cadastrojogo.model.dao.EquipeDAO;
import cadastrojogo.model.dao.JogadorDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author edu64
 */
public class FXMLCadastroEquipeInserirController implements Initializable {

    @FXML
    private JFXDatePicker datePickerCadastroEquipe;
    @FXML
    private JFXTextField textFieldNomeCadastroEquipe;

    @FXML
    private JFXTextField textFieldLigaCadastroEquipe;

    @FXML
    private JFXTextField textFieldPaisCadastroEquipe;

    @FXML
    private JFXTextField textFieldAnoFundacaoCadastroEquipe;

    @FXML
    private JFXTextField textFieldTecnicoCadastroEquipe;

    @FXML
    private JFXComboBox<Jogador> comboBoxJogadoresCadastroEquipe;

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXButton buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Equipe equipe;

    private List<Jogador> listJogador;
    private ObservableList<Jogador> observableListJogador;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final JogadorDAO jogadorDAO = new JogadorDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboBoxJogador();

    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void carregarComboBoxJogador() {
        jogadorDAO.setConnection(connection);
        listJogador = jogadorDAO.listar();
        observableListJogador = FXCollections.observableArrayList(listJogador);
        comboBoxJogadoresCadastroEquipe.setItems(observableListJogador);
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
        this.textFieldNomeCadastroEquipe.setText(equipe.getnome());
        this.textFieldLigaCadastroEquipe.setText(equipe.getLiga());
        this.textFieldPaisCadastroEquipe.setText(equipe.getPais());
        this.textFieldTecnicoCadastroEquipe.setText(equipe.getTecnico());
        this.datePickerCadastroEquipe.setValue(equipe.getanofundacao());
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {
            equipe.setnome(textFieldNomeCadastroEquipe.getText());
            //equipe.setNome(textFieldNomeCadastroEquipe.getText());
            equipe.setLiga(textFieldLigaCadastroEquipe.getText());
            equipe.setPais(textFieldPaisCadastroEquipe.getText());
            equipe.setTecnico(textFieldTecnicoCadastroEquipe.getText());
            equipe.setanofundacao(datePickerCadastroEquipe.getValue());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    public void handleButtonCancelar() {
        dialogStage.close();
    }

    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldNomeCadastroEquipe.getText() == null || textFieldNomeCadastroEquipe.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (textFieldLigaCadastroEquipe.getText() == null || textFieldLigaCadastroEquipe.getText().length() == 0) {
            errorMessage += "Liga inválida!\n";
        }
        if (textFieldPaisCadastroEquipe.getText() == null || textFieldPaisCadastroEquipe.getText().length() == 0) {
            errorMessage += "Pais inválido!\n";
        }
        if (textFieldTecnicoCadastroEquipe.getText() == null || textFieldTecnicoCadastroEquipe.getText().length() == 0) {
            errorMessage += "Tecnico inválido!\n";
        }

        // Implementar a validação do Date Picker
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

}
