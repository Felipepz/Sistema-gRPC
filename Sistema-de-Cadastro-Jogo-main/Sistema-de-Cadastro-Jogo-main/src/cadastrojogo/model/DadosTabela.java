/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrojogo.model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DadosTabela extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Pode ser vazio ou incluir outros componentes na cena, se necessário
    }

    public static void exibirTabela(String timeFora, String timeCasa, String golFora, String golCasa, String arbitro) {
        launch(timeFora, timeCasa, golFora, golCasa, arbitro);
    }

    public void start(Stage primaryStage, String... params) {
        primaryStage.setTitle("Tabela de Futebol");

        TableView<Jogo> tabela = new TableView<>();

        TableColumn<Jogo, String> colunaTimeFora = new TableColumn<>("Time Fora");
        colunaTimeFora.setCellValueFactory(new PropertyValueFactory<>("timeFora"));

        TableColumn<Jogo, String> colunaTimeCasa = new TableColumn<>("Time Casa");
        colunaTimeCasa.setCellValueFactory(new PropertyValueFactory<>("timeCasa"));

        TableColumn<Jogo, String> colunaGolFora = new TableColumn<>("Gol Fora");
        colunaGolFora.setCellValueFactory(new PropertyValueFactory<>("golFora"));

        TableColumn<Jogo, String> colunaGolCasa = new TableColumn<>("Gol Casa");
        colunaGolCasa.setCellValueFactory(new PropertyValueFactory<>("golCasa"));

        TableColumn<Jogo, String> colunaArbitro = new TableColumn<>("Árbitro");
        colunaArbitro.setCellValueFactory(new PropertyValueFactory<>("arbitro"));

        tabela.getColumns().addAll(colunaTimeFora, colunaTimeCasa, colunaGolFora, colunaGolCasa, colunaArbitro);

        ObservableList<Jogo> dados = FXCollections.observableArrayList(
                new Jogo(params[0], params[1], params[2], params[3], params[4])
        );

        tabela.setItems(dados);

        Scene scene = new Scene(tabela);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class Jogo {
        private String timeFora;
        private String timeCasa;
        private String golFora;
        private String golCasa;
        private String arbitro;

        public Jogo(String timeFora, String timeCasa, String golFora, String golCasa, String arbitro) {
            this.timeFora = timeFora;
            this.timeCasa = timeCasa;
            this.golFora = golFora;
            this.golCasa = golCasa;
            this.arbitro = arbitro;
        }

        public String getTimeFora() {
            return timeFora;
        }

        public String getTimeCasa() {
            return timeCasa;
        }

        public String getGolFora() {
            return golFora;
        }

        public String getGolCasa() {
            return golCasa;
        }

        public String getArbitro() {
            return arbitro;
        }
    }
}

