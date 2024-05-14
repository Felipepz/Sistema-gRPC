/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLTelaInicialController implements Initializable {

    @FXML
    private MenuItem menuItemCadastroArbitro;
    
    @FXML
    private MenuItem menuItemCadastroEquipe;
    
    @FXML
    private MenuItem menuItemCadastroJogadores;
    
    @FXML
    private MenuItem menuItemGraficosJogos;
    
    @FXML
    private MenuItem menuItemProcessosCartoes;
    
    @FXML
    private MenuItem menuItemGraficosCartoes;
    
    @FXML
    private MenuItem menuItemProcessosJogo;
    
    @FXML
    private MenuItem menuItemRelatoriosCartoes;
    
    @FXML
    private MenuItem menuItemRelatoriosJogos;
    
    @FXML
    private MenuItem menuItemIntegrantes;
    
    @FXML
    private MenuItem menuItemGrpc;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private JFXButton ButtonCadastroArbitro;

    @FXML
    private JFXButton ButtonCadastroEquipe;
   
    @FXML
    private JFXButton ButtonProcessoCartao;
    
    @FXML
    private JFXButton ButtonGraficoJogos;
    
    @FXML
    private JFXButton ButtonGraficoCartao;
    
    @FXML
    private JFXButton ButtonRelatorioCartao;
    
    @FXML
    private JFXButton ButtonRelatorioJogos;
    
    @FXML
    private JFXButton ButtonCadastroJogador;
    
    @FXML
    private JFXButton ButtonProcessoJogo;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
   @FXML  
   public void handleMenuItemCadastroEquipe() throws IOException {
   
   AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLCadastroEquipe.fxml"));
   anchorPane.getChildren().setAll(a);
   
   
   }
  
    
    @FXML
    public void handleMenuItemCadastroArbitro() throws IOException {
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cadastrojogo/view/FXMLCadastroArbitro.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stageCadastroArbitro = new Stage();     
        stageCadastroArbitro.setScene(scene);
        stageCadastroArbitro.show();*/
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLCadastroArbitro.fxml"));
        anchorPane.getChildren().setAll(a);
        
        
    }
    
    @FXML
    void handleMenuItemCadastroJogador() throws IOException {

        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/cadastroJogador.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    
    
    @FXML
    public void handleMenuItemProcessosCartoes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLProcessoCartao.fxml"));
        anchorPane.getChildren().setAll(a);
    } 
    
    @FXML
    public void handleMenuItemGraficoCartoes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLGraficoCartao.fxml"));
        anchorPane.getChildren().setAll(a);
    } 
    
    @FXML
    public void handleMenuItemRelatorioCartoes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLRelatorioCartoes.fxml"));
        anchorPane.getChildren().setAll(a);
    } 
    
    @FXML
    void handleMenuItemProcessosJogo(ActionEvent event) throws IOException {

        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/ProcessoJogo.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    void handleMenuItemGraficoJogos(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLGraficoJogo.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    void handleMenuItemRelatorioJogos(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLRelatorioJogo.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    void handleMenuItemIntegrantes(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLSobreIntegrantes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    void handleMenuItemGrpc(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/cadastrojogo/view/FXMLInformacoesGrpc.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    
}
