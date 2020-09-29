package no.oslomet.cs.algdat;

public class Main {
    public static void main(String[] args) {

        String [] str = {};


        Liste<String> liste = new DobbeltLenketListe<>(str);
        System.out.println("Antall : "+liste.antall()+". Er tabellen tom? "+liste.tom());
    }
}
