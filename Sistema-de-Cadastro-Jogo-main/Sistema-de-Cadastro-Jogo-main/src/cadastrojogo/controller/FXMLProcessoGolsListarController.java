/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author edu64
 */
public class FXMLProcessoGolsListarController implements Initializable {

    @FXML
    private TableColumn<?, ?> tableColumnIDGol;
    
    @FXML
    private TableView<?> tableViewGols;

    @FXML
    private TableColumn<?, ?> tableColumnJogador;

    @FXML
    private TableColumn<?, ?> tableColumnJogo;

    @FXML
    private TableColumn<?, ?> tableColumnCamisaJogador;

    @FXML
    private TableColumn<?, ?> tableColumnTempo;

    @FXML
    private TableColumn<?, ?> tableColumnEquipe;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
