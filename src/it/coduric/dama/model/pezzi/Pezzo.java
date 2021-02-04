package it.coduric.dama.model.pezzi;

import it.coduric.dama.model.Giocatore;

public abstract class Pezzo {
    private Giocatore proprietario; //giocatore proprietario del pezzo
    private char simbolo;

    public Pezzo(Giocatore g){
        proprietario = g;
    }

    public Giocatore getProprietario(){
        return proprietario;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

}
