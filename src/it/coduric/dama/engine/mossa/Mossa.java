package it.coduric.dama.engine.mossa;

import it.coduric.dama.campo.Casella;
import it.coduric.dama.model.Giocatore;

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
