/**
 * ueb 08 - Artikelklasse zur einfachen Bestandsaufnahme
 * 
 * @author Simon Klasen & Andreas Scheuer
 * @version 0.1
 */

public class Artikel {

    // Private Attribute
    private int artikelNr;
    private String art;
    private int bestand;
    private double preis;

    // Klassenkonstante
    private final static double EPSILON = 1E-12;
    private final static String ARTIKELNRFALSCH = "Die Artikelnummer muss 4 Ziffern lang sein";
    private final static String ARTIKELBEZEICHNERFALSCH = "Die Artikelbezeichnung darf nicht leer sein";
    private final static String BESTANDFALSCH = "Bestand darf nicht niedriger als 0 sein";
    private final static String PREISFALSCH = "Der Preis darf nicht weniger wie 0 betragen";
    private final static String PREISNACHKOMMA = "Der Preis darf nur 2 Nachkommastellen haben";
    private final static String FALSCHEBUCHUNG = "Die Menge darf nicht weniger als 0 betragen";
    private static final int ARTIKELMIN = 1000;
    private static final int ARTIKELMAX = 9999;

    /**
     * Artikel Konstruktor mit 4 Parametern
     *
     * @param artikelNr muss eine 4-stellige positive Artikelnummer
     * @param art       darf nicht leer sein, unnötige Leerstellen werden entfernt
     * @param bestand   muss >= 0 sein
     * @param preis     muss >= 0 sein
     */

    public Artikel(int artikelNr, String art, int bestand, double preis) {

        check(artikelNr >= ARTIKELMIN && artikelNr <= ARTIKELMAX, ARTIKELNRFALSCH);
        {
            this.artikelNr = artikelNr;
        }

        check(art != null && !art.isBlank(), ARTIKELBEZEICHNERFALSCH);
        {
            this.art = trimArt(art);
        }

        check(bestand >= 0, BESTANDFALSCH);
        {
            this.bestand = bestand;
        }

        check(preis >= 0, PREISFALSCH);
        {
            double rechnung = (preis * 100.0) % 1.0;
            check(rechnung == 0 || rechnung < 1 && rechnung > 1 - EPSILON, PREISNACHKOMMA);
            {
                this.preis = preis;

            }
        }
    }

    /**
     * Artikel Konstruktor mit 3 Parametern
     *
     * @param artikelNr muss eine 4-stellige positive Artikelnummer
     * @param art       darf nicht leer sein, unnötige Leerstellen werden entfernt
     * 
     */

    public Artikel(int artikelNr, String art, double preis) {
        this(artikelNr, art, 0, preis);
    }

    /**
     * Artikelbestand vergrößern
     *
     * @param menge muss >= 0 sein
     * @return bestand wird gemäß menge erhöht
     */

    public int bucheZugang(int menge) {
        check(menge >= 0, FALSCHEBUCHUNG);
        {
            return bestand += menge;
        }
    }

    /**
     * Artikelbestand verringern
     *
     * @param menge muss >= 0 sein
     * @return bestand darf nicht kleiner 0 werden
     * 
     */

    public int bucheAbgang(int menge) {
        check(menge >= 0, FALSCHEBUCHUNG);
        {
            check(bestand - menge >= 0, BESTANDFALSCH);
            {
                return bestand -= menge;
            }
        }
    }

    /**
     * Artikelnummer abrufen
     *
     * @return artikelNr, Artikelnummer des Produktes
     * 
     */

    public int getArtikelNr() {
        return artikelNr;
    }

    /**
     * Artikelbezeichnung abrufen
     *
     * @return art, Artikelbezeichnung des Produktes
     * 
     */

    public String getArt() {
        return art;
    }

    /**
     * Bestand abrufen
     *
     * @return bestand, Bestand des Produktes.
     * 
     */

    public int getBestand() {
        return bestand;
    }

    /**
     * Preis abrufen
     *
     * @return preis, Preis des Produktes.
     * 
     */

    public double getPreis() {
        return preis;
    }

    /**
     * Artikelnummer wird verändert
     *
     * @param artikelNr neue Zahl (muss 4-stellig und positiv sein)
     */

    public void setArtikelNr(int artikelNr) {
        check(artikelNr >= ARTIKELMIN && artikelNr <= ARTIKELMAX, ARTIKELNRFALSCH);
        this.artikelNr = artikelNr;
    }

    /**
     * Artikelbezeichnung wird verändert
     *
     * @param art neuer Wert (darf nicht leer sein unnötige Leerstellen werden
     *            entfernt)
     * 
     */

    public void setArt(String art) {
        check(art != null && !art.isBlank(), ARTIKELBEZEICHNERFALSCH);
        this.art = trimArt(art);
    }

    /**
     * Artikelpreis wird verändert
     *
     * @param preis muss groesser als 0 sein.
     * 
     */
    public void setPreis(double preis) {
        check(preis >= 0, PREISFALSCH);
        this.preis = preis;
    }

    public String toString() {
        return "Typ: Artikel" + "\n" + "Nummer: " + this.artikelNr + "\nBezeichnung: " + this.art + "\nBestand: "
                + this.bestand + "\nPreis: " + this.preis;
    }

    /**
     * Bedingungen werden abgefragt
     *
     * @param bedingung muss true oder false sein
     * @param message   gibt Fehlermeldung aus
     */
    
    public static void check(Boolean bedingung, String message) {
        if (!bedingung)
            throw new IllegalArgumentException(message);
    }

    /**
     * Methode zum entfernen nicht benötigter Leerstellen
     *
     * @param replaceALL (ersetze alle Variable 1 durch Variable 2, (Variable1 ,
     *                   Variable 2))
     * @param \s+        (alle zusammenhängende Leerstellen)
     */

    public String trimArt(String art) {
        art = art.replaceAll("\\s+", " ");
        art = art.trim();
        return art;
    }

    public String getBeschreibung() {
        return art;
    }
}
