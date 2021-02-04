package it.coduric.dama.model;

/**
 * Classe che rappresenta un giocatore.
 * Questa ha un attributo che indica il suo colore (bianco o nero).
 * Il giocatore sarà proprietario di più pezzi attraverso "bianco" posso effettuare controlli sui vari pezzi.
*/

public class Giocatore {
    private final boolean bianco; // se vero => "giocatore bianco", se falso => "giocatore nero"

    public Giocatore(boolean bianco){
        this.bianco = bianco;
    }

    public boolean isBianco(){
        return bianco;
    }

    //Per questioni "grafiche" il giocatore bianco viene stampato a consolo come azzuro, mentre il nero come rosso
    public String toString(){
        if(bianco)
            return "Giocatore Azzurro";
        else
            return "Giocatore Rosso";
    }
}
