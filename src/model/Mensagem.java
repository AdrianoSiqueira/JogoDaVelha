package model;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Mensagem extends Application {
    private Alert alert;

    public Mensagem(Alert.AlertType tipo, String titulo, String cabecalho, String conteudo) {
        alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.getDialogPane().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }

    @Override
    public void start(Stage stage) {
    }

    public void show() {
        alert.showAndWait();
    }
}