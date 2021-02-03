package it.coduric.dama;

public class Validatore {


    /**
     * Questo metodo effettua dei controlli sulla MOSSA che si vuole effettuare.
     * Mossa.partenza sarà la casella di partenza
     * Mossa.arrivo sarà la casella di arrivo ovvero a destra o sinistra della riga successiva
     *
     * @return true/false in base a se la mossa è consentita o no ( NON equivale alla cattura )
     */
    public static boolean mossaConsentita(Mossa m) {

        //Attributi per semplificare le chiamate a chiamate
        Casella partenza = m.getPartenza();
        Casella arrivo = m.getArrivo();
        Giocatore giocante = m.getGiocante();

        //Se nella casella di partenza non c'è nessun pezzo
        if (!partenza.isTherePezzo())
            return false;

        //Controllo che non esca dal campo di gioco
        if (arrivo.getRiga() > 7 || arrivo.getColonna() > 7)
            return false;

        //Se sto andando in una casella che contiene un pezzo NON è una mossa, ma potrebbe essere una cattura.
        if (arrivo.isTherePezzo())
            return false;

        //Controllo che giocante stia muovendo un suo pezzo
        if (partenza.getPezzo().getProprietario().isBianco() != giocante.isBianco())
            return false;

        //Pezzo mosso: PEDINA
        if (partenza.getPezzo() instanceof Pedina) {

            //Pedina BIANCA: si muove verso l'alto
            if (partenza.getPezzo().getProprietario().isBianco()) {

                //Controllo se si muove correttamente: riga -= 1 (verso alto decresce) && colonna += 1 (destra) || colonna -= 1 (sinistra)
                if (arrivo.getRiga() == partenza.getRiga() - 1 && (arrivo.getColonna() == partenza.getColonna() + 1 || arrivo.getColonna() == partenza.getColonna() - 1))
                    return true;
                else
                    return false;

            }

            //Pedina NERA: si muove verso il basso
            else {

                //Controllo se si muove correttamente: riga += 1 (verso il basso cresce) && colonna += 1 (destra) || colonna -= 1 (sinistra)
                if (arrivo.getRiga() == partenza.getRiga() + 1 && (arrivo.getColonna() == partenza.getColonna() + 1 || arrivo.getColonna() == partenza.getColonna() - 1))
                    return true;
                else
                    return false;

            }
        }

        //Pezzo mosso: DAMA
        else {
            // se { riga +=1 (verso basso)|| riga -=1 (verso alto) } && { colonna +=1 (verso sinistra) || colonna +=1 (verso destra) }
            boolean cond1 = (arrivo.getRiga() == partenza.getRiga() + 1 || arrivo.getRiga() == partenza.getRiga() - 1);
            boolean cond2 = (arrivo.getColonna() == partenza.getColonna() + 1 || arrivo.getColonna() == partenza.getColonna() - 1);

            if (cond1 && cond2)
                return true;
            else
                return false;
        }
    }

    /**
     * Questo metodo effettua dei controlli sulla CATTURA che si vuole effettuare.
     * Mossa.partenza sarà la casella di partenza
     * Mossa.arrivo sarà la casella di arrivo ovvero a destra o sinistra (di due caselle rispetto alla partenza)
     * di due righe successive
     *
     * @return true/false in base a se la cattura è consentita o no ( NON equivale ad una mossa )
     */
    public static boolean catturaConsentita(Mossa m, CampoDiGioco board) {

        //Attributi per semplificare le chiamate a chiamate
        Casella partenza = m.getPartenza();
        Casella arrivo = m.getArrivo();
        Giocatore giocante = m.getGiocante();

        //Se nella casella di partenza non c'è nessun pezzo
        if (!partenza.isTherePezzo())
            return false;

        //Controllo che non esca dal campo di gioco
        if (arrivo.getRiga() > 7 || arrivo.getColonna() > 7)
            return false;

        //Se sto andando in una casella che contiene un pezzo NON è una cattura, ma ho saltato una casella per arrivare ad una contentente un altro pezzo
        if (arrivo.isTherePezzo())
            return false;

        //Controllo che giocante stia muovendo un suo pezzo
        if (partenza.getPezzo().getProprietario().isBianco() != giocante.isBianco())
            return false;

        //Controllo se nella casella di mezzo (quella saltata) c'è un pezzo dell'avversario (ovvero quello che voglio catturare)
        int rigaCattura = (arrivo.getRiga() + partenza.getRiga()) / 2;
        int colCattura = (arrivo.getColonna() + partenza.getColonna()) / 2;
        Casella cattura = board.getCampo()[rigaCattura][colCattura];

        //nella casella di mezzo NON c'è un pezzo
        if (!cattura.isTherePezzo())
            return false;

        //nella casella di mezzo c'è un pezzo, controllo non sia dello stesso giocatore
        else if (cattura.getPezzo().getProprietario().isBianco() == giocante.isBianco())
            return false;

        //Pedina non può mangiare una dama
        if(partenza.getPezzo() instanceof Pedina && cattura.getPezzo() instanceof Dama)
            return false;

        //Pezzo mosso: PEDINA (controllo se movimento di cattura corretto)
        if (partenza.getPezzo() instanceof Pedina) {

            //Pedina BIANCA: si muove verso l'alto
            if (partenza.getPezzo().getProprietario().isBianco()) {

                //Controllo se si muove correttamente: riga -= 2 (verso alto decresce) && colonna += 2 (destra) || colonna -= 2 (sinistra)
                if (arrivo.getRiga() == partenza.getRiga() - 2 && (arrivo.getColonna() == partenza.getColonna() + 2 || arrivo.getColonna() == partenza.getColonna() - 2))
                    return true;
                else
                    return false;

            }

            //Pedina NERA: si muove verso il basso
            else {

                //Controllo se si muove correttamente: riga += 2 (verso il basso cresce) && colonna += 2 (destra) || colonna -= 2 (sinistra)
                if (arrivo.getRiga() == partenza.getRiga() + 2 && (arrivo.getColonna() == partenza.getColonna() + 2 || arrivo.getColonna() == partenza.getColonna() - 2))
                    return true;
                else
                    return false;

            }
        }

        //Pezzo mosso: DAMA (controllo se movimento di cattura corretto)
        else {
            // se { riga +=2 (verso basso)|| riga -=2 (verso alto) } && { colonna +=2 (verso sinistra) || colonna +=2 (verso destra) }
            boolean cond1 = (arrivo.getRiga() == partenza.getRiga() + 2 || arrivo.getRiga() == partenza.getRiga() - 2);
            boolean cond2 = (arrivo.getColonna() == partenza.getColonna() + 2 || arrivo.getColonna() == partenza.getColonna() - 2);

            if (cond1 && cond2)
                return true;
            else
                return false;
        }
    }


    /**
     * Questo metodo effettua un controllo sulla mossa per capire se è stata raggiunta la "base nemica"
     * e quindi se è necessario trasformare una pedina in dama
     *
     * @return true/false se la pedina è da promuovere o no
     */
    public static boolean promozionePedina(Mossa m) {
        Casella arrivo = m.getArrivo();
        boolean bianco = m.getGiocante().isBianco();

        //pezzo BIANCO controllo se è arrivato alla riga 0 (riga per promuovere una pedina a Dama)
        if (bianco && arrivo.getRiga() == 0)
            return true;

            //pezzo NERO controllo se è arrivato alla riga 7 (riga per promuovere una pedina a Dama)
        else if ((!bianco) && arrivo.getRiga() == 7)
            return true;

        else
            return false;
    }
}
