package it.coduric.dama;

public class Casella {
    private int riga;
    private int colonna;
    private Pezzo p;

    public Casella(int riga, int colonna, Pezzo p){
        this.riga = riga;
        this.colonna = colonna;
        this.p = p;
    }

    public Casella(int riga, int colonna){
        this.riga = riga;
        this.colonna = colonna;
    }

    //metodo che indica se nella casella c'è un pezzo o no
    public boolean isTherePezzo() {
        return p != null;
    }

    //Getters:
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
