/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import cadastrojogo.model.Usuario;
import cadastrojogo.model.dao.UsuarioDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLCadastroLoginController implements Initializable {

    @FXML
    private JFXButton buttonCadastroLogin;
       
    @FXML
    private  JFXTextField textFieldEmail;  
    
    @FXML
    private  JFXTextField textFieldNomeCompleto; 
    
    @FXML
    private  JFXTextField textFieldNomeUsuario; 
    
    @FXML
    private  JFXPasswordField textFieldSenha; 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void handleCadastrar(ActionEvent event) throws IOException {    
        
            Database database = DatabaseFactory.getDatabase("postgresql");
            Connection connection = database.conectar();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setConnection(connection);
            
            Usuario usuario = new Usuario();
            usuario.setEmail(textFieldEmail.getText());
            usuario.setNomeUsuario(textFieldNomeUsuario.getText());
            usuario.setNomeCompleto(textFieldNomeCompleto.getText());
            usuario.setSenha(textFieldSenha.getText());
            
            usuarioDAO.inserir(usuario);
            
            buttonCadastroLogin.getScene().getWindow().hide();
        
    } 
    

    
}
