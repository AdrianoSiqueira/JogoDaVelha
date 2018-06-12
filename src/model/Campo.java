package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

@SuppressWarnings("unused")
public class Campo {
    private BooleanProperty disponivel;
    private char simbolo;

    public Campo() {
        this.disponivel = new SimpleBooleanProperty(true);
        this.simbolo = ' ';
    }

    public boolean isDisponivel() {
        return disponivel.get();
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel.set(disponivel);
    }

    public BooleanProperty disponivelProperty() {
        return disponivel;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }
}
