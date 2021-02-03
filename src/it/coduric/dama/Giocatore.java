package it.coduric.dama;

public class Giocatore {
    private boolean bianco; // se vero => "giocatore bianco", se falso => "giocatore nero"

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
