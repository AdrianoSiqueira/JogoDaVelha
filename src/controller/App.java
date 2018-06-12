package controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Campo;
import model.Jogador;
import model.Mensagem;
import view.CadastroUI;

@SuppressWarnings("unused")
public class App {

    private Campo[] tabuleiro;
    private Jogador[] jogadores;

    private boolean primeiroJogador;
    private int jogadas;

    public App() {
        initTabuleiro();
        initJogadores();
        this.primeiroJogador = true;
        this.jogadas = 0;
    }

    private void initTabuleiro() {
        tabuleiro = new Campo[9];
        for (int i = 0; i < tabuleiro.length; i++) {
            tabuleiro[i] = new Campo();
        }
    }

    private void initJogadores() {
        jogadores = new Jogador[2];
        for (int i = 0; i < jogadores.length; i++) {
            jogadores[i] = new Jogador();
        }

        new CadastroUI(jogadores).start(new Stage());
    }

    /**
     * <p>Exibe uma mensagem de finalizacao e encerra o jogo.</p>
     *
     * @param vencedor Indice do vencedor no vetor de jogadores.
     */
    public void encerrar(int vencedor) {
        new Mensagem(Alert.AlertType.INFORMATION,
                "Jogo da Velha",
                "Fim de Jogo",
                (vencedor < 0) ? "Temos um empate. Parabéns aos dois jogadores!!!" : jogadores[vencedor].getNome() + " venceu o jogo. Parabéns!!!"
        ).show();
        Platform.exit();
    }

    /**
     * <p>Realiza a jogada dependendo da disponibilidade da posicao escolhida. Caso a posicao escolhida esteja disponivel, a jogada e
     * realizada e o simbolo do jogador que realizou a jogada e retornado. Caso contrario, a jogada e cancelada e nao retorna simbolo
     * nenhum.</p>
     *
     * @param posicao Posicao para marcar a jogada.
     * @return Simbolo do jogador que realizou a jogada.
     */
    public char jogar(int posicao) {
        try {
            if (tabuleiro[posicao].isDisponivel()) {
                tabuleiro[posicao].setSimbolo((primeiroJogador) ? 'X' : 'O');
                tabuleiro[posicao].setDisponivel(false);
                primeiroJogador = !primeiroJogador;
                ++jogadas;
                return tabuleiro[posicao].getSimbolo();
            } else {
                return ' ';
            }
        } catch (IndexOutOfBoundsException e) {
            new Mensagem(Alert.AlertType.ERROR, "Jogar", "O valor da posição ultrapassa o limite válido!", e.getMessage()).show();
            return ' ';
        } catch (Exception e) {
            new Mensagem(Alert.AlertType.ERROR, "Jogar", "Ocorreu um error desconhecido!", e.getMessage()).show();
            return ' ';
        }
    }

    /**
     * <p>Verifica todas as possibilidades de vitoria para ambos os jogadores e retorna a posicao do vetor correspondente ao vencedor.</p>
     * <p>Caso o jogo ainda nao tenha terminado ou houve um empate, um valor negativo e retornado.</p>
     *
     * @return Posicao do campeao ou negativo para empate
     */
    public int verificarVencedor() {
        if (tabuleiro[0].getSimbolo() == 'X' && tabuleiro[1].getSimbolo() == 'X' && tabuleiro[2].getSimbolo() == 'X' ||
                tabuleiro[3].getSimbolo() == 'X' && tabuleiro[4].getSimbolo() == 'X' && tabuleiro[5].getSimbolo() == 'X' ||
                tabuleiro[6].getSimbolo() == 'X' && tabuleiro[7].getSimbolo() == 'X' && tabuleiro[8].getSimbolo() == 'X' ||
                tabuleiro[0].getSimbolo() == 'X' && tabuleiro[3].getSimbolo() == 'X' && tabuleiro[6].getSimbolo() == 'X' ||
                tabuleiro[1].getSimbolo() == 'X' && tabuleiro[4].getSimbolo() == 'X' && tabuleiro[7].getSimbolo() == 'X' ||
                tabuleiro[2].getSimbolo() == 'X' && tabuleiro[5].getSimbolo() == 'X' && tabuleiro[8].getSimbolo() == 'X' ||
                tabuleiro[0].getSimbolo() == 'X' && tabuleiro[4].getSimbolo() == 'X' && tabuleiro[8].getSimbolo() == 'X' ||
                tabuleiro[2].getSimbolo() == 'X' && tabuleiro[4].getSimbolo() == 'X' && tabuleiro[6].getSimbolo() == 'X') {
            return 0;
        } else if (tabuleiro[0].getSimbolo() == 'O' && tabuleiro[1].getSimbolo() == 'O' && tabuleiro[2].getSimbolo() == 'O' ||
                tabuleiro[3].getSimbolo() == 'O' && tabuleiro[4].getSimbolo() == 'O' && tabuleiro[5].getSimbolo() == 'O' ||
                tabuleiro[6].getSimbolo() == 'O' && tabuleiro[7].getSimbolo() == 'O' && tabuleiro[8].getSimbolo() == 'O' ||
                tabuleiro[0].getSimbolo() == 'O' && tabuleiro[3].getSimbolo() == 'O' && tabuleiro[6].getSimbolo() == 'O' ||
                tabuleiro[1].getSimbolo() == 'O' && tabuleiro[4].getSimbolo() == 'O' && tabuleiro[7].getSimbolo() == 'O' ||
                tabuleiro[2].getSimbolo() == 'O' && tabuleiro[5].getSimbolo() == 'O' && tabuleiro[8].getSimbolo() == 'O' ||
                tabuleiro[0].getSimbolo() == 'O' && tabuleiro[4].getSimbolo() == 'O' && tabuleiro[8].getSimbolo() == 'O' ||
                tabuleiro[2].getSimbolo() == 'O' && tabuleiro[4].getSimbolo() == 'O' && tabuleiro[6].getSimbolo() == 'O') {
            return 1;
        } else {
            return -1;
        }
    }

    public Campo[] getTabuleiro() {
        return tabuleiro;
    }

    public Jogador[] getJogadores() {
        return jogadores;
    }

    public boolean isPrimeiroJogador() {
        return primeiroJogador;
    }

    public int getJogadas() {
        return jogadas;
    }
}
