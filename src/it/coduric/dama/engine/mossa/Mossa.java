package it.coduric.dama.engine.mossa;

import it.coduric.dama.campo.Casella;
import it.coduric.dama.model.Giocatore;

/**
 * Classe che rappresenta una mossa che va da una casella (partenza) ad una di arrivo.
 * La mossa può essere una cattura o un semplice movimento (necessario sapere per i controlli).
 * Ogni mossa è generata da un giocatore (necessario sapere per i controlli).
*/

public class Mossa {
    private Casella partenza;
    private Casella arrivo;
    private Giocatore giocante;
    private tipoMossa tipo;

    public Mossa(Casella da, Casella a, Giocatore g, tipoMossa t){
        partenza = da;
        arrivo = a;
        giocante = g;
        tipo = t;
    }

    public Casella getPartenza() {
        return partenza;
    }

    public Casella getArrivo() {
        return arrivo;
    }

    public Giocatore getGiocante() {
        return giocante;
    }

    public tipoMossa getTipo() {
        return tipo;
    }
}
