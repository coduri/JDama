package it.coduric.dama.campo;
import it.coduric.dama.model.Giocatore;
import it.coduric.dama.model.pezzi.Pedina;
import it.coduric.dama.model.pezzi.Pezzo;

import static it.coduric.dama.costanti.CostantiColori.*;

public class CampoDiGioco {
    private Casella[][] campo;

    public CampoDiGioco(){
        campo = new Casella[8][8];
        newGame();
    }

/**
     * Inizializzo il campo di gioco disponendo le pedine nelle posizioni di partenza
     * Giocatore colore nero ha le pedine "in alto" (muove verso il basso)
     * Giocatore colore bianco ha le pedine "in basso" (muove verso l'alto)
*/
    private void newGame(){

        for(int i=0; i<8; i++){
            for (int j=0; j<8; j++) {
                campo[i][j] = new Casella(i, j);    //inizializzo casella

                //controllo se devo posizionare un pezzo del giocatore nero (in alto)
                if(i < 3){
                    if(i%2 == 0 && j%2 == 0){
                        //setto nella casella campo[i][j] una nuova pedina che APPARTIENE al Giocatore nero (riga 0 e 2)
                        campo[i][j].setP(new Pedina(new Giocatore(false)));
                    }

                    if(i%2 != 0 && j%2 != 0){
                        //setto nella casella campo[i][j] una nuova pedina che APPARTIENE al Giocatore nero (riga 1)
                        campo[i][j].setP(new Pedina(new Giocatore(false)));
                    }
                }

                //controllo se devo posizionare un pezzo del giocatore bianco (in basso)
                if(i > 4){
                    if(i%2 != 0 && j%2 != 0){
                        //setto nella casella campo[i][j] una nuova pedina che APPARTIENE al Giocatore nero (riga 5 e 7)
                        campo[i][j].setP(new Pedina(new Giocatore(true)));
                    }

                    if(i%2 == 0 && j%2 == 0){
                        //setto nella casella campo[i][j] una nuova pedina che APPARTIENE al Giocatore nero (riga 6)
                        campo[i][j].setP(new Pedina(new Giocatore(true)));
                    }
                }
            }
        }

    }

/**
     * Di seguito la funzione printGame che stampa a console il campo di gioco,
     * mostrando la disposizione corrente delle pedine
*/
    public void printGame(){
        printBordoAlto();

        for(int i=0; i<8;i++) {
            printPezzi(i);
            System.out.println("\t" + i);
            if(i < 7)
                printBordoCentrale();
        }

        printBordoBasso();
    }

        private void printBordoAlto() {
        System.out.print("┌─────"); 		//angolo alto sx

        for(int i=0; i<7; i++)
            System.out.print("┬─────");

        System.out.println("┐");		//angolo alto dx
    }

        private void printPezzi(int riga){
        for (int i=0; i<8; i++) {
            if(campo[riga][i].isTherePezzo()){
                //in questa casella è contenuto un pezzo, stampo il simbolo
                if(!campo[riga][i].getPezzo().getProprietario().isBianco())
                    System.out.print("│ " + ANSI_RED + ANSI_BLACK_BACKGROUND + " " + campo[riga][i].getPezzo().getSimbolo() + " " + ANSI_RESET + " ");
                else
                    System.out.print("│ " + ANSI_CYAN + ANSI_BLACK_BACKGROUND + " " + campo[riga][i].getPezzo().getSimbolo() + " " + ANSI_RESET + " ");
            }
            else{
                //non è contenuto nessun pezzo nella casella, faccio la differenza per la stampa dello sfondo

                if(riga%2 == 0  &&   i%2 != 0) //riga pari e colonna dispari -> colore bianco
                    System.out.print( "│ " + ANSI_WHITE_BACKGROUND + "   " + ANSI_RESET + " ");

                else if(riga%2 != 0  &&  i%2 == 0) //riga dispari e colonna pari
                    System.out.print( "│ " + ANSI_WHITE_BACKGROUND + "   " + ANSI_RESET + " ");

                else
                    System.out.print( "│ " + ANSI_BLACK_BACKGROUND + "   " + ANSI_RESET + " ");
            }
        }
        System.out.print("│"); //barra chiusura riga
    }

        private void printBordoCentrale() {
        System.out.print("├─────");	//inizio riga centrale

        for(int i=0; i<7; i++)
            System.out.print("┼─────");

        System.out.println("┤");	//fine riga centrale
    }

        private void printBordoBasso() {
        System.out.print("└─────");	//angolo basso sx

        for(int i=0; i<7; i++)
            System.out.print("┴─────");

        System.out.println("┘");		//angolo basso dx

        //stampo indici colonna
        System.out.print("   0");
        for(int i=1; i<8; i++)
            System.out.print("     " + i);

    }

/** Setter e Getter necessari   */
    public void setCampo(int i, int j, Pezzo p) {
        campo[i][j].setP(p);
    }

    public Casella[][] getCampo() {
        return campo;
    }

}
