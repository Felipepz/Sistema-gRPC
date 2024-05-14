/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import cadastrojogo.model.Arbitro;
import cadastrojogo.model.Cartao;
import cadastrojogo.model.Jogador;
import cadastrojogo.model.dao.ArbitroDAO;
import cadastrojogo.model.dao.CartaoDAO;
import cadastrojogo.model.dao.JogadorDAO;
import cadastrojogo.model.dao.pJogoDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import cadastrojogo.model.pJogo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.IntegerValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLProcessoCartaoInserirController implements Initializable {

    @FXML
    private JFXComboBox comboBoxArbitro;

    @FXML
    private JFXComboBox comboBoxJogador;
    
    @FXML
    private JFXComboBox comboBoxJogo;

    @FXML
    private JFXTextField textFieldCorCartao;

    @FXML
    private JFXTextField textFieldQuantidade;
    
    @FXML
    private JFXDatePicker datePickerData;
    

    @FXML
    private JFXButton buttonConfirmar;
    

    
    private List<Arbitro> listArbitro;
    private List<Jogador> listJogador;
    private List<pJogo> listJogo;
    private ObservableList<Arbitro> observableListArbitro;
    private ObservableList<Jogador> observableListJogador;
    private ObservableList<pJogo> observableListJogo;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    private final CartaoDAO cartaoDAO = new CartaoDAO();
    private final ArbitroDAO arbitroDAO = new ArbitroDAO();
    private final JogadorDAO jogadorDAO = new JogadorDAO();
    private final pJogoDAO jogoDAO = new pJogoDAO();
    
    private Cartao cartao;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cartaoDAO.setConnection(connection);
        arbitroDAO.setConnection(connection);
        jogadorDAO.setConnection(connection);
        jogoDAO.setConnection(connection);
        
        carregarComboBoxArbitro();
        carregarComboBoxJogador();
        carregarComboBoxJogo();
      
        //Declaração dos objetos de validações de dados
        RequiredFieldValidator validadorCampoObrigatorio = new RequiredFieldValidator();
        IntegerValidator validadorInteiro = new IntegerValidator();
        
        //Configuração dos objetos de validações de dados
        validadorCampoObrigatorio.setMessage("Campo deve ser preenchido");
        validadorInteiro.setMessage("Insira um inteiro");
        
        //Adição dos objetos de validações de dados nos componentes visuais
        textFieldQuantidade.getValidators().add(validadorInteiro);
        textFieldQuantidade.getValidators().add(validadorCampoObrigatorio);
        
        //Criação de Listeners para validação imediata após inserção de algum valor
        textFieldQuantidade.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal)textFieldQuantidade.validate();
        });
    }    
    
    public void carregarComboBoxArbitro() {
        listArbitro = arbitroDAO.listar();
        observableListArbitro = FXCollections.observableArrayList(listArbitro);
        comboBoxArbitro.setItems(observableListArbitro);
    }

    public void carregarComboBoxJogador() {
        listJogador = jogadorDAO.listar();
        observableListJogador = FXCollections.observableArrayList(listJogador);
        comboBoxJogador.setItems(observableListJogador);
    }
    
    public void carregarComboBoxJogo() {
        listJogo = jogoDAO.listar();
        observableListJogo = FXCollections.observableArrayList(listJogo);
        comboBoxJogo.setItems(observableListJogo);
    }
    
    public Cartao getCartao() {
        return this.cartao;
    }
    
    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
        
        
        comboBoxArbitro.getSelectionModel().select(cartao.getArbitro());
        comboBoxJogador.getSelectionModel().select(cartao.getNomeJogador());
        comboBoxJogo.getSelectionModel().select(cartao.getJogo());

    }
    


  
    
    @FXML
    public void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            this.cartao = new Cartao();
            cartao.setArbitro(((Arbitro)comboBoxArbitro.getSelectionModel().getSelectedItem()));
            cartao.setNomeJogador(((Jogador) comboBoxJogador.getSelectionModel().getSelectedItem()));
            cartao.setJogo(((pJogo) comboBoxJogo.getSelectionModel().getSelectedItem()));
            cartao.setCorCartao(textFieldCorCartao.getText());
            cartao.setQuantidade(Integer.parseInt(textFieldQuantidade.getText()));
            cartao.setData(datePickerData.getValue());
            //Regra de negocio
            if(verificarQuantidadeCartao(cartao)){
                if(verificaCorCartao(cartao)){
                    try{
                        connection.setAutoCommit(false);
                        cartaoDAO.setConnection(connection);
                        jogadorDAO.setConnection(connection);
                        cartaoDAO.inserir(cartao);
                        Jogador jogador = cartao.getNomeJogador();
                        jogador.setQuantidadeAtual(jogador.getQuantidadeAtual() + cartao.getQuantidade());
                        cartaoDAO.alterar(cartao);
                        connection.commit();
                        limparCampos();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Cartão Registrado");
                        alert.show();
                    }catch (SQLException ex){
                        try{
                            connection.rollback();
                        } catch (SQLException ex1) {
                            Logger.getLogger(FXMLProcessoCartaoAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        Logger.getLogger(FXMLProcessoCartaoAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } // missing closing brace here
            }
        }
    }

    
    
    public void limparCampos(){
        comboBoxArbitro.getSelectionModel().clearSelection();
        comboBoxJogador.getSelectionModel().clearSelection();
        comboBoxJogo.getSelectionModel().clearSelection();
        textFieldCorCartao.clear();
        textFieldQuantidade.clear();
        datePickerData.getEditor().clear();
        datePickerData.setValue(null);
    }
    
    
    
    // Regra de Negocio
    // Verifica a quantidade de Cartao
    public boolean verificarQuantidadeCartao(Cartao cartao) {
        int quantidadeCartao = cartao.getQuantidade();
        String corCartao = cartao.getCorCartao();

        if (corCartao.equals("amarelo") && quantidadeCartao > 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Não foi Possivel inserir o Cartão!");
            alert.show();
            return false; // há mais de 2 cartões amarelos
        }

        if (corCartao.equals("vermelho") && quantidadeCartao > 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Não foi Possivel inserir o Cartão!");
            alert.show();
            return false; // há mais de 1 cartão vermelho
        }

        return true; // a cor do cartão é vermelha ou amarela e as quantidades estão dentro do limite
    }
    
    // Verifica a cor do cartao
    public boolean verificaCorCartao(Cartao cartao) {
    String corCartao = cartao.getCorCartao();
    if (corCartao.equalsIgnoreCase("vermelho") || corCartao.equalsIgnoreCase("amarelo")) {
        return true;
    } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Cor do Cartão não é compativel!");
            alert.show();
        return false;
    }
    }
    
    
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (comboBoxArbitro.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Arbitro inválido!\n";
        }
        if (comboBoxJogador.getValue() == null) {
            errorMessage += "Arbitro Jogador!\n";
        }
        if (textFieldCorCartao.getText() == null || textFieldCorCartao.getText().length() == 0) {
            errorMessage += "Cor Invalida!\n";
        }
        if (textFieldQuantidade.getText() == null || textFieldQuantidade.getText().length() == 0) {
            errorMessage += "Quantidade Invalida!\n";
        }
       if (comboBoxJogo.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Jogo inválido!\n";
        }
       if (datePickerData.getValue() != null) {
        } else {
           errorMessage += "dataInvalida!\n";
        }

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

