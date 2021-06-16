public class CD extends Artikel {

    // Private Attribute
    private String interpret;
    private String titel;
    private int anzahlTitel;
    // Klassenkonstante
    private final static String INTERPRETLEER = "Interpret darf nicht leer sein";
    private final static String TITELLEER = "Titel darf nicht leer sein";
    private final static String ANZAHLTITELLEER = "Anzahl Titel darf nicht weniger als 0 betragen";

    /**
     * Buch Konstruktor mit 7 Parametern
     *
     * @param artikelNr   muss eine 4-stellige positive Artikelnummer
     * @param art         darf nicht leer sein, unnötige Leerstellen werden entfernt
     * @param bestand     muss >= 0 sein
     * @param preis       muss >= 0 sein
     * @param interpret   darf nicht leer sein, unnötige Leerstellen werden entfernt
     * @param titel       darf nicht leer sein, unnötige Leerstellen werden entfernt
     * @param anzahlTitel muss > 0 sein
     */

    public CD(int artikelNr, int bestand, double preis, String interpret, String titel, int anzahlTitel) {

        super(artikelNr, "Medien", bestand, preis);

        check(interpret != null && !interpret.isBlank(), INTERPRETLEER);
        {
            this.interpret = trimArt(interpret);
        }

        check(titel != null && !titel.isBlank(), TITELLEER);
        {
            this.titel = trimArt(titel);
        }

        check(anzahlTitel > 0, ANZAHLTITELLEER);
        {
            this.anzahlTitel = anzahlTitel;
        }
    }

    // Get Methode für interpret

    public String getInterpret() {
        return interpret;
    }

    // Get Methode für titel

    public String getTitel() {
        return titel;
    }

    // Get Methode für anzahlTitel

    public int getAnzahlTitel() {
        return anzahlTitel;
    }

    // Methode zur ausgabe von Interpet und Titel

    @Override
    public String getBeschreibung() {
        return interpret + " : " + titel;

    }

    // toString methode zur ausgabe der ausgabeBestands Methode

    public String toString() {
        return "Typ: CD" + "\n" + "Nummer: " + super.getArtikelNr() + "\nBezeichnung: " + super.getArt()
                + "\nInterpret: " + interpret + "\nTitel: " + titel + "\nAnzahl Titel: " + anzahlTitel + "\nBestand: "
                + super.getBestand() + "\nPreis: " + super.getPreis();
    }

}
