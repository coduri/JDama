package it.coduric.dama.model.pezzi;

import it.coduric.dama.model.Giocatore;

/**
 * Classe astratta che rappresenta un pezzo generico, nel caso della dama era possibile implementare un'unica classe
 * Pezzo senza creare figlio Pedina e Dama (in quanto molto simili), per una questione logica ho preferito crearle entrambe.
*/

public abstract class Pezzo {
    private final Giocatore proprietario; //giocatore proprietario del pezzo
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

    //Simbolo non chiesto dal costruttore perché necessario costrutto di selezione nel costruttore di pedina e dama
    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

}
