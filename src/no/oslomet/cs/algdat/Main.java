package no.oslomet.cs.algdat;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {


        String [] str = {"hei", null, "paa", "deg",null};

        Integer [] ints = {1, 2, null, 3, 4};

        Integer [] ints2 = {};

        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};

        Liste<String> nyListe = new DobbeltLenketListe<>(navn);
        DobbeltLenketListe.sorter(nyListe, Comparator.naturalOrder());
        System.out.println(nyListe);
        System.out.println(nyListe.hent(3));


    }
}
