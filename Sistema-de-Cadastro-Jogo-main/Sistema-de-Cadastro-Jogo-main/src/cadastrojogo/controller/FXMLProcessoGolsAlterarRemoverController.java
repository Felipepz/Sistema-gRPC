/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author edu64
 */
public class FXMLProcessoGolsAlterarRemoverController implements Initializable {

      @FXML
    private JFXComboBox<?> comboBoxJogador;

    @FXML
    private JFXComboBox<?> comboBoxJogo;

    @FXML
    private JFXComboBox<?> comboBoxEquipe;

    @FXML
    private JFXComboBox<?> comboBoxCamisaJogador;

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXListView<?> listViewGol;

    @FXML
    private JFXButton buttonRemover;
    
    @FXML
    private JFXTimePicker timePickerTempo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
