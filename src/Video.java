public class Video extends Artikel {

    // Private Attribute
    private String titel;
    private int spieldauer;
    private int jahr;
    // Klassenkonstante
    private final int MINJAHR = 1950;
    private final int MAXJAHR = 2020;
    private final static String TITELLEER = "Titel darf nicht leer sein";
    private final static String SPIELDAUERLEER = "Spieldauer muss größer null sein";
    private final static String JAHRFALSCH = "Jahr muss zwischen 1950 und 2020 liegen";

    /**
     * Buch Konstruktor mit 7 Parametern
     *
     * @param artikelNr  muss eine 4-stellige positive Artikelnummer
     * @param art        darf nicht leer sein, unnötige Leerstellen werden entfernt
     * @param bestand    muss >= 0 sein
     * @param preis      muss >= 0 sein
     * @param titel      darf nicht leer sein, unnötige Leerstellen werden entfernt
     * @param spieldauer muss > 0 sein
     * @param Jahr       muss zwischen 1950 und 2020 liegen.
     */

    public Video(int artikelNr, int bestand, double preis, String titel, int spieldauer, int jahr) {
        super(artikelNr, "Medien", bestand, preis);

        check(titel != null && !titel.isBlank(), TITELLEER);
        {
            this.titel = trimArt(titel);
        }

        check(spieldauer > 0, SPIELDAUERLEER);
        {
            this.spieldauer = spieldauer;
        }

        check(jahr >= MINJAHR && jahr <= MAXJAHR, JAHRFALSCH);
        {
            this.jahr = jahr;
        }
    }

    // Get Methode für titel

    public String getTitel() {
        return titel;
    }

    // Get Methode für spieldauer

    public int getSpieldauer() {
        return spieldauer;
    }

    // Get Methode für jahr

    public int getJahr() {
        return jahr;
    }

    // Methode zur ausgabe der Artikelbeschreibung

    @Override
    public String getBeschreibung() {
        return titel;
    }

    // toString methode zur ausgabe der ausgabeBestands Methode

    public String toString() {
        return "Typ: Video" + "\n" + "Nummer: " + super.getArtikelNr() + "\nBezeichnung: " + super.getArt()
                + "\nTitel: " + titel + "\nSpieldauer: " + spieldauer + "\nJahr: " + jahr + "\nBestand: "
                + super.getBestand() + "\nPreis: " + super.getPreis();
    }
}
