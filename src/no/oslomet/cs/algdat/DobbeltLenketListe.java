package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
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

    public DobbeltLenketListe() {
        throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {
        //throw new UnsupportedOperationException();
        //Har ikke gjort noe med neste og forrige pekere ennå. Er litt usikker på hvor de skal settes

        if(tom()){
            //Hvis tabellen er tom, kaster det et unntak
            throw new NullPointerException("Tabellen a er null!");
        }
        // looper gjennom hver av disse verdiene i a
        for(T verdi : a){
            //sjekker om verdi er null
            if(verdi != null){
                a[antall++] = verdi;
            }
        }

    }

    public Liste<T> subliste(int fra, int til){
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        //throw new UnsupportedOperationException();
        //Har opprettet en ny node p
        Node<T> p = hode;
        //den går helt til p.neste er lik null, dvs helt til p er lik hale
        while (p.neste != null){
            //hver gang blir p lik til neste node
            p = p.neste;
            //oker antallet
            antall++;

        }
        return antall;
    }

    @Override
    public boolean tom() {
        //throw new UnsupportedOperationException();
        //Hvis antallet() er 0, så er listen tom og det returnerer true.
        return antall() == 0;
    }

    private Node<T> finnNode(int index) {
        Node<T> p = hode;
        if (index < (antall / 2)) {
            // den starter fra hode og looper til indeks
            for (int i = 0; i < index; i++) {
                p = p.neste;
            }
        } else {
            // p ble satt til hale
            p = hale;
            // her må den nye noden starte fra halen og gå til indeks
            for (int i = antall - 1; i >= index; i--) {
                p = p.forrige;
            }
        }
        return p;
    }
    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        //Den versjonen ble beskrevet i kompendiet
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, true);  // Se Liste, true: indeks = antall er lovlig

        if (indeks == 0)                     // ny verdi skal ligge først
        {
            hode = new Node<>(verdi, null, hode);    // legges først
            if (antall == 0)
                hale = hode;      // hode og hale går til samme node
        }
        else if (indeks == antall)           // ny verdi skal ligge bakerst
        {
            hale = hale.neste = new Node<>(verdi, hale, null);  // legges bakerst
        }
        else
        {
            Node<T> p = hode;                  // p flyttes indeks - 1 ganger
            for (int i = 1; i < indeks; i++) p = p.neste;

            p.neste = new Node<>(verdi, p.neste, p);  // verdi settes inn i listen
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        //throw new UnsupportedOperationException();
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
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
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


