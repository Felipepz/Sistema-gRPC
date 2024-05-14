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
import com.jfoenix.controls.JFXListView;
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
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLProcessoCartaoAlterarRemoverController implements Initializable {

    @FXML
    private JFXComboBox<Arbitro> comboBoxArbitro;

    @FXML
    private JFXComboBox<Jogador> comboBoxJogador;
    
    @FXML
    private JFXComboBox<pJogo> comboBoxJogo; 
    
    @FXML
    private JFXTextField textFieldCorCartao;

    @FXML
    private JFXTextField textFieldQuantidade;
    
    @FXML
    private JFXDatePicker datePickerData;
       
    @FXML
    private JFXButton buttonRemover;
    
    @FXML
    private JFXButton buttonAlterar;
    
    @FXML
    private JFXListView<Cartao> listViewCartoes;
    
    private List<Arbitro> listArbitro;
    private List<Jogador> listJogador;
    private List<Cartao> listCartao;
    private List<pJogo> listJogo;
    
    private ObservableList<Arbitro> observableListArbitro;
    private ObservableList<Jogador> observableListJogador;
    private ObservableList<Cartao> observableListCartao;
    private ObservableList<pJogo> observableListJogo;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    private final CartaoDAO cartaoDAO = new CartaoDAO();
    private final ArbitroDAO arbitroDAO = new ArbitroDAO();
    private final JogadorDAO jogadorDAO = new JogadorDAO();
    private final pJogoDAO jogoDAO = new pJogoDAO();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        arbitroDAO.setConnection(connection);
        jogadorDAO.setConnection(connection);
        cartaoDAO.setConnection(connection);
        jogoDAO.setConnection(connection);

        carregarComboBoxArbitro();
        carregarComboBoxJogador();
        carregarComboBoxJogo();
        carregarListView();
        
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

        listViewCartoes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemListView(newValue));
    }    
    
    public void carregarListView(){
        listCartao = cartaoDAO.listar();
        observableListCartao = FXCollections.observableArrayList(listCartao);
        listViewCartoes.setItems(observableListCartao);
    }
    
    public void selecionarItemListView(Cartao cartao){
        if(cartao != null){
            comboBoxArbitro.getSelectionModel().select(cartao.getArbitro());            
            comboBoxJogador.getSelectionModel().select(cartao.getNomeJogador());
            comboBoxJogo.getSelectionModel().select(cartao.getJogo());
            datePickerData.setValue(cartao.getData());
            textFieldCorCartao.setText(String.valueOf(cartao.getCorCartao()));
            textFieldQuantidade.setText(String.valueOf(cartao.getQuantidade()));
        }else{
            limparCampos();
        }
    }
    
    public void carregarComboBoxArbitro(){
        comboBoxArbitro.setEditable(true);
        comboBoxArbitro.getEditor().setEditable(false);
        comboBoxArbitro.setConverter(new StringConverter<Arbitro>() {
            @Override
            public String toString(Arbitro object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public Arbitro fromString(String string) {
                return comboBoxArbitro.getSelectionModel().getSelectedItem();
            }
        });
                
        listArbitro = arbitroDAO.listar();
        observableListArbitro = FXCollections.observableArrayList(listArbitro);
        comboBoxArbitro.setItems(observableListArbitro);
    }
    
    public void carregarComboBoxJogador(){
        comboBoxJogador.setEditable(true);
        comboBoxJogador.getEditor().setEditable(false);
        comboBoxJogador.setConverter(new StringConverter<Jogador>() {
            @Override
            public String toString(Jogador object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public Jogador fromString(String string) {
                return comboBoxJogador.getSelectionModel().getSelectedItem();
            }
        });
                
        listJogador = jogadorDAO.listar();
        observableListJogador = FXCollections.observableArrayList(listJogador);
        comboBoxJogador.setItems(observableListJogador);
    }
    
    public void carregarComboBoxJogo(){
        comboBoxJogo.setEditable(true);
        comboBoxJogo.getEditor().setEditable(false);
        comboBoxJogo.setConverter(new StringConverter<pJogo>() {
            @Override
            public String toString(pJogo object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public pJogo fromString(String string) {
                return comboBoxJogo.getSelectionModel().getSelectedItem();
            }
        });
                
        listJogo = jogoDAO.listar();
        observableListJogo = FXCollections.observableArrayList(listJogo);
        comboBoxJogo.setItems(observableListJogo);
    } 
    
    
    @FXML
    public void handleButtonAlterar(){
        if(listViewCartoes.getSelectionModel().getSelectedItem() != null){
            if(validarEntradaDeDados()){
                Cartao cartao = listViewCartoes.getSelectionModel().getSelectedItem();
                try{
                    connection.setAutoCommit(false);
                    cartaoDAO.setConnection(connection);
                    jogadorDAO.setConnection(connection);
                    Jogador jogador = cartao.getNomeJogador();
          
                    cartao.setQuantidade(0);
                    cartaoDAO.alterar(cartao);
                    jogadorDAO.alterarNovo(jogador);
                    
                    cartao.setArbitro((Arbitro)comboBoxArbitro.getSelectionModel().getSelectedItem());
                    cartao.setNomeJogador((Jogador)comboBoxJogador.getSelectionModel().getSelectedItem());
                    cartao.setJogo((pJogo)comboBoxJogo.getSelectionModel().getSelectedItem()); 
                    cartao.setData(datePickerData.getValue());
                    cartao.setQuantidade(Integer.parseInt(textFieldQuantidade.getText()));
                    cartao.setCorCartao(textFieldCorCartao.getText());
                    
                    if(verificarQuantidadeCartao(cartao)){
                    //Regra de Negocio
                        if(verificaCorCartao(cartao)){
                            cartaoDAO.alterar(cartao);
                            jogador = cartao.getNomeJogador();
                            jogador.setQuantidadeAtual(jogador.getQuantidadeAtual() + cartao.getQuantidade());
                            jogadorDAO.alterarNovo(jogador);
                            connection.commit();
                            carregarListView();
                            limparCampos();
                            listViewCartoes.getSelectionModel().clearSelection();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("Alteração registrada com sucesso");
                            alert.show();                            
                        }else{
                            connection.rollback();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Não foi possivel alterar");
                            alert.show();
                        }
                    }else{
                        connection.rollback();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Não foi possivel alterar");
                        alert.show();
                    }
                }catch(SQLException ex){
                    try{
                        connection.rollback();
                    }catch (SQLException ex1) {
                        Logger.getLogger(FXMLProcessoCartaoAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Logger.getLogger(FXMLProcessoCartaoAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Os dados estão incorretos, vazios ou possuem valores negativos, favor revisar!");
                alert.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Escolha um Jogador na tabela ao lado!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonRemover(){
        if(listViewCartoes.getSelectionModel().getSelectedItem() != null){
            Cartao cartao = listViewCartoes.getSelectionModel().getSelectedItem();
            
            try{
                connection.setAutoCommit(false);
                Jogador jogador = cartao.getNomeJogador();
                
                jogadorDAO.alterarNovo(jogador);
                cartaoDAO.remover(cartao);
                connection.commit();
                
                listViewCartoes.getSelectionModel().clearSelection();
                limparCampos();
                carregarListView();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Cartao deletado com sucesso!");
                alert.show(); 
            }catch (SQLException ex){
                try{
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(FXMLProcessoCartaoAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(FXMLProcessoCartaoAlterarRemoverController.class.getName()).log(Level.SEVERE, null, ex);   
            }
        }else{
            
        }
    }
    
    
    
    public void limparCampos(){
        comboBoxArbitro.getSelectionModel().clearSelection();
        comboBoxJogador.getSelectionModel().clearSelection();
        comboBoxJogo.getSelectionModel().clearSelection();
        datePickerData.getEditor().clear();
        datePickerData.setValue(null);
        textFieldCorCartao.clear();
        textFieldQuantidade.clear();
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
