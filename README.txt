LEVIATHAN

Caso d'uso UC1: Consulta listino
Attore primario: Utente non registrato
Scenario principale di successo: 
1. Un Utente vuole consultare il catalogo dei prodotti e vederne i dettagli.
2. L'Utente sceglie l'attività «Visualizza Catalogo Prodotti».
3. Il Sistema mostra il catalogo dei prodotti.
4. L'Utente seleziona una prodotto di cui vuole conoscere i dettagli. Il Sistema mostra i dettagli del prodotto selezionato.
L'utente ripete i passi 3-4 fino a che non indica che ha terminato


Caso d'uso UC2: Effettua ordine
Attore primario: Cliente
Scenario principale di successo: 
1. Un cliente vuole inserire un nuovo ordine.
2. Il Cliente sceglie l'attività «Crea Nuovo Ordine».
3. Il Sistema mostra il catalogo dei prodotti.
4. Il Cliente seleziona un prodotto del catalogo, per inserirlo nell'ordine. 
5. Il Sistema mostra i dettagli riguardo il prodotto selezionato.
6. L’Utente inserisce una quantità desiderata del prodotto nell'ordine. Il Sistema imposta il prezzo unitario del prodotto scelto dal prezzo corrente del prodotto nel catalogo. Il Sistema aggiunge il prodotto e le informazioni a esso associate all'ordine.
Il Cliente ripete i passi 3-6 fino a che non indica che ha terminato.
7. Il Cliente conferma la creazione dell'ordine. Il Sistema registra tutte le informazioni riguardo il nuovo ordine, compresa la data di chiusura e un identificatore univoco.  Da questo momento in poi, questo ordine potrà essere visualizzato dal Cliente che l’ha creato (caso d’uso UC3) e anche dagli Amministratori del Sistema (caso d’uso UC6). 


Caso d'uso UC3: Consulta i propri ordini
Attore primario: Cliente
Scenario principale:
1. Un Cliente vuole consultare i propri ordini e vederne i dettagli.
2. Il Cliente sceglie l'attività «Visualizza Ordini Effettuati» 
3. Il Sistema mostra l'elenco degli ordini del cliente.
4. Il Cliente seleziona un ordine di cui vuole conoscere i dettagli. Il Sistema mostra i dettagli dell'ordine selezionato.
Il Cliente ripete i passi 3-4 fino a che non indica che ha terminato.


Caso d'uso UC4: Inserimento prodotti nel catalogo
Attore primario: Amministratore
Scenario principale:
1. Un Amministratore vuole inserire un nuovo prodotto nel catalogo.
2. L'amministratore sceglie l'attività «Crea Nuovo Prodotto».
3. L'amministratore inserisce il nome, il prezzo e la descrizione del prodotto che vuole inserire nel catalogo. Il Sistema registra tutte le informazioni sul nuovo prodotto, compreso un identificatore univoco. 
Il Cliente ripete il passo 3 fino a che non indica che ha terminato.

Caso d'uso UC5: Recupera indirizzo cliente
Attore primario: Amministratore
Scenario principale:
1- Un Amministratore vuole conoscere il creatore di un ordine.
2- L'Amministratore sceglie l'attività «Recupera Indirizzo Cliente».
3- L'Amministratore fornisce l'identificatore dell'ordine di cui vuole conoscere il Cliente. Il Sistema mostra i dati del Cliente che ha effettuato l'ordine scelto.


Caso d'uso UC6: Evasione ordine
Attore primario: Amministratore
Scenario principale:
1- Un Amministratore vuole evadere un ordine chiuso in precedenza.
2- L'Amministratore sceglie l'attività «Evadi Ordine».
3- Il Sistema mostra l'elenco degli ordini chiusi, ma non ancora evasi.
4- L'Amministratore seleziona un'ordine che vuole evadere. Il Sistema evade l'ordine, aggiornando l'ordine inserendo la data di spedizione e  la quantità dei prodotti nel magazzino.
Eccezioni:
1-4a Alcuni prodotti potrebbero non essere presenti nel magazzino nella quantità specificata dall'ordine. Il Sistema non evade nessun ordine e non aggiorna alcuna informazione riguardo esso.

Caso d'uso UC7: Chiusura ordine
Attore primario: Cliente
Scenario principale (forma breve): Il cliente comunica al sistema la chiusura dell'ordine, ovvero l'ordine è pronto per essere evaso
