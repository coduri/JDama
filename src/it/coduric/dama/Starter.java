package it.coduric.dama;

import it.coduric.dama.campo.CampoDiGioco;
import it.coduric.dama.engine.MotoreGioco;
import it.coduric.dama.engine.mossa.Mossa;
import it.coduric.dama.engine.mossa.tipoMossa;
import it.coduric.dama.model.Giocatore;

import static it.coduric.dama.costanti.CostantiColori.*;

public class Starter {

    public static void main(String[] args) {
        CampoDiGioco c = new CampoDiGioco();
        MotoreGioco engine = new MotoreGioco(c);
        Giocatore gBianco = new Giocatore(true); //stampato con colore azzurri
        Giocatore gNero = new Giocatore(false);  //stampato con colore rosso
        Mossa m;

        System.out.println("\n© Christian Coduri");

        while(!engine.finePartita()){
            
            engine.stampaPunteggio();
            c.printGame();

            if(engine.getTurno())
                m = engine.leggiMossa(gBianco); //turno in motore di gioco = true => tocca al bianco (azzurro)
            else
                m = engine.leggiMossa(gNero);

            if(m != null) {
                if (m.getTipo() == tipoMossa.CATTURA)
                    engine.effettuaCattura(m);
                else
                    engine.effettuaMossa(m);
            }
        }

        c.printGame();
        System.out.println();
        System.out.println(ANSI_YELLOW + "Ha vinto il giocatore " + engine.vincitore());

    }
}
