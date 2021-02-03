package it.coduric.dama;

public class Dama extends Pezzo{

    public Dama(Giocatore proprietario){
        super(proprietario); //setto gicatore a cui appartiene pedina

        if(proprietario.isBianco())
            super.setSimbolo('◉');  //simbolo dama "bianca"
        else
            super.setSimbolo('♦');  //simbolo dama "nera"
    }
}
