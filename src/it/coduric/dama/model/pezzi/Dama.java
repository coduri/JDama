package it.coduric.dama.model.pezzi;

import it.coduric.dama.model.Giocatore;

/**
 * Classe che rappresenta una dama (setta il simbolo in base al giocatore a cui appartiene)
*/

public class Dama extends Pezzo{

    public Dama(Giocatore proprietario){
        super(proprietario); //setto gicatore a cui appartiene pedina

        if(proprietario.isBianco())
            super.setSimbolo('◉');  //simbolo dama "bianca"
        else
            super.setSimbolo('♦');  //simbolo dama "nera"
    }
}
