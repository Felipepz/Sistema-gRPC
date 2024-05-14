/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author edu64
 */
public class FXMLProcessoGolsController implements Initializable {

    
    @FXML
    private JFXTabPane tabPaneGols;

    @FXML
    private Tab tabInserir;

    @FXML
    private Tab tabIAlterarRemover;

    @FXML
    private Tab tabIListar;

    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
