package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Jogador;
import model.Mensagem;

public class CadastroUI extends Application {

    private Jogador[] jogadores;
    private Stage stage;

    public CadastroUI(Jogador[] jogadores) {
        this.jogadores = jogadores;
    }

    private void cadastrar(String nome1, String nome2) {
        if (!nome1.isEmpty()) {
            if (!nome2.isEmpty()) {
                if (!nome1.equals(nome2)) {
                    jogadores[0].setNome(nome1);
                    jogadores[1].setNome(nome2);
                    stage.close();
                } else new Mensagem(Alert.AlertType.WARNING, "Cadastro", "Alerta", "Os nomes devem ser diferentes.").show();
            } else new Mensagem(Alert.AlertType.WARNING, "Cadastro", "Alerta", "Informe o nome do segundo jogador.").show();
        } else new Mensagem(Alert.AlertType.WARNING, "Cadastro", "Alerta", "Informe o nome do primeiro jogador.").show();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        Label labelTitulo = new Label("Cadastro de Jogadores"),
                labelNome1 = new Label("Nome do primeiro jogador:"),
                labelNome2 = new Label("Nome do segundo jogador:");

        labelTitulo.getStyleClass().add("label-titulo");

        TextField fieldNome1 = new TextField(),
                fieldNome2 = new TextField();

        fieldNome1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 20) fieldNome1.setText(oldValue);
        });
        fieldNome1.setOnAction(event -> cadastrar(fieldNome1.getText(), fieldNome2.getText()));

        fieldNome2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 20) fieldNome2.setText(oldValue);
        });
        fieldNome2.setOnAction(event -> cadastrar(fieldNome1.getText(), fieldNome2.getText()));

        Button buttonCadastro = new Button("Cadastrar");
        buttonCadastro.setOnAction(event -> cadastrar(fieldNome1.getText(), fieldNome2.getText()));

        GridPane.setConstraints(labelTitulo, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(labelNome1, 0, 3, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(fieldNome1, 0, 4, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(labelNome2, 0, 5, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(fieldNome2, 0, 6, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(buttonCadastro, 0, 8, 1, 1, HPos.RIGHT, VPos.CENTER);

        GridPane paneFundo = new GridPane();
        paneFundo.getStyleClass().add("pane-fundo");
        paneFundo.getChildren().addAll(labelTitulo, labelNome1, labelNome2, fieldNome1, fieldNome2, buttonCadastro);

        Scene scene = new Scene(paneFundo);
        scene.getRoot().getStylesheets().clear();
        scene.getRoot().getStylesheets().addAll("/css/cadastroui/ButtonStyle.css", "/css/cadastroui/LabelStyle.css", "/css/cadastroui/PaneStyle.css", "/css/cadastroui/TextFieldStyle.css");

        stage.setTitle("Cadastro");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.showAndWait();
    }
}
