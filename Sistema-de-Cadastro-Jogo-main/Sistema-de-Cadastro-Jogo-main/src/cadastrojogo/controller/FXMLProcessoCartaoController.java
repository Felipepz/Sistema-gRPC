/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLProcessoCartaoController implements Initializable {
    

    
    @FXML
    private JFXTabPane tabPaneCartoes;

    @FXML
    private Tab tabInserir;

    @FXML
    private Tab tabAlterarRemover;
    
    @FXML
    private Tab tabListar;

    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            handleProcessoCartaoInserir();
        } catch (IOException ex) {
            Logger.getLogger(FXMLProcessoCartaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            tabPaneCartoes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() { 
            @Override 
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                if(newTab.equals(tabInserir)) {            
                    try {
                        handleProcessoCartaoInserir();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLTelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(newTab.equals(tabAlterarRemover)){
                    try {
                        handleProcessoCartaoAlterarRemover();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLProcessoCartaoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(newTab.equals(tabListar)){
                    try {
                        handleProcessoCartaoListar();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLProcessoCartaoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }    
    
    public void handleProcessoCartaoInserir() throws IOException {
      AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLProcessoCartaoInserir.fxml"));
      anchorPane.getChildren().setAll(a);
    }
    
    public void handleProcessoCartaoAlterarRemover() throws IOException {
      AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLProcessoCartaoAlterarRemover.fxml"));
      anchorPane.getChildren().setAll(a);
    }
    
    public void handleProcessoCartaoListar() throws IOException {
      AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLProcessoCartaoListar.fxml"));
      anchorPane.getChildren().setAll(a);
    }
    
    
}
