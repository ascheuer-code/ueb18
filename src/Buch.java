public class Buch extends Artikel {

    // Private Attribute
    private String autor;
    private String titel;
    private String verlag;
    // Klassenkonstante
    private final static String AUTORLEER = "Autor darf nicht leer sein";
    private final static String TITELLEER = "Titel darf nicht leer sein";
    private final static String VERLAGLEER = "Verlag darf nicht leer sein";

    /**
     * Buch Konstruktor mit 7 Parametern
     *
     * @param artikelNr muss eine 4-stellige positive Artikelnummer
     * @param art       darf nicht leer sein, unnötige Leerstellen werden entfernt
     * @param bestand   muss >= 0 sein
     * @param preis     muss >= 0 sein
     * @param autor     darf nicht leer sein, unnötige Leerstellen werden entfernt
     * @param titel     darf nicht leer sein, unnötige Leerstellen werden entfernt
     * @param verlag    darf nicht leer sein, unnötige Leerstellen werden entfernt
     */

    public Buch(int artikelNr, int bestand, double preis, String autor, String titel, String verlag) {
        super(artikelNr, "Medien", bestand, preis);

        check(autor != null && !autor.isBlank(), AUTORLEER);
        {
            this.autor = trimArt(autor);
        }

        check(titel != null && !titel.isBlank(), TITELLEER);
        {
            this.titel = trimArt(titel);
        }

        check(verlag != null && !verlag.isBlank(), VERLAGLEER);
        {
            this.verlag = trimArt(verlag);
        }
    }

    // Get Methode für autor

    public String getAutor() {
        return autor;
    }

    // Get Methode für titel

    public String getTitel() {
        return titel;
    }

    // Get Methode für verlag

    public String getVerlag() {
        return verlag;
    }

    // Methode zur ausgabe der Artikelbeschreibung

    @Override
    public String getBeschreibung() {
        return autor + " : " + titel;
    }

    // toString methode zur ausgabe der ausgabeBestands Methode

    public String toString() {
        return "Typ: Buch" + "\n" + "Nummer: " + super.getArtikelNr() + "\nBezeichnung: " + super.getArt() + "\nTitel: "
                + titel + "\nAuthor: " + autor + "\nVerlag: " + verlag + "\nBestand: " + super.getBestand()
                + "\nPreis: " + super.getPreis();
    }
}
