package it.coduric.dama.model.pezzi;

import it.coduric.dama.model.Giocatore;

/**
 * Classe che rappresenta una pedina (setta il simbolo in base al giocatore a cui appartiene)
*/

public class Pedina extends Pezzo{

    public Pedina(Giocatore proprietario){
        super(proprietario); //setto gicatore a cui appartiene pedina

        if(proprietario.isBianco())
            super.setSimbolo('☉');  //simbolo pedina "bianca"
        else
            super.setSimbolo('♢');  //simbolo pedina "nera"
    }

}
