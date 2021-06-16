import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * ueb 08 - Klasse zum anlegen eines Lagers
 * 
 * @author Simon Klasen & Andreas Scheuer
 * @version 0.1
 */

public class Lager {

    // Private Attribute
    public Artikel[] lager;
    private int lagerbestand = 0;

    // Klassenkonstante
    private final static String LAGERNICHTNULL = "Lager muss größer als 0 sein.";
    private final static String LAGERVOLLMELDUNG = "Lager Voll!";
    private final static String ARTIKELEXISTENT = "Artikelnummer existiert schon!";
    private final static String ARTIKELNICHTEXISTENT = "Artikelnummer existiert nicht!";
    private final static String LAGERISTLEER = "Lager ist leer! Bitte erst füllen";
    private final static String LAGERPLATZLEER = "Der Platz im Lager ist leer";
    private final static String ARTIKELNICHTVORHANDEN = "Artikel nicht vorhanden";

    /**
     * Lager Konstruktor mit 1 Parametern
     *
     * @param menge Lagergroesse muss größer 0 sein
     */

    public Lager(int menge) {
        Artikel.check(menge > 0, LAGERNICHTNULL);
        this.lager = new Artikel[menge];
    }

    /**
     * Parameterloser Lager Konstruktor der eine groesse von 10 besitzt
     * 
     */

    public Lager() {
        this(10);
    }

    /**
     * Methode zum Anlegen eines Artikels im Lager
     * 
     * ueberprueft ob das lager voll ist mittels check methode und erhöht den
     * lagerbestand um 1
     */

    public void legeAnArtikel(Artikel artikel) {
        Artikel.check(lagerbestand < lager.length, LAGERVOLLMELDUNG);

        Artikel.check(existArtikelNr(artikel.getArtikelNr()) == false, ARTIKELEXISTENT);
        lager[lagerbestand] = artikel;
        lagerbestand++;
    }

    /**
     * Methode zum entfernen eines Artikels im Lager
     * 
     * ueberprueft ob das lager leer ist mittels und ob der artikel vorhanden
     * mittels check methode und verringert den lagerbestand um 1
     * 
     * @param artikelNr wird genutzt ob der artikel vorhanden ist
     */

    public void entferneArtikel(int artikelNr) {
        Artikel.check(existArtikelNr(artikelNr) == true, ARTIKELNICHTEXISTENT);
        for (int i = 0; i < lagerbestand; i++) {
            if (lager[i] != null && lager[i].getArtikelNr() == artikelNr) {
                lager[i] = lager[lagerbestand - 1];
                lager[lagerbestand - 1] = null;
                lagerbestand--;
            }
        }
    }

    /**
     * Artikelbestand vergrößern
     *
     * @param menge muss >= 0 sein
     * @return bestand wird gemäß menge erhöht
     */

    public void bucheZugang(int artikelNr, int menge) {

        Artikel.check(findArtikel(artikelNr) != null, ARTIKELNICHTEXISTENT);
        Artikel artikel = findArtikel(artikelNr);

        artikel.bucheZugang(menge);
    }

    /**
     * Artikelbestand verringern
     *
     * @param menge muss >= 0 sein
     * @return bestand darf nicht kleiner 0 werden
     * 
     */

    public void bucheAbgang(int artikelNr, int menge) {

        Artikel.check(findArtikel(artikelNr) != null, ARTIKELNICHTEXISTENT);
        Artikel artikel = findArtikel(artikelNr);

        artikel.bucheAbgang(menge);

    }

    /**
     * Preis aller Artikel wird um einen Prozentsatzn veaendert
     * 
     */

    public void aenderePreisAllerArtikel(double prozent) {
        Artikel.check(lagerbestand > 0, LAGERISTLEER);
        for (int i = 0; i < lagerbestand; i++) {
            double result;

            double aenderung = ((lager[i].getPreis() * prozent) / 100.0);
            result = (lager[i].getPreis() + aenderung);

            result = Math.round(result * 100.0) / 100.0;

            lager[i].setPreis(result);

        }
    }

    // Get Methode für den Artikel auf einem bestimmten Index

    public Artikel getArtikel(int index) {
        Artikel.check(lager[index] != null, LAGERPLATZLEER);
        return lager[index];
    }

    // Get Methode für die Lagergroesse

    public int getLagerGroesse() {
        return lager.length;
    }

    // Get Methode für den Artikelpreis

    public double getArtikelPreis(int index) {
        Artikel.check(lager[index] != null, ARTIKELNICHTVORHANDEN);
        return lager[index].getPreis();
    }

    /**
     * Findet einen Artikel im Lager anhand seiner Artikelnummer
     * 
     */

    public Artikel findArtikel(int artikelNr) {
        for (int i = 0; i < lagerbestand; i++) {
            if (lager[i].getArtikelNr() == artikelNr) {
                return lager[i];

            }
        }
        return null;
    }

    /**
     * Gibt die Bestandsliste aus nach dem vorgegebenen Format
     * 
     */

    public void ausgebenBestandsListe() {

        String artnr = "ArtNr";
        String beschreibung = "Beschreibung";
        String preis = "Preis";
        String bestand = "Bestand";
        String gesamt = "Gesamt";
        String trennlinie = "----------------------------------------------------------------------------------------------------------------------------------";
        String gesamtwert = "Gesamtwert:";
        float summe = 0;

        System.out.printf("%-8s%-70s%12s%13s%17s", artnr, beschreibung, preis, bestand, gesamt);
        System.out.println();
        System.out.println(trennlinie);

        for (int i = 0; i < lagerbestand; i++) {
            if (lager[i] != null) {
                System.out.printf("%-8d%-70s%12.2f%13d%17.2f", lager[i].getArtikelNr(), lager[i].getBeschreibung(),
                        lager[i].getPreis(), lager[i].getBestand(), lager[i].getPreis() * lager[i].getBestand());
                System.out.print("\n");

                summe += lager[i].getPreis() * lager[i].getBestand();
            } else {
                break;
            }
        }

        System.out.println(trennlinie);
        System.out.printf("%-103s%17.2f", gesamtwert, summe);

    }

    public int getLagerbestand() {
        return lagerbestand;
    }

    public String toString() {

        String output = "";

        for (int i = 0; i < lagerbestand; i++) {

            output += lager[i].toString();
            output += "\n\n";
        }
        return output;
    }

    /**
     * Ueberprueft ob der Artikel bereits im Lager existiert anhand der Artikelnr.
     * 
     */

    public boolean existArtikelNr(int artikelNr) {
        for (int i = 0; i < lagerbestand; i++) {
            if (lager[i].getArtikelNr() == artikelNr) {
                return true;
            }
        }
        return false;
    }

    // schrott muss neu gemacht werden!

    // applytoarticles

    public void applyToSomeArticles(Artikel[] array, Predicate<? super Artikel> predicate, Consumer<?> consumer) {

        ArrayList<Artikel> liste = new ArrayList<Artikel>();

        for (Artikel artikel : this.lager) {
            liste.add(artikel);
        }

        liste.stream().filter(predicate).forEach((item) -> consumer.accept(item));

        this.lager = (Artikel[]) liste.toArray();
    }
}
