# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Krav til innlevering

Se oblig-tekst for alle krav. Oppgaver som ikke oppfyller følgende vil ikke få godkjent:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* Ingen debug-utskrifter
* Alle testene som kreves fungerer (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet

# Arbeidsfordeling

Oppgaven er levert av følgende studenter:
* Aina Katrine Stustad-Meren, s338849, s338849@oslomet.no
* Julie Kvarme Johansen, s338850, s338850@oslomet.no
* Günel Shirinova, s339490, s339490@oslomet.no

Vi har brukt git til å dokumentere arbeidet vårt. 

I oppgaven har vi hatt følgende arbeidsfordeling:
* Aina har hatt hovedansvar for oppgave 6, 8, og 9. 
* Günel har hatt hovedansvar for oppgave 4, 5 og 7. 
* Julie har hatt hovedansvar for oppgave 1, 2 (2a løst i samarbeid med Aina),
 3 (3a i samarbeid med Günel) og 10.
 
 # Merknader:
 * Vi får warnings på metoder hvor vi bruker Objects.requireNonNull() som nullsjekk. Siden denne 
 nullsjekken ble foreslått brukt i oppgaveteksten, så ser vi bort fra denne. 
 * Oppgave 5g) Feil i indekssjekken. Her klarer vi ikke helt å se hvor feilen ligger. Resten av oppgaven 
 kjører fint.
 * Oppgave 6 zg) Ineffektivkode dukker opp av og til, men ikke alltid. Vi har forsøkt å se på måter 
 å gjøre den mer effektiv på, men ble stående litt fast.     
   

# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

* Oppgave 1: int antall() er løst ved å returnere antallet til listen man jobber med, i alle metdoer som legger til eller fjerner elementer så vil antallet oppdateres.
     I metoden tom() returneres svaret på en sjekk om antallet == 0. 
     Konstruktøren DobbeltlenketListe(T[] a) strater med nullsjekk for tabellen som sendes inn i metoden. Etter dette sjekkes hver enkelt verdi i tabellen om det er en null-verdi. Hvis så hoppes den over. 
     Ellers legges elementene inn etter hverandre. Brukte tegninger som visualisering for å få pekerne til å peke riktig.  
* Oppgave 2: a) Løst ved å lage et Stringbuilder objekt, legger til en startverdi "[" og legger til verdien til hver node i listen ved hjelp av en while-løkke. Hvis listen er tom legges det til en ny " ]". Når
            Og når gjeldendeNode.neste == null, legges gjeldendeNode sin verdien til,sammen med avsluttende "]".
            b) er løst på omtrent samme måte bare med gjeldendeNode som siste node og en sjekk på gjeldeNode.forrige.
            c) leggInn(T verdi) er laget med sjekker for tom liste og for en liste med verdier i. 
* Oppgave 3:a) sjekker om indeks er mindre enn/ større enn antall/2. er den mindre enn starter letingen fra hode, er den større enn starter den fra halen. 
                hent(int indeks) - sjekker om det er en gyldig indeks, med metode foreslått i oppgaveteksten. bruker finnNode() for å hente ut verdi fra riktig verdi.
                oppdater() sjekker gyldig indeks, bruker finnNode til å finne riktig node, setter en ny verdi på noden og returnerer den gamle.
            b) subliste() sjekker for gyldig indekser slik deter forslått i oppgavetekst. Bruker en for-løkke til å gå gjennom listen og metodene leggInn() og hent() for å legge inn riktige verdier i en ny liste som så retrneres.     
* Oppgave 4: Først sjekker metoden om verdien er null, hvis det er null returnerer -1. Så brukes en vanlig for-løkke, den looper gjennom indeks 
0 til den siste verdien. Hvis den finnes i listen, returneres verdien, ellers returneres -1, som står i oppgaveteksten.
* Oppgave 5: Det ble brukt requireNonNull for å ikke tillate nullverdier  og indeksKontroll
metoden bel brukt for å sjekke om det er lovlig antall elementer i listen. Alle de tilfellene som står i oppgaveteksten
er sjekket. Hvis listen er tom, hode blir lik hale. Hvis den skal legges først, verdiene blir satt til hode og forrige pekere blir lik null.
Hvis verdien skal legges sist, blir verdien lik hale og neste pekere blir satt null. Når jeg kjører programmet, får jeg indeks feil. 
* Oppgave 6: Satte jeg opp sjekkene som en mal for hva som  må sjekkes.
 Brukte tegninger for å få pekere til å peke riktig.
 Brukte litt tid på å finne ut av hvordan jeg skulle få pekeren riktig på hvis det fjernes en verdi mellom to verdier, men litt tegning hjalp.
 Får feilmelding i testkjøring på at metoden er ineffektiv, men har ikke kommet fram til en måte å få den til å bli mer effektiv på.
* Oppgave 7: Det skal lages to metoder som tømmes listen. En ble kodet ved bruk av while-løkke og den går fra hodet til hale. Den andre
kodet med for-løkke og fjern-metoden kalles. Fjern metoden fjerner den første verdien helt til listen er tom. I oppgaveteksten står det at 
vi skal velge den mest effektive metoden. Metoden med while-løkke er mest effektiv. DEn gjør 3n + 6 operasjoner og den andre som kaller
fjern metoden gjør 4n + 2 operasjoner. 
* Oppgave 8: a) Satte opp sjekker slik beskrevet i oppg og sørget for å ta vare på _denne.verdi_ og returnere den til slutt.
b) Returnerte _DobbeltLenketListeIterator()_ for å returnere en instans av iteratorklassen.
c) Kontrollerte indeks først og brukte _finnNode(indeks)_ for å sette _denne_ til den noden som tilhører den oppgitte indeksen.
d) Som b), men returnerte _DobbeltLenketListeIterator(indeks)_
* Oppgave 9: Satt inn sjekker for avvik. Laget en mal av sjekker ut fra sjekkliste i oppaveteksten.
Laget hjeplevariabel, som beskrevet i kompendiet, og brukte den og tegning til å modifisere _fjern_-metodene som var laget tidligere. 
*Oppgave 10: Så til eksempelkode 1.4.6 b) i kompendiet for innsettingsortering. Bruker metodene oppdater() og hent() fra Liste-interfacet for å sette verdiene på riktig plass. 

