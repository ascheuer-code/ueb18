import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class ArtikelTest {

    @Test
    // Artikel Erstellen Test
    public void testerstelleArtikel() {
        Artikel artikel = new Artikel(1111, "Birne", 12.20);
        assertEquals(1111, artikel.getArtikelNr());
        assertEquals("Birne", artikel.getArt());
        assertEquals(0, artikel.getBestand());
        assertEquals(12.20, artikel.getPreis());

        CD artikel2 = new CD(4567, 15, 12.20, "TestInterpret", "TestTitel", 10);
        assertEquals(4567, artikel2.getArtikelNr());
        assertEquals("CD", artikel2.getArt());
        assertEquals(15, artikel2.getBestand());
        assertEquals(12.20, artikel2.getPreis());
        assertEquals("TestInterpret", artikel2.getInterpret());
        assertEquals("TestTitel", artikel2.getTitel());
        assertEquals(10, artikel2.getAnzahlTitel());

        Buch artikel3 = new Buch(3246, 24, 15.20, "TestAutor", "TestTitel", "TestVerlag");
        assertEquals(3246, artikel3.getArtikelNr());
        assertEquals("Buch", artikel3.getArt());
        assertEquals(24, artikel3.getBestand());
        assertEquals(15.20, artikel3.getPreis());
        assertEquals("TestAutor", artikel3.getAutor());
        assertEquals("TestTitel", artikel3.getTitel());
        assertEquals("TestVerlag", artikel3.getVerlag());

        Video artikel4 = new Video(6666, 34, 50, "TestTitel", 120, 1999);
        assertEquals(6666, artikel4.getArtikelNr());
        assertEquals("Video", artikel4.getArt());
        assertEquals(34, artikel4.getBestand());
        assertEquals(50, artikel4.getPreis());
        assertEquals("TestTitel", artikel4.getTitel());
        assertEquals(120, artikel4.getSpieldauer());
        assertEquals(1999, artikel4.getJahr());

    }

    @Test
    // Buche zugang Test
    public void testBucheZugang() {
        Artikel artikel1 = new Artikel(1234, "Apfel", 15, 12.20);
        Video artikel4 = new Video(6666, 34, 50, "TestTitel", 120, 1999);

        ThrowingRunnable throwingRunnable = new ThrowingRunnable() {

            @Override
            public void run() throws Throwable {
                artikel1.bucheAbgang(-1);
            }
        };

        assertThrows(IllegalArgumentException.class, throwingRunnable);

        assertThrows(IllegalArgumentException.class, () -> {
            artikel4.bucheAbgang(-1);
        });

        artikel1.bucheZugang(5);
        assertEquals(20, artikel1.getBestand());
        artikel1.bucheZugang(5);
        assertEquals(25, artikel1.getBestand());
        artikel4.bucheZugang(5);
        assertEquals(39, artikel4.getBestand());

    }

    @Test
    // Buche Abgang Test
    public void testBucheAbgang() {
        Artikel artikel1 = new Artikel(1234, "Apfel", 15, 12.20);
        assertThrows(IllegalArgumentException.class, () -> {
            artikel1.bucheZugang(-1);
        });
        artikel1.bucheAbgang(5);
        assertEquals(10, artikel1.getBestand());
        artikel1.bucheAbgang(5);
        assertEquals(5, artikel1.getBestand());
        artikel1.bucheAbgang(5);
        assertEquals(0, artikel1.getBestand());

        assertThrows(IllegalArgumentException.class, () -> {
            artikel1.bucheAbgang(1);
        });
    }

    @Test
    // Artikel Nummer aendern Test
    public void testsetArtikelNr() {
        Artikel artikel1 = new Artikel(1234, "Apfel", 15, 12.20);
        assertThrows(IllegalArgumentException.class, () -> {
            artikel1.setArtikelNr(111);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            artikel1.setArtikelNr(1112987);
        });

        artikel1.setArtikelNr(1234);
        assertEquals(1234, artikel1.getArtikelNr());
        artikel1.setArtikelNr(4567);
        assertEquals(4567, artikel1.getArtikelNr());
    }

    @Test
    // Artikelbezeichnung aendern Test
    public void testsetArt() {
        Artikel artikel1 = new Artikel(1234, "Apfel", 15, 12.20);

        artikel1.setArt("   Apfel   Birne   ");
        assertEquals("Apfel Birne", artikel1.getArt());

        assertThrows(IllegalArgumentException.class, () -> {
            artikel1.setArt("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            artikel1.setArt("    ");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            artikel1.setArt(null);
        });
    }

    @Test
    // Artikel Preis aendern Test
    public void testsetPreis() {
        Artikel artikel1 = new Artikel(1234, "Apfel", 15, 12.20);

        artikel1.setPreis(12);
        assertEquals(12, artikel1.getPreis());
        assertThrows(IllegalArgumentException.class, () -> {
            artikel1.setPreis(-5);
        });

    }

    @Test
    // Artikel Ausgabe Test
    public void testtoString() {
        Artikel artikel1 = new Artikel(1234, "Apfel", 15, 12.20);

        assertEquals("Nummer: " + "1234" + "\nBezeichnung: " + "Apfel" + "\nBestand: " + "15" + "\nPreis: " + "12.2",
                artikel1.toString());
    }

}
