package no.oslomet.cs.algdat;

public class Main {
    public static void main(String[] args) {

        String [] str = {"hei", null, "paa", "deg",null};

        Integer [] ints = {1, 2, null, 3, 4};

        Integer [] ints2 = {};

        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};

        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>(str);
        System.out.println("Antall : "+liste.antall()+". Er tabellen tom? "+liste.tom());

        DobbeltLenketListe<Integer> intliste = new DobbeltLenketListe<>(ints);
        System.out.println("Antall : "+intliste.antall()+". Er tabellen tom? "+intliste.tom());
        DobbeltLenketListe<Integer> intliste2 = new DobbeltLenketListe<>(ints2);
        System.out.println("Antall : "+intliste2.antall()+". Er tabellen tom? "+intliste2.tom());

        Liste<String> liste2 = new DobbeltLenketListe<>(navn);
        DobbeltLenketListe<String> liste3 =
                new DobbeltLenketListe<>(new String[] {"Birger","Lars","Anders","Bodil","Kari","Per","Berit"});

        System.out.println(intliste2.toString());

        intliste2.leggInn(9);
        intliste2.leggInn(6);
        //System.out.println("Antall : "+intliste2.antall()+". Er tabellen tom? "+intliste2.tom());
        //System.out.println(intliste2.toString());
        System.out.println(liste.toString());
        System.out.println(intliste.toString());
        System.out.println(intliste2.omvendtString());
        System.out.println(liste.omvendtString());

        //Integer newint = intliste2.hent(0);
        //System.out.println(newint);

        liste2.forEach(s -> System.out.print(s + " "));
        System.out.println();
        for (String s : liste2) {
            System.out.print(s + " ");
        }

        System.out.println();
        liste3.fjernHvis(navn1 -> navn1.charAt(0) == 'B'); // fjerner navn som starter på B

        System.out.println(liste3 + " " + liste3.omvendtString());
    }
}
