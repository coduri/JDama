package it.coduric.dama;

public class Pedina extends Pezzo{

    public Pedina(Giocatore proprietario){
        super(proprietario); //setto gicatore a cui appartiene pedina

        if(proprietario.isBianco())
            super.setSimbolo('☉');  //simbolo pedina "bianca"
        else
            super.setSimbolo('♢');  //simbolo pedina "nera"
    }

}
