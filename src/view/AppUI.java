package view;

import controller.App;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class AppUI extends Application {

    private Button[] buttons;
    private App app;

    public static void main(String[] args) {
        launch();
    }

    /**
     * <p>Realiza a jogada e verifica as condicoes de finalizacao do jogo.</p>
     *
     * @param posicao Posicao que o botao representa no tabuleiro.
     */
    private void jogar(int posicao) {
        buttons[posicao].setText(String.valueOf(app.jogar(posicao)));
        buttons[posicao].setId(!app.isPrimeiroJogador() ? "primeiro" : "segundo");

        // Encerra o jogo quando houver um vencedor, ou quando esgotarem as jogadas
        int vencedor = app.verificarVencedor();
        if (vencedor >= 0 || app.getJogadas() == 9) {
            app.encerrar(vencedor);
        }
    }

    @Override
    public void start(Stage stage) {
        app = new App();
        buttons = new Button[9];

        buttons[0] = new Button();
        app.getTabuleiro()[0].disponivelProperty().addListener((observable, oldValue, newValue) -> buttons[0].setDisable(!newValue));
        buttons[0].setOnAction(event -> jogar(0));

        buttons[1] = new Button();
        app.getTabuleiro()[1].disponivelProperty().addListener((observable, oldValue, newValue) -> buttons[1].setDisable(!newValue));
        buttons[1].setOnAction(event -> jogar(1));

        buttons[2] = new Button();
        app.getTabuleiro()[2].disponivelProperty().addListener((observable, oldValue, newValue) -> buttons[2].setDisable(!newValue));
        buttons[2].setOnAction(event -> jogar(2));

        buttons[3] = new Button();
        app.getTabuleiro()[3].disponivelProperty().addListener((observable, oldValue, newValue) -> buttons[3].setDisable(!newValue));
        buttons[3].setOnAction(event -> jogar(3));

        buttons[4] = new Button();
        app.getTabuleiro()[4].disponivelProperty().addListener((observable, oldValue, newValue) -> buttons[4].setDisable(!newValue));
        buttons[4].setOnAction(event -> jogar(4));

        buttons[5] = new Button();
        app.getTabuleiro()[5].disponivelProperty().addListener((observable, oldValue, newValue) -> buttons[5].setDisable(!newValue));
        buttons[5].setOnAction(event -> jogar(5));

        buttons[6] = new Button();
        app.getTabuleiro()[6].disponivelProperty().addListener((observable, oldValue, newValue) -> buttons[6].setDisable(!newValue));
        buttons[6].setOnAction(event -> jogar(6));

        buttons[7] = new Button();
        app.getTabuleiro()[7].disponivelProperty().addListener((observable, oldValue, newValue) -> buttons[7].setDisable(!newValue));
        buttons[7].setOnAction(event -> jogar(7));

        buttons[8] = new Button();
        app.getTabuleiro()[8].disponivelProperty().addListener((observable, oldValue, newValue) -> buttons[8].setDisable(!newValue));
        buttons[8].setOnAction(event -> jogar(8));

        Label labelTitulo = new Label("Jogo da Velha"),
                labelNome1 = new Label(app.getJogadores()[0].getNome()),
                labelNome2 = new Label(app.getJogadores()[1].getNome()),
                labelVs = new Label("VS");

        labelTitulo.getStyleClass().add("label-titulo");
        labelVs.getStyleClass().add("label-vs");

        // Posicionamento dos componentes do painel do tabuleiro
        GridPane.setConstraints(buttons[0], 0, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(buttons[1], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(buttons[2], 2, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(buttons[3], 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(buttons[4], 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(buttons[5], 2, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(buttons[6], 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(buttons[7], 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(buttons[8], 2, 2, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane paneTabuleiro = new GridPane();
        paneTabuleiro.getStyleClass().add("pane-tabuleiro");
        paneTabuleiro.getChildren().addAll(buttons[0], buttons[1], buttons[2], buttons[3], buttons[4], buttons[5], buttons[6], buttons[7], buttons[8]);

        // Posicionamento dos componentes do painel do placar
        GridPane.setConstraints(labelNome1, 0, 0, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(labelVs, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(labelNome2, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER);

        GridPane panePlacar = new GridPane();
        panePlacar.getStyleClass().add("pane-placar");
        panePlacar.getChildren().addAll(labelNome1, labelVs, labelNome2);

        // Posicionamento dos componentes do painel de fundo principal
        GridPane.setConstraints(labelTitulo, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(paneTabuleiro, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(panePlacar, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

        GridPane paneFundo = new GridPane();
        paneFundo.getStyleClass().add("pane-fundo");
        paneFundo.getChildren().addAll(labelTitulo, paneTabuleiro, panePlacar);

        Scene scene = new Scene(paneFundo);
        scene.getRoot().getStylesheets().clear();
        scene.getRoot().getStylesheets().addAll("/css/appui/ButtonStyle.css", "/css/appui/LabelStyle.css", "/css/appui/PaneStyle.css");

        stage.setTitle("Jogo da Velha");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}