/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sockets.thread.SocketCliente;
import sockets.thread.ThreadCliente;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLSobreIntegrantesController implements Initializable {

    @FXML
    private Label labelIntegrantes;
    
    List<String> equipe = new ArrayList();
    

    public static Label vetLabelIntegrantes[] = new Label[3];
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       SocketCliente cliente = new SocketCliente();
       equipe = cliente.conectar();
      
        ThreadCliente runableCliente = new ThreadCliente(equipe,labelIntegrantes);
        Thread t1 = new Thread(runableCliente);
        t1.start();
        

    }    

    
    
    
    
}

