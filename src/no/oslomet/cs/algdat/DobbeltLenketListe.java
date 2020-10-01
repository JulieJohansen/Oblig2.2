/*Aina Katrine Stustad-Meren, s338849, s338849@oslomet.no
Julie Kvarme Johansen, s338850, s338850@oslomet.no
Günel Shirinova, s339490, s339490@oslomet.no */

package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;

import java.util.function.Predicate;



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
        //throw new UnsupportedOperationException();
        hode = hale = null;
        antall = 0;
    }
        // Se på endringer-variabelen.
    public DobbeltLenketListe(T[] a) {
        //Sjekker at tabellen ikke er tom
        Objects.requireNonNull(a, "Tabellen er tom!");

        for (T t : a) {
            //sjekker om t == null og om hode-plassen er "ledig," legger til hvis den er det.
            if (t != null && hode == null) {
                hode = hale = new Node<T>(t, null, null);
                antall++;
            } else if (t != null && hale != null) {  // Sjekker at t ikke er null og legger til hvis den ikke er det.
                hale = hale.neste = new Node<T>(t, hale.forrige, null);
                antall++;
            } else {        // lager en tom liste hvis alle elementene i listen er null.
                hode = hale = null;
                antall = this.antall();
            }
            endringer = 0;

        }
        //throw new UnsupportedOperationException();

    }

    public Liste<T> subliste(int fra, int til){
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        //throw new UnsupportedOperationException();
        return antall;
    }

    @Override
    public boolean tom() {
        //throw new UnsupportedOperationException();
        return antall == 0;
    }
    // Hjelpemetode som beskrevet i oppgaveteksten, Den skal lete frem en node med gitt indeks, begynner fra hode hvis indeks < antall/2
    // og fra hale om indeks > antall/2
    private Node<T> finnNode(int indeks){
        Node<T> p = hode;
        if(indeks < antall/2){
            for (int i = 0; i < indeks; i++){
                p = p.neste;
            }
        }
        else {
            p = hale;
            for(int i = antall-1; i >= indeks; i--){
                p = p.forrige;
            }
        }
        return  p;
    }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi, "Du kan ikke legge inn en tom verdi");
        boolean legginn = false;

        if(antall == 0){
            hode = hale = new Node<T>(verdi);
            antall++;
            endringer++;
            legginn = true;
        }
        else{
            hale = hale.neste = new Node<T> (verdi);
            antall++;
            endringer++;
            legginn = true;
        }
        return legginn;
        //throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {

        indeksKontroll(indeks, false);

        return finnNode(indeks).verdi;
        //throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {

        if (verdi == null) {
            return false;
        }

        Node<T> p = hode;

        // Ser etter verdien.
        while (p != null) {
            if (p.verdi.equals(verdi)) {
                break;
            }
            p = p.neste;
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
            hode = hode.neste;
            hode.forrige = null;
        }
        // Siste fjernes
        else if (p == hale) {
            hale = hale.forrige;
            hale.neste = null;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {

        indeksKontroll(indeks, false);  // sjekker indeks.

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
            hale = hale.forrige;
            hale.neste = null;
        }
        // Se på hva som skjer videre
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }
    //Fungerer ikke. Får null pointer og legger ikke til neste element i listen.
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();         //oppretter en StringBuilder
        sb.append("[");                                 //Legger til "[" som første element

        if(!tom()){                                     // Sjekker at tabellen ikke er tom.
            sb.append(hode.verdi);                      // Legger til første verdi i lista.

            Node<T> p = hode.neste;
            while (p != null) {
                sb.append(", ").append(p.verdi);
                p = p.neste;
            }
        }

        sb.append("]");                                 // Legger til "]" på slutten.

        return sb.toString();                           // returnerer strengen som er satt sammen.

        // throw new UnsupportedOperationException();


    }
    // Denne fungerer ikke, stopper etter første verdi i listen. det er logikken det er noe galt med.
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
        //throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


