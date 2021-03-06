package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    // Denne konstruktøren setter hode- og hale-pekeren på samme node, som i dette tilfellet er null,
    // antallet elementer i listen settes til 0, siden vi ikke har lagt inn noen enda.
    public DobbeltLenketListe() {

        hode = hale = null;
        antall = 0;
    }

    public DobbeltLenketListe(T[] a) {
        //Sjekker at tabellen ikke er tom
        Objects.requireNonNull(a, "Tabellen er tom!");

        for (T t : a) {
            //sjekker om t == null og om hode-plassen er "ledig," legger til hvis den er det.
            if (t != null && hode == null) {

                hode = hale = new Node<>(t, null, null);
                antall++;

            } else if (t != null) {  // Sjekker at t ikke er null og legger til hvis den ikke er det.
                Node<T> p = new Node<>(t);
                hale.neste = p;
                p.forrige = hale;
                hale = p;
                hale.neste = null;
                antall++;
            }
            endringer = 0;

        }


    }
    // hjelpemetode til subliste(int fra, int til), hentet fra kompendiet slik det er foreslått i oppgaveteksten.
    private static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antallet (" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    // subliste bruker både leggInn(T verdi) og hent(int indeks)
    public Liste<T> subliste(int fra, int til){

        fratilKontroll(antall, fra, til);

        Liste<T> subListe = new DobbeltLenketListe<>();

        for(int i = fra; i < til ; i++){
            subListe.leggInn(hent(i));
        }
        return subListe;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    // Hjelpemetode som beskrevet i oppgaveteksten, Den skal lete frem en node med gitt indeks,
    // begynner fra hode hvis indeks < antall/2 og fra hale om indeks > antall/2
    public Node<T> finnNode(int indeks){
        Node<T> funnet = hode;
        if(indeks == 0){
            return funnet;
        }
        else if(indeks <= antall/2){

            for (int i = 0; i < indeks; i++){
                funnet = funnet.neste;
            }
        }
        else {
            funnet = hale;
            for(int i = antall-1; i > indeks; i--){
                funnet = funnet.forrige;
            }
        }
        return  funnet;
    }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi, "Du kan ikke legge inn en tom verdi");

        if(antall == 0){
            hode = hale = new Node<>(verdi);
        }
        else{
            Node<T> p = new Node<>(verdi);
            hale.neste = p;
            p.forrige = hale;
            hale = p;
            hale.neste = null;
        }
        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");
        indeksKontroll(indeks, true);  // Se Liste, true: indeks = antall er lovlig

        if (indeks == 0)                     // ny verdi skal ligge først
        {
            hode = hode.forrige = new Node<>(verdi, null, hode);    // legges først
             if (antall == 0)
                 hale = hode;       // hode og hale går til samme node
        }
           else if (indeks == antall)           // ny verdi skal ligge bakerst
        {
            hale = hale.neste = new Node<>(verdi,hale, null);  // legges bakerst
        }
        else
        {
            Node<T> p = hode;                  // p flyttes indeks - 1 ganger
            for (int i = 1; i < indeks; i++) p = p.neste;

            p.neste = new Node<>(verdi, p, p.neste);  // verdi settes inn i listen
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        // returnerer true
        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int indeks) {

        indeksKontroll(indeks, false);

        return finnNode(indeks).verdi;

    }

    @Override
    public int indeksTil(T verdi) {
      //sjekker om verdi er null
        if (verdi == null) {
            return -1;}
        Node<T> p = hode;
         // looper gjennom hele listen
        for (int indeks = 0; indeks < antall ; indeks++)
        {
            if (p.verdi.equals(verdi)) {
                return indeks;}
                p = p.neste;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false);
        Objects.requireNonNull(nyverdi, "Du kan ikke legge inn NULL-verdier");

        Node<T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;
        p.verdi = nyverdi;
        endringer++;
        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {

        if (verdi == null) {
            return false;
        }

        Node<T> p = hode;                       // Gjeldende node

        // Ser etter verdien.
        while (p != null) {
            if (p.verdi.equals(verdi)) {        // Når veriden er funnet hopper den ut av løkka
                break;
            }
            p = p.neste;                        // Eller så sjekkes neste element osv.
        }

        // Verdien finnes ikke i lista
        if (p == null) {
            return false;
        }
        // Bare en node i lista
        else if (antall == 1) {
            hode = hale = null;
        }
        // Første fjernes
        else if (p == hode) {
            hode = hode.neste;                  // Hode-node blir neste node
            hode.forrige = null;                // Gammelt hode blir null
        }
        // Siste fjernes
        else if (p == hale) {
            hale = hale.forrige;                // Hale-node blir forrige node
            hale.neste = null;                  // Gammel hale blir null
        }
        // Verdi mellom to andre fjernes
        else {
            p.forrige.neste = p.neste;
            p.neste.forrige = p.forrige;
        }

        // Resisrkuilering
        p.verdi = null;
        p.forrige = p.neste = null;

        antall--;                               // Det er en verdi midre i lista
        endringer++;                            // Det har skjedd en endring i lista

        return true;                            // Fjerningen er vellykket

    }

    @Override
    public T fjern(int indeks) {

        indeksKontroll(indeks, false);  // sjekker indeks.

        Node<T> p = hode;                       // Gjeldende node

        // Bare en node i listen
        if (antall == 1) {
            hode = hale = null;
        }
        // Første node skal fjernes
        else if (indeks == 0) {
            hode = hode.neste;                  // neste node gjores til hode.
            hode.forrige = null;                // hode som fjernes, blir null.
        }
        // Sisite node fjernes
        else if (indeks == antall - 1) {
            p = hale;
            hale = hale.forrige;                // noden før halen gjøres til hale
            hale.neste = null;                  // den gamle halen gjøres til null.
        }
        else {
            p = finnNode(indeks);               // hjelpemetode fra tidligere oppgave
            p.forrige.neste = p.neste;
            p.neste.forrige = p.forrige;
        }

        // Returneres
        T verdi = p.verdi;
        // Resirkulering
        p.verdi = null;
        p.forrige = p.neste = null;

        antall--;                               // reduserer antallet
        endringer++;                            // øker endringer

        return verdi;                           // returnerer fjernet verdi

    }

    @Override
    public void nullstill() {

        Node<T> p = hode;
        Node<T> q;
        while (p != null){
           q = p.neste;
            p.verdi = null;
            p.neste = null;
            p = q;
        }
        hode = hale = null;
        endringer++;
        antall = 0;


        /*
        for(int i = 0; i < antall(); i++){
               fjern(0);

        }
        hode = hale = null;
        endringer++;
        antall= 0;*/
    }

    @Override
    public String toString() {

            StringBuilder sb = new StringBuilder();         //oppretter en StringBuilder
            sb.append("[");                                 //Legger til "[" som første element
            Node<T> p = hode;                               // Setter hode som første Node den sjekker,

            if(antall == 0){                                // Sjekker om tabellen er tom, i så fall setter den på "]" på stringen
                sb.append("]");
            }
            else {                                          //Hvis den ikke er tom, går den igjennom alle nodene så lenge de har en
                while (p.neste != null) {                   // nesteverdi som ikke er lik 0. Så legger de til verdien i strengen etterfulgt av et komma.
                    sb.append(p.verdi).append(", ");
                    p = p.neste;                            // Setter p= lik neste node.
                }
                sb.append(p.verdi).append("]");             //Vi må ha med den siste verdien, etterfulgt av den siste firkantklammen.
            }

            return sb.toString();
    }

    public String omvendtString() {

        StringBuilder osb = new StringBuilder();
        osb.append("[");

        if (!tom()) {
            osb.append(hale.verdi);

            Node<T> q = hale.forrige;
            while (q != null) {
                osb.append(", ").append(q.verdi);
                q = q.forrige;
            }
        }

        osb.append("]");

        return osb.toString();

    }

    @Override
    public Iterator<T> iterator() {

        return new DobbeltLenketListeIterator();

    }

    public Iterator<T> iterator(int indeks) {

        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){

            indeksKontroll(indeks,false);
            denne = finnNode(indeks);   // Setter "denne" til den noden som tilhorer den oppgitte indeksen
            // Resten er som i ferdigkodet constructor
            fjernOK = false;
            iteratorendringer = endringer;

        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){

            // Sjekker om "iteratorendringer" stemmer med "endringer", hvis ikke kastes avvik.
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Det har skjedd endringer i listen!");
            }

            // Sjekker om det er fler igjen i lista, og er det ikke det kastes avvik.
            if (denne == null) {
                throw new NoSuchElementException("Ingen verdier igjen i listen!");
            }

            fjernOK = true;

            T verdi = denne.verdi;          // Tar vare på verdien i "denne".
            denne = denne.neste;            // Flytter "denne" til neste node.

            return  verdi;                  //returnerer "dennes" verdi.
        }

        @Override
        public void remove(){

            // Sjekker on det er tillatt å kalle denne metoden
            if (!fjernOK) {
                throw new IllegalStateException("Ulovlig tilstand!");
            }

            // Sjekker om "endringer" og "iteratorendringer" er like
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Endringer har skjedd i listen!");
            }

            fjernOK = false;    // remove() kan ikke kalles på nytt
            Node<T> denDer = hode;   // Hjelpevariabel som beskrevet i kompendie

            // Hode og hale nulles når det bare er et element
            if (antall == 1) {
                hode = hale = null;
            }
            // Hale oppdateres når den siste skal fjernes
            else if (denne == null) {
                denDer = hale;
                hale = hale.forrige;
                hale.neste = null;
            }
            // Hode oppdateres når den første skal fjernes
            else if (denne.forrige == hode) {
                hode = hode.neste;
                hode.forrige = null;
            }
            // Node inne i listen skal oppdateres: pekere i hver side må oppdateres
            else {
                denDer = denne.forrige;     // "denDer" skal fjernes
                denDer.forrige.neste = denDer.neste;
                denDer.neste.forrige = denDer.forrige;
            }

            denDer.verdi = null; // Resirkulerer: nuller verdien i noden
            denDer.neste = null; // Resirkulerer: nuller nestereferansen


            antall--;               // antall reduseres
            endringer++;            // endreinger økes
            iteratorendringer++;    // iteratorendringer økes

        }

    } // class DobbeltLenketListeIterator


    // Basert på eksempel i kompendiet 1.4.6 b)
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {

        Objects.requireNonNull(liste, "Listen kan ikke være null");

        for (int i = 1; i < liste.antall(); i++)
        {
            T verdi = liste.hent(i);
            int  j = i - 1;

            for (; j >= 0 && c.compare(verdi, liste.hent(j)) < 0 ; j--) {
                liste.oppdater(j+1, liste.hent(j));
            }
            liste.oppdater(j+1, verdi);
        }
    }
} // class DobbeltLenketListe


