package no.oslomet.cs.algdat;

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

        System.out.println(intliste2.toString());

        intliste2.leggInn(9);
        intliste2.leggInn(6);
        //System.out.println("Antall : "+intliste2.antall()+". Er tabellen tom? "+intliste2.tom());
        //System.out.println(intliste2.toString());
        System.out.println(liste.toString());
        System.out.println(intliste.toString());
        System.out.println(intliste.omvendtString());

        //Integer newint = intliste2.hent(0);
        //System.out.println(newint);
    }
}
