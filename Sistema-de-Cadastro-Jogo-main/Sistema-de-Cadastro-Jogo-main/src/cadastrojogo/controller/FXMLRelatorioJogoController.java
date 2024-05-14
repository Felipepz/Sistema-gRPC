package cadastrojogo.controller;

import cadastrojogo.model.Equipe;
import cadastrojogo.model.dao.pJogoDAO;
import cadastrojogo.model.database.Database;
import cadastrojogo.model.database.DatabaseFactory;
import cadastrojogo.model.pJogo;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class FXMLRelatorioJogoController implements Initializable {

    @FXML
    private JFXButton btnImprimir;

    @FXML
    private TableView<pJogo> tabela;

    @FXML
    private TableColumn<pJogo, String> colunaTimeFora;

    @FXML
    private TableColumn<pJogo, String> colunaTimeCasa;

    @FXML
    private TableColumn<pJogo, String> colunaGolFora;

    @FXML
    private TableColumn<pJogo, String> colunaGolCasa;

    @FXML
    private TableColumn<pJogo, String> colunaArbitro;
    
    private List<pJogo> jogo;
    private ObservableList<pJogo> observableListJogo;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewJogo();
    }
    
    @FXML
    void Imprimir(ActionEvent event) throws JRException {
        URL url = getClass().getResource("/cadastrojogo/relatorios/RelatorioJogo.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);//null: caso não existam filtros
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        jasperViewer.setVisible(true);
    }
    
    public void carregarTableViewJogo() {
        pJogoDAO pjogoDAO = new pJogoDAO();
        pjogoDAO.setConnection(connection);
        colunaTimeFora.setCellValueFactory(new PropertyValueFactory<>("timeFora"));
        colunaTimeCasa.setCellValueFactory(new PropertyValueFactory<>("timeCasa"));
        colunaGolFora.setCellValueFactory(new PropertyValueFactory<>("golFora"));
        colunaGolCasa.setCellValueFactory(new PropertyValueFactory<>("golCasa"));
        colunaArbitro.setCellValueFactory(new PropertyValueFactory<>("Arbitro"));

        jogo = pjogoDAO.listar();

        observableListJogo = FXCollections.observableArrayList(jogo);
        tabela.setItems(observableListJogo);
        
    }  

}

