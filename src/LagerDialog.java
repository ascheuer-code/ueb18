
/**
 * ueb 08 - Klasse zum Testen der Lagerklasse
 * 
 * @author Simon Klasen & Andreas Scheuer
 * @version 0.1
 */

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LagerDialog extends Lager {
    // Klassenkonstante
    private static final int ERSTELLEN = 1;
    private static final int ENTFERNEN = 2;
    private static final int ZUGANG = 3;
    private static final int ABGANG = 4;
    private static final int PREIS_ALLE_AENDERN = 5;
    private static final int ART_AUSGABE = 6;
    private static final int ALLE_AUSGABE = 7;
    private static final int TOSTRING = 8;
    private static final int ANZAHL_ARTIKEL = 9;
    private static final int LAGER_GROESSE = 10;
    private static final int ENDE = 0;
    // Klassenkonstante
    private static final int ARTIKEL = 1;
    private static final int CD = 2;
    private static final int VIDEO = 3;
    private static final int BUCH = 4;
    // Klassenkonstante
    private static final String OPTIONSFEHLER = "Falsche Option";

    private Scanner input;
    Lager lager;

    public LagerDialog() {
        input = new Scanner(System.in);
    }

    public static void main(String[] arcs) {
        new LagerDialog().start();
    }

    /**
     * Eigentliche Startmethode
     */

    public void start() {
        int option = -1;

        lager = initLager();

        while (option != ENDE) {
            try {
                option = einlesenFunktion();
                ausfuehrenFunktion(option);
            } catch (IllegalArgumentException msg) {
                System.out.println("\n" + msg);
            } catch (InputMismatchException msg) {
                System.out.println("\n" + msg + ": Kein korrekter Wert");
                input.nextLine();
            } catch (Exception msg) {
                System.out.println("\n" + msg);
            }
        }
    }

    /**
     * Menü ausgabe und einlesen der Funktion
     * 
     * @return eingelesener Wert als Zahl
     */

    public int einlesenFunktion() {
        System.out.println("\n---------------------------------");
        System.out.println("Wählen Sie eine der folgenden Optionen: " + "\n" + ERSTELLEN + ": Artikel erstellen  "
                + "\n" + ENTFERNEN + ": Artikel entfernen" + "\n" + ZUGANG + ": Zugang buchen" + "\n" + ABGANG
                + ": Abgang buchen" + "\n" + PREIS_ALLE_AENDERN + ": Preis aller Artikel ändern" + "\n" + ART_AUSGABE
                + ": Einen bestimmten Artikel anzeigen" + "\n" + ALLE_AUSGABE + ": Alle Artikel anzeigen" + "\n"
                + TOSTRING + ": toString Methode des Lagers aufrufen" + "\n" + +ANZAHL_ARTIKEL
                + ": Anzahl aller Artikel" + "\n" + LAGER_GROESSE + ": Größe des Lagers" + "\n" + ENDE
                + ": Programm beenden");
        System.out.println("---------------------------------");
        System.out.print("\nOption: ");

        return input.nextInt();
    }

    public int einlesenTyp() {
        System.out.println("\n---------------------------------");
        System.out.println("Welchen Typ wollen Sie erstellen?" + "\n" + ARTIKEL + ": Artikel" + "\n" + CD + ": CD"
                + "\n" + VIDEO + ": Video" + "\n" + BUCH + ": Buch");
        System.out.println("---------------------------------");
        System.out.print("\nOption: ");

        return input.nextInt();
    }

    /**
     * Ausführung der Funktion entsprechend des Returnwert
     * 
     * @param option        return wert der einlesenFunktion()
     * @param artikelnummer Artikelnummer des Produktes
     * @param artikelart    Artikelbezeichnung des Produktes
     * @param betrag        Bestand des Produktes
     */

    public void ausfuehrenFunktion(int option) {

        // Artikel
        int artikelnummer;
        String artikelart;
        int betrag;
        double prozent;
        double preis;
        int index;

        // CD
        String interpret;
        String titel;
        int anzahlTitel;

        // Video
        int spieldauer;
        int jahr;

        // Buch
        String autor;
        String verlag;

        /**
         * Erstellen eines Produktes mit Bestand
         */

        if (option == ERSTELLEN) {
            int typ = einlesenTyp();
            Artikel.check(typ > 0 && typ < 5, OPTIONSFEHLER);

            System.out.println();
            System.out.println("Neuer Artikel:");

            System.out.print("Artikelnummer: ");
            artikelnummer = input.nextInt();
            input.nextLine();

            System.out.print("Bestand: ");
            betrag = input.nextInt();

            System.out.print("Preis: ");
            preis = input.nextDouble();

            /**
             * Erstellen eines Artikels
             */

            if (typ == ARTIKEL) {
                input.nextLine();
                System.out.print("Artikelbezeichnung: ");
                artikelart = input.nextLine();
                Artikel artikel = new Artikel(artikelnummer, artikelart, betrag, preis);
                lager.legeAnArtikel(artikel);

                System.out.println("\n" + artikelart + " wurde erstellt.");
            }

            /**
             * Erstellen einer CD
             */

            else if (typ == CD) {
                input.nextLine();
                System.out.print("Interpret: ");
                interpret = input.nextLine();

                System.out.print("Titel: ");
                titel = input.nextLine();

                System.out.print("Anzahl Titel: ");
                anzahlTitel = input.nextInt();

                CD cd = new CD(artikelnummer, betrag, preis, interpret, titel, anzahlTitel);
                lager.legeAnArtikel(cd);

                System.out.println("\nCD: " + cd.getBeschreibung() + " wurde erstellt.");
            }

            /**
             * Erstellen eines Videos
             */

            else if (typ == VIDEO) {
                input.nextLine();
                System.out.print("Titel: ");
                titel = input.nextLine();

                System.out.print("Spieldauer: ");
                spieldauer = input.nextInt();

                System.out.print("Jahr: ");
                jahr = input.nextInt();

                Video video = new Video(artikelnummer, betrag, preis, titel, spieldauer, jahr);
                lager.legeAnArtikel(video);

                System.out.println("\nVideo: " + video.getBeschreibung() + " wurde erstellt");
            }

            /**
             * Erstellen eines Buches
             */

            else if (typ == BUCH) {
                input.nextLine();
                System.out.print("Autor: ");
                autor = input.nextLine();

                System.out.print("Titel: ");
                titel = input.nextLine();

                System.out.print("Verlag: ");
                verlag = input.nextLine();

                Buch buch = new Buch(artikelnummer, betrag, preis, autor, titel, verlag);
                lager.legeAnArtikel(buch);

                System.out.println("\nBuch: " + buch.getBeschreibung() + " wurde erstellt");

            }
        }

        /**
         * Entfernen eines Produktes
         */

        else if (option == ENTFERNEN) {
            System.out.println();
            System.out.println("Artikel löschen:");

            System.out.print("Artikelnummer: ");
            artikelnummer = input.nextInt();

            lager.entferneArtikel(artikelnummer);

            System.out.println("\n Artikel wurde entfernt!");
        }

        /**
         * Artikelbestand vergrößern
         *
         * menge muss >= 0 sein
         */

        else if (option == ZUGANG) {
            System.out.println();
            System.out.print("Artikelnummer: ");
            artikelnummer = input.nextInt();
            System.out.print("Betrag: ");
            betrag = input.nextInt();

            lager.bucheZugang(artikelnummer, betrag);

            System.out.println("\n  Artikelbestand von Artikel: " + artikelnummer + " wurde um " + betrag + " erhöht.");
        }

        /**
         * Artikelbestand verringern
         *
         * menge muss >= 0 sein bestand darf nicht kleiner 0 werden
         */

        else if (option == ABGANG) {
            System.out.println();
            System.out.print("Artikelnummer: ");
            artikelnummer = input.nextInt();
            System.out.print("Betrag: ");
            betrag = input.nextInt();

            lager.bucheAbgang(artikelnummer, betrag);

            System.out.println(
                    "\n  Artikelbestand von Artikel: " + artikelnummer + " wurde um " + betrag + " verringert.");
        }

        /**
         * Preis alller Artikel wird geaendert
         * 
         */

        else if (option == PREIS_ALLE_AENDERN) {
            System.out.println();
            System.out.println("Preis aller Artikel ändern: (positiv für Preiserhöhung, negativ für Preissenkung)");
            System.out.print("Prozent: ");
            prozent = input.nextDouble();
            lager.aenderePreisAllerArtikel(prozent);
            if (prozent >= 0) {
                System.out.println("\n  Alle Preise wurden um " + prozent + "% erhöht.");
            } else {
                System.out.println("\n  Alle Preise wurden um " + prozent + "% verringert.");
            }
        }

        /**
         * Artikel an einem bestimmten Index wird ausgegeben
         */

        else if (option == ART_AUSGABE) {
            System.out.println();
            System.out.print("Artikel anzeigen an Index: ");
            index = input.nextInt();

            System.out.println();
            System.out.println(lager.getArtikel(index).toString());
        }

        /**
         * Ausgabe aller werte von Artikel
         *
         */

        else if (option == ALLE_AUSGABE) {

            System.out.println();
            lager.ausgebenBestandsListe();

            // Artikel[] test = (Artikel[]) lager.filer(lager.lager, a -> a instanceof CD);

            // for (Object art : test) {
            // System.out.println(art.toString());
            // }

            Ueb18Fassade object = new Ueb18Fassade();
            object.aufgabe_c_iii(lager);

        }

        else if (option == TOSTRING) {
            System.out.println();
            if (lager.toString().isEmpty()) {
                System.out.println("Lager ist leer");
            } else {
                System.out.println(lager.toString());
            }
        }

        else if (option == ANZAHL_ARTIKEL) {
            System.out.println();
            System.out.println("Anzahl aller Artikel: " + lager.getLagerbestand());
        }

        else if (option == LAGER_GROESSE) {
            System.out.println();
            System.out.println("Lagergröße: " + lager.getLagerGroesse());
        }

        /**
         * Beendet das Programm
         *
         */

        else if (option == ENDE) {
            System.out.println();
            System.out.println("Programmende!");
        }

        // Fehlermeldung bei falsche eingabe
        else {
            System.out.println();
            System.out.println("Falsche Option!");
        }
    }

    /**
     * Erstell das Lager
     * 
     * @param lagerGroesse muss größer 0 sein
     *
     */

    public Lager initLager() {
        int option;
        int lagerGroesse;

        System.out.println("\nLagergroesse waehlen: " + "\n");
        System.out.println("1: Groesse manuell waehlen" + "\n" + "2: Standardgroesse 10 waehlen");

        while (true) {
            try {
                System.out.print("\nOption: ");
                option = input.nextInt();

                if (option == 1) {
                    System.out.println();
                    System.out.print("Lagergroesse: ");
                    lagerGroesse = input.nextInt();
                    return new Lager(lagerGroesse);
                } else if (option == 2) {
                    return new Lager();
                } else {
                    System.out.println("Falsche Option");
                }
            } catch (IllegalArgumentException msg) {
                System.out.println("\n" + msg);
            } catch (InputMismatchException msg) {
                System.out.println("\n" + msg + ": Kein korrekter Wert");
                input.nextLine();
            } catch (Exception msg) {
                System.out.println("\n" + msg);
            }
        }
    }
}
