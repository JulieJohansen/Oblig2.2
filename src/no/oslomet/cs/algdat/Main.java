package no.oslomet.cs.algdat;

import org.junit.platform.engine.support.hierarchical.Node;

public class Main {
    public static void main(String[] args) {

        String [] str = {"hei", null, "paa", "deg",null};

        Integer [] ints = {1, 2, null, 3, 4};

        Integer [] ints2 = {};

        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>(str);
        System.out.println("Antall : "+liste.antall()+". Er tabellen tom? "+liste.tom());

        DobbeltLenketListe<Integer> intliste = new DobbeltLenketListe<>(ints);
        System.out.println("Antall : "+intliste.antall()+". Er tabellen tom? "+intliste.tom());
        DobbeltLenketListe<Integer> intliste2 = new DobbeltLenketListe<>(ints2);
        System.out.println("Antall : "+intliste2.antall()+". Er tabellen tom? "+intliste2.tom());

        System.out.println(liste.hent(1));

        System.out.println(intliste.oppdater(2, 9));
        System.out.println(intliste.toString());

        //Integer newint = intliste2.hent(0);
        //System.out.println(newint);
    }
}
