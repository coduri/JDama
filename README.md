# JDama
Gioco della dama implementato senza GUI, la creazione della "grafica" è stata fatta attraverso singoli caratteri.

### Giocatore "Nero" (rosso):
    ♢ Pedina
    ♦ Dama

### Giocatore "Bianco" (azzurro):
    ☉ Pedina
    ◉ Dama

## Schermata di gioco:
Dalla console è possibile visualizzare la schermata di gioco, che viene stampata ad ogni mossa, da questa è possibile vedere la disposizione delle pedine, e attraverso un contatore è sempre possibile sapere quale dei due giocatori sta avendo la meglio.
    
<img src="art/schermata.png" width="100%">

### Formato input:
    L'input della mossa deve essere effetuato con un formato di questo tipo:
    
<img src="art/formato_cattura.png" width="40%"> <img src="art/formato_movimento.png" width="40%">

- Dove 'c' ed 'm' indicano il tipo di mossa che si vuole fare: cattura o movimento
- I due numeri che seguono indicano rispettivamente la posizione di partenza e la posizione di arrivo, più precisamente:
    - la prima cifra indica la riga
    - la seconda indica la colonna.
