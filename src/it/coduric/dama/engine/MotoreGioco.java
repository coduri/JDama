package it.coduric.dama.engine;

import it.coduric.dama.campo.CampoDiGioco;
import it.coduric.dama.campo.Casella;
import it.coduric.dama.engine.mossa.Mossa;
import it.coduric.dama.engine.mossa.Validatore;
import it.coduric.dama.engine.mossa.tipoMossa;
import it.coduric.dama.model.pezzi.Dama;
import it.coduric.dama.model.Giocatore;

import java.util.Scanner;
import static it.coduric.dama.costanti.CostantiColori.*;

/**
 * Classe che rappresenta il "braccio" del gioco, si serve dei metodi della classe Validatore ai quali può accedere
 * senza dover istanziare nulla e se questi dicono 'true' allora esegue effettivamente ciò che è stato chiesto.
*/

public class MotoreGioco {
    private CampoDiGioco campo; //campo di gioco su cui deve effettuare le mosse (in caso di esito positivo da validatore)
    private boolean turnoBianco = true; //mantiene il turno. Il turno iniziale sarà true => inizia il giocatore bianco

    public MotoreGioco(CampoDiGioco c){
        campo = c;
    }

    private void nuovoTurno(){
        turnoBianco = !turnoBianco;
    }

    public boolean getTurno() {
        return turnoBianco;
    }


    //Questo metodo si serve di Validatore.mossaConsentita(m) e in caso di esito positivo effettua la mossa e aggiorna turno
    public void effettuaMossa(Mossa m){
        if(Validatore.mossaConsentita(m)){
            //Mossa valida => eseguo lo spostamento: metto in arrivo il pezzo che ho in partenza e in partenza un pezzo null
            campo.setCampo(m.getArrivo().getRiga(), m.getArrivo().getColonna(), m.getPartenza().getPezzo());
            campo.setCampo(m.getPartenza().getRiga(), m.getPartenza().getColonna(), null);

            if(Validatore.promozionePedina(m))
                pedinaToDama(m);

            nuovoTurno();
        }
    }


    //Questo metodo si serve di Validatore.catturaConsentita(m) e in caso di esito positivo effettua la cattura e aggiorna turno
    public void effettuaCattura(Mossa m){
        if(Validatore.catturaConsentita(m, campo)){
            //Cattura valida => eseguo lo spostamento
            campo.setCampo(m.getArrivo().getRiga(), m.getArrivo().getColonna(), m.getPartenza().getPezzo());
            campo.setCampo(m.getPartenza().getRiga(), m.getPartenza().getColonna(), null);

            //Elimino il pezzo nella casella di mezzo
            int rigaCattura = (m.getArrivo().getRiga() + m.getPartenza().getRiga())/2;
            int colCattura = (m.getArrivo().getColonna() + m.getPartenza().getColonna())/2;
            campo.setCampo(rigaCattura, colCattura, null);

            if(Validatore.promozionePedina(m))
                pedinaToDama(m);

            nuovoTurno();
        }
    }


    // effettuaCattura() e effettuaMossa() attraverso Validatore controllano se è necessaria la promozione
    // della pedina che ha effettuato la mossa. In caso di esito positivo rimpiazzo la pedina con una nuova dama.
    public void pedinaToDama(Mossa m){
        m.getArrivo().setP(new Dama(m.getGiocante()));
    }


/**
     * Metodo che permette la lettura di mosse:
     * Formato mosse: c xx xx    cattura RigaColonna(partenza) RigaColonna(arrivo)
     * Formato mosse: m xx xx    muovi RigaColonna(partenza) RigaColonna(arrivo)
     * @return mossa da effettuare
*/
    public Mossa leggiMossa(Giocatore giocatore){
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n" + giocatore.toString() + " > ");
        String strMossa = scanner.nextLine();

        if(strMossa.length() != 7){
            System.out.println("Mossa scritta non correttamente!\n " +
                    "Formato mosse: [c,m] xy xy    [muovi,cattura] RigaColonna(partenza) RigaColonna(arrivo)");
            return null;
        }

        char tipo;
        int daRiga, daColonna, aRiga, aColonna;
        tipoMossa attuale;

        tipo = strMossa.charAt(0);

        if(tipo == 'c' || tipo == 'C')
            attuale = tipoMossa.CATTURA;
        else if(tipo == 'm' || tipo == 'M')
            attuale = tipoMossa.MOVIMENTO;
        else{
            System.out.println("Tipo di mossa non riconosciuto. Tipi disponibili: [c,m]");
            return null;
        }

        try{
            //Ottengo dati sulla mossa da effettuare
            daRiga = Character.getNumericValue(strMossa.charAt(2));
            daColonna = Character.getNumericValue(strMossa.charAt(3));
            aRiga = Character.getNumericValue(strMossa.charAt(5));
            aColonna = Character.getNumericValue(strMossa.charAt(6));

            try{
                Casella c1 = campo.getCampo()[daRiga][daColonna];
                Casella c2 = campo.getCampo()[aRiga][aColonna];
                return new Mossa(c1, c2, giocatore, attuale);
            }
            catch (ArrayIndexOutOfBoundsException e){ //eccezione generata da numeri >7
                System.out.println("Ricorda che l'area di gioco va da 0 a 7 (sia colonne che righe)");
                return null;
            }

        } catch (NumberFormatException e){ //eccezione unchecked generata da errore di parsing su stringhe
            System.out.println("Si è verificato un errore durante la lettura mossa");
            return null;
        }
    }


    //Metodo che ritorna ul numero di Pezzi bianchi in gioco
    private int numBianchi(){
        Casella[][] campoGioco = campo.getCampo();
        int bianchi = 0;

        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                if(campoGioco[i][j].isTherePezzo() && campoGioco[i][j].getPezzo().getProprietario().isBianco())
                    bianchi++;

        return bianchi;
    }

    //Metodo che ritorna ul numero di Pezzi neri in gioco
    private int numNeri(){
        Casella[][] campoGioco = campo.getCampo();
        int neri = 0;

        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                if(campoGioco[i][j].isTherePezzo() && (!campoGioco[i][j].getPezzo().getProprietario().isBianco()))
                    neri++;

        return neri;
    }

    //Metodo che returna true se la partita è finita
    public boolean finePartita(){
        return (numBianchi() == 0 || numNeri() == 0);
    }

    //Metodo che returna caratteri carini
    private char pt(int i){
        if(i==1) return '①';
        if(i==2) return '②';
        if(i==3) return '③';
        if(i==4) return '④';
        if(i==5) return '⑤';
        if(i==6) return '⑥';
        if(i==7) return '⑦';
        if(i==8) return '⑧';
        if(i==9) return '⑨';
        if(i==10) return '⑩';
        if(i==11) return '⑪';
        if(i==12) return '⑫';

        return '0';
    }

    //Metodo che stampa con caratteri carini (↑) il numero di pezzi in gioco di ogni player
    public void stampaPunteggio(){
        System.out.println(ANSI_CYAN + "\nPezzi in gioco: " + pt(numBianchi()));
        System.out.println(ANSI_RED + "Pezzi in gioco: " + pt(numNeri()) + ANSI_RESET);
    }

    //Metodo che returna il toString del giocatore che ha vinto
    public String vincitore(){
        if(numNeri()==0) //nero ha perso
            return new Giocatore(true).toString(); //giocatore bianco
        else
            return new Giocatore(false).toString(); //giocatore nero
    }
}
