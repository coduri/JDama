package it.coduric.dama;

import java.util.Scanner;
import static it.coduric.dama.CostantiColori.*;

public class MotoreGioco {
    private CampoDiGioco campo;

        //Variabile che mantiene il turno. Il turno iniziale sarà true == isBianco di giocatore => inizia il giocatore bianco
    private boolean turnoBianco = true;

    public MotoreGioco(CampoDiGioco c){
        campo = c;
    }

    private void nuovoTurno(){
        turnoBianco = !turnoBianco;
    }

    public boolean getTurno() {
        return turnoBianco;
    }


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
        System.out.println("Stringa:" +strMossa);

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
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Ricorda che l'area di gioco va da 0 a 7 (sia colonne che righe)");
                return null;
            }

        } catch (NumberFormatException e){
            System.out.println("Si è verificato un errore durante la lettura mossa");
            return null;
        }
    }

    private int numBianchi(){
        Casella[][] campoGioco = campo.getCampo();
        int bianchi = 0;

        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                if(campoGioco[i][j].isTherePezzo() && campoGioco[i][j].getPezzo().getProprietario().isBianco())
                    bianchi++;

        return bianchi;
    }

    private int numNeri(){
        Casella[][] campoGioco = campo.getCampo();
        int neri = 0;

        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                if(campoGioco[i][j].isTherePezzo() && (!campoGioco[i][j].getPezzo().getProprietario().isBianco()))
                    neri++;

        return neri;
    }

    public boolean finePartita(){
        return (numBianchi() == 0 || numNeri() == 0);
    }

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

    public void stampaPunteggio(){
        System.out.println(ANSI_CYAN + "\nPezzi in gioco: " + pt(numBianchi()));
        System.out.println(ANSI_RED + "Pezzi in gioco: " + pt(numNeri()) + ANSI_RESET);
    }

    public String vincitore(){
        if(numNeri()==0)
            return "azzurro"; //giocatore bianco
        else
            return "rosso"; //giocatore nero
    }
}
