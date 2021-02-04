package it.coduric.dama.campo;

import it.coduric.dama.model.pezzi.Pezzo;

/**
 * Questa classe rappresenta una casella del campo di gioco, la casella è all'interno di una matrice e ha una sua
 * riga e colonna di appartenenza e può contenere o non contenere un pezzo. (Se non lo contiene p = null)
*/

public class Casella {
    private int riga;
    private int colonna;
    private Pezzo p;

    //Costruttore per inizializzare una casella (il pezzo verrà settato sempre attraverso setP())
    public Casella(int riga, int colonna){
        this.riga = riga;
        this.colonna = colonna;
        this.p = null;
    }

    //metodo che indica se nella casella c'è un pezzo o no
    public boolean isTherePezzo() {
        return p != null; //se p != null dico true
    }


    public Pezzo getPezzo() {
        return p;
    }

    public int getRiga() {
        return riga;
    }

    public int getColonna() {
        return colonna;
    }

    public void setP(Pezzo p) {
        this.p = p;
    }
}
