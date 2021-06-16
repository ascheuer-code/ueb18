import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * <p>
 * Diese Klasse ist eine Fassade, hinter der Sie Ihre Loesung verstecken. Der
 * Test ruft nur die hier definierten Schnittstellenmethoden auf. Loeschen Sie
 * bitte keine Methoden. Wenn Sie eine Methode nicht implementieren moechten,
 * lassen Sie bitte die leere Implementierung stehen. Innerhalb der Methoden und
 * in allen anderen Klassen, die Sie noch benoetigen, haben Sie alle Freiheiten.
 * </p>
 * 
 * <p>
 * Wenn Sie Ihre Loesung mit JUnit testen moechten, testen Sie diese
 * Schnittstellenmethoden.
 * </p>
 * 
 * @author christopher
 *
 */
public class Ueb18Fassade extends Lager {

	Scanner input = new Scanner(System.in);

	Comparator<Artikel> unterkategorie = Comparator.comparing(Artikel::getBeschreibung);
	Comparator<Artikel> bestand = Comparator.comparing(Artikel::getBestand);
	Comparator<Artikel> preis = Comparator.comparing(Artikel::getPreis);

	Consumer<Artikel> preisZehnProzentHoch = x -> x.setPreis(x.getPreis() * 1.1);
	Consumer<Artikel> addPrefix = x -> x.setArt(x.getBeschreibung() + "(Sonderangebot)");
	BiConsumer<Artikel, Artikel> prefixAndPrice = (x, y) -> {
		x.setPreis(x.getPreis() * 1.1);
		y.setArt(y.getBeschreibung() + "(Sonderangebot)");
	};

	/**
	 * Loest die Aufgabe (c) i. <br />
	 * Sortierung nach den folgenden Kriterien:
	 * <ol>
	 * <li>Unterkategorie (alphabetisch)</li>
	 * <li>Bestand</li>01
	 * <li>Preis</li>
	 * </ol>
	 * 
	 * @param lager Das Lager mit der unsortierten Artikelliste.
	 * @return Die sortierte Artikelliste.
	 */
	public Artikel[] aufgabe_c_i(Lager lager) {

		ArrayList<Artikel> liste = new ArrayList<Artikel>();

		System.out.println("1: Unterkategorie\n2: Bestand\n3: Preis");
		int option = input.nextInt();

		for (Artikel artikel : this.lager) {
			liste.add(artikel);
		}

		switch (option) {
			case 1:
				liste.sort(unterkategorie);
			case 2:
				liste.sort(bestand);
			case 3:
				liste.sort(preis);
			default:
				break;
		}

		return (Artikel[]) liste.toArray();

	}

	/**
	 * Loest die Aufgabe (c) ii. <br />
	 * Der Preis aller Artikel wird um 10% reduziert.
	 * 
	 * @param lager Das Lager mit den Artikeln, deren Preise geaendert werden
	 *              sollen.
	 */
	public void aufgabe_c_ii(Lager lager) {

		ArrayList<Artikel> liste = new ArrayList<Artikel>();

		for (Artikel artikel : this.lager) {
			liste.add(artikel);
		}

		liste.stream().forEach((item) -> preisZehnProzentHoch.accept(item));
	}

	/**
	 * Loest die Aufgabe (c) iii. <br />
	 * An alle Artikelbezeichnungen wird das Suffix (Sonderangebot) angehaengt.
	 * 
	 * @param lager Das Lager mit den Artikeln, deren Bezeichnungen geaendert werden
	 *              sollen.
	 */
	public void aufgabe_c_iii(Lager lager) {

		ArrayList<Artikel> liste = new ArrayList<Artikel>();

		for (Artikel artikel : this.lager) {
			liste.add(artikel);
		}

		liste.stream().forEach((item) -> addPrefix.accept(item));

		lager.lager = (Artikel[]) liste.toArray();
	}

	/**
	 * Loest die Aufgabe (c) iv. <br />
	 * Die beiden Operatoren aus den Aufgabenteilen ii und iii werden konkateniert,
	 * d.h. alle Preise werden um 10 % reduziert und alle Bezeichnungen werden um
	 * das Suffix (Sonderangebot) erweitert.
	 * 
	 * @param lager Das Lager mit den Artikeln, deren Preise und Bezeichnungen
	 *              geaendert werden sollen.
	 */
	public void aufgabe_c_iv(Lager lager) {

		ArrayList<Artikel> liste = new ArrayList<Artikel>();

		for (Artikel artikel : this.lager) {
			liste.add(artikel);
		}

		liste.stream().forEach((item) -> prefixAndPrice.accept(item, item));
	}

	/**
	 * Loest die Aufgabe (h) i. <br />
	 * Der Preis aller CDs wird um 10 % erhoeht.
	 * 
	 * @param lager Das Lager mit den Artikeln. Die Aenderungen werden direkt in
	 *              diesem Objekt vorgenommen.
	 */
	public void aufgabe_h_i(Lager lager) {
		lager.applyToSomeArticles(a -> a instanceof CD, a -> a.aenderePreis(10));
	}

	/**
	 * Loest die Aufgabe (h) ii. <br />
	 * Der Preis aller Artikel, von denen der Bestand hoechstes zwei ist, wird um 5
	 * % reduziert.
	 * 
	 * @param lager Das Lager mit den Artikeln. Die Aenderungen werden direkt in
	 *              diesem Objekt vorgenommen.
	 */
	public void aufgabe_h_ii(Lager lager) {
	}

	/**
	 * Loest die Aufgabe (h) iii. <br />
	 * Der Preis der Buecher eines bestimmten Autors wird um 5 % reduziert.
	 * 
	 * @param lager          Das Lager mit den Artikeln. Die Aenderungen werden
	 *                       direkt in diesem Objekt vorgenommen.
	 * @param gesuchterAutor Der Autor, dessen Buecher guenstiger werden sollen.
	 */
	public void aufgabe_h_iii(Lager lager, String gesuchterAutor) {
	}

	/**
	 * Loest die Aufgabe (h) iv. <br />
	 * Der Preis aller CDs wird um 10 % erhoeht und der Preis aller Artikel, von
	 * denen der Bestand hoechstes zwei ist, wird um 5 % reduziert.
	 * 
	 * @param lager Das Lager mit den Artikeln. Die Aenderungen werden direkt in
	 *              diesem Objekt vorgenommen.
	 */
	public void aufgabe_h_iv(Lager lager) {
	}

	/**
	 * Loest die Aufgabe (h) v. <br />
	 * 
	 * @param lager Das Lager mit den Artikeln.
	 * @return Eine Liste mit allen Buechern, sortiert nach den Namen der Autoren.
	 */
	public Artikel[] aufgabe_h_v(Lager lager) {
		return null;
	}

	/**
	 * Loest die Aufgabe (h) vi. <br />
	 * 
	 * @param lager          Das Lager, dessen Artikel gefiltert werden sollen.
	 * @param gesuchterAutor Der Autor, nach dem gefiltert werden soll.
	 * @param minPreis       Der kleinste Preis, den die zu filternden Buecher haben
	 *                       sollen.
	 * @param maxPreis       Der hoechste Preis, den die zu filternden Buecher haben
	 *                       sollen.
	 * @return Alle Buecher vom Autor autor und mit einem Preis, der zwischen
	 *         minPreis und maxPreis liegt.
	 */
	public Artikel[] aufgabe_h_vi(Lager lager, String gesuchterAutor, double minPreis, double maxPreis) {
		return null;
	}
}