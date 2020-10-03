package no.oslomet.cs.algdat;

import org.junit.platform.engine.support.hierarchical.Node;

public class Main {
    public static void main(String[] args) {

        String [] str = {"hei", null, "paa", "deg",null};

        Integer [] ints = {1, 2, null, 3, 4};

        Integer [] ints2 = {};
        /*
        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>(str);
        System.out.println("Antall : "+liste.antall()+". Er tabellen tom? "+liste.tom());

        DobbeltLenketListe<Integer> intliste = new DobbeltLenketListe<>(ints);
        System.out.println("Antall : "+intliste.antall()+". Er tabellen tom? "+intliste.tom());
        DobbeltLenketListe<Integer> intliste2 = new DobbeltLenketListe<>(ints2);
        System.out.println("Antall : "+intliste2.antall()+". Er tabellen tom? "+intliste2.tom());

        System.out.println(liste.hent(1));

        System.out.println(liste.oppdater(0, "halla"));
        System.out.println(liste.toString());
        */

        Character[] c = { 'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J' ,};
        DobbeltLenketListe<Character> listechar = new DobbeltLenketListe<>(c);
        System.out.println(listechar);
        System.out.println(listechar.hent(2));

        System.out.println(listechar.hent(3));

        System.out.println(listechar.hent(4));
        System.out.println(listechar.hent(5));
        System.out.println(listechar.hent(6));
        System.out.println(listechar.hent(7));
        System.out.println(listechar.hent(8));



        System. out .println(listechar.subliste(3,8)); // [D, E, F, G, H]
        //System. out .println(listechar.subliste(5,5)); // []
        //System. out .println(listechar.subliste(8,listechar.antall())); // [I, J]
        // System.out.println(liste.subliste(0,11)); // skal kaste unntak

        //Integer newint = intliste2.hent(0);
        //System.out.println(newint);
    }
}
