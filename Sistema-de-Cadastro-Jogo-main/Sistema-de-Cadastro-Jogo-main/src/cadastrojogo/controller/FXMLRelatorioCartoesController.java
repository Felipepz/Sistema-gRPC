/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.controller;

import cadastrojogo.model.Cartao;
import cadastrojogo.model.Jogador;
import cadastrojogo.model.dao.CartaoDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLRelatorioCartoesController implements Initializable {

    @FXML
    private TableView<Cartao> tableViewCartoes;

    @FXML
    private TableColumn<Cartao, Jogador> tableColumnJogador;  

    //String
    @FXML
    private TableColumn<Cartao, String> tableColumnQuantidadeCartoes;
    
    @FXML
    private JFXButton  buttonImprimir;

    
    private List<Cartao> listCartao;
    private ObservableList<Cartao> observableListCartao;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final CartaoDAO cartaoDAO = new CartaoDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cartaoDAO.setConnection(connection);
        carregarTableView();
    }    
    
    public void carregarTableView(){
        tableColumnJogador.setCellValueFactory(new PropertyValueFactory<>("nomeJogador"));
        tableColumnQuantidadeCartoes.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
 
        
        listCartao = cartaoDAO.listar();
        observableListCartao = FXCollections.observableArrayList(listCartao);
        tableViewCartoes.setItems(observableListCartao);
    }
    
    
    public void handleImprimir() throws JRException{
        
        URL url = getClass().getResource("/cadastrojogo/relatorios/CadastroJogoRelatorioCartoes.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);//null: caso não existam filtros
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        jasperViewer.setVisible(true);
    }
    
    
}
