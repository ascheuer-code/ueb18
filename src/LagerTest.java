
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class LagerTest {
    public static final int LAGERVOLL = -1;

    @Test
    public void testLagerTest() {

        // Konstanten für den Test
        Artikel[] lager = new Artikel[4];
        Lager lagertest = new Lager(4);
        // Lege Artikel an
        Artikel artikel = new Artikel(1234, "Apfel", 1, 1);
        // Lege CD an
        CD artikel1 = new CD(5555, 1, 1, "Testinterpret", "Testtitel", 1);
        // Lege Video an
        Video artikel2 = new Video(4444, 1, 1, "TestTitel", 100, 2000);
        // Lege Buch an
        Buch artikel3 = new Buch(3214, 1, 1, "Testautor", "TestTitel", "Testverlag");

        // LegeAnArtikel
        assertEquals(0, lagertest.getLagerbestand());
        assertFalse(lagertest.existArtikelNr(1234));

        lagertest.legeAnArtikel(artikel);
        lagertest.legeAnArtikel(artikel1);
        lagertest.legeAnArtikel(artikel2);
        lagertest.legeAnArtikel(artikel3);

        // Lagerbestand
        assertEquals(4, lagertest.getLagerbestand());
        assertTrue(lagertest.existArtikelNr(1234));

        assertThrows(IllegalArgumentException.class, () -> {
            lagertest.legeAnArtikel(artikel);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            lagertest.legeAnArtikel(artikel2);
        });

        // getArtikelAnzahl
        assertEquals(4, lagertest.getLagerbestand());

        // Entferne Artikel
        lagertest.entferneArtikel(1234);

        assertEquals(artikel3, lagertest.getArtikel(0));
        assertThrows(IllegalArgumentException.class, () -> {
            lagertest.entferneArtikel(6666);
        });

        // get ArtikelAnzahl
        assertEquals(3, lagertest.getLagerbestand());

        // Buche Zugang
        lagertest.bucheZugang(5555, 10);

        assertEquals(11, artikel1.getBestand());
        assertThrows(IllegalArgumentException.class, () -> {
            lagertest.bucheZugang(5554, 1);
        });

        // Buche Abgang
        lagertest.bucheAbgang(5555, 10);

        assertEquals(1, artikel1.getBestand());
        assertThrows(IllegalArgumentException.class, () -> {
            lagertest.bucheAbgang(5554, 1);
        });

        // aenderePreisAllertArtikel
        lagertest.legeAnArtikel(artikel);

        lagertest.aenderePreisAllerArtikel(100);

        assertEquals(2, artikel.getPreis());
        assertEquals(2, artikel1.getPreis());

        lagertest.aenderePreisAllerArtikel(0.5);

        assertEquals(2.01, artikel.getPreis());
        assertEquals(2.01, artikel1.getPreis());

        // getArtikel
        assertEquals(artikel3, lagertest.getArtikel(0));
        assertThrows(Throwable.class, () -> {
            lagertest.getArtikel(5554);
        });

        // getLagerGroesse
        assertEquals(4, lager.length);

        // findArtikel
        assertEquals(artikel, lagertest.findArtikel(1234));

    }

}
