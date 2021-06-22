import java.util.List;

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

		return null;

	}

	/**
	 * Loest die Aufgabe (c) ii. <br />
	 * Der Preis aller Artikel wird um 10% reduziert.
	 * 
	 * @param lager Das Lager mit den Artikeln, deren Preise geaendert werden
	 *              sollen.
	 */
	public void aufgabe_c_ii(Lager lager) {

		lager.applyToArticles(a -> a.setPreis(a.getPreis() * 0.9));
	}

	/**
	 * Loest die Aufgabe (c) iii. <br />
	 * An alle Artikelbezeichnungen wird das Suffix (Sonderangebot) angehaengt.
	 * 
	 * @param lager Das Lager mit den Artikeln, deren Bezeichnungen geaendert werden
	 *              sollen.
	 */
	public void aufgabe_c_iii(Lager lager) {

		lager.applyToArticles(a -> a.setArt(a.getArt() + " (Sonderangebot)"));
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

		lager.applyToArticles(a -> {
			a.setPreis(a.getPreis() * 1.1);
			a.setArt(a.getArt() + " (Sondernangebot)");
		});
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
		lager.applyToSomeArticles(a -> a.getBestand() <= 2, a -> a.aenderePreis(-5));
		/*
		 * Stream<Artikel> stream = Arrays.stream(this.lager);
		 * stream.sorted(bestand.reversed()).limit(2).forEach((item) ->
		 * addPrefix.accept((Artikel) item)); lager.lager = (Artikel[])
		 * stream.toArray();
		 */
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
		for (Artikel artikel : this.lager) {
			if (artikel.toString().contains(gesuchterAutor)) {
				artikel.setPreis(artikel.getPreis() * 0.95);
			}
		}
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

		/*
		 * lager.applyToArticles((Artikel[]) lager.filer(this.lager, a -> a.getBestand()
		 * < 3), a -> a.setPreis(a.getPreis() * 0.95));
		 * lager.applyToArticles((Artikel[]) lager.filer(this.lager, a -> a.getBestand()
		 * > 2), a -> a.setPreis(a.getPreis() * 1.1));
		 */
		lager.applyToArticles(artikel -> {
			if (artikel instanceof CD) {
				artikel.aenderePreis(10);
			} else if (artikel.getBestand() <= 2) {
				artikel.aenderePreis(-5);
			}
		});
	}

	/**
	 * Loest die Aufgabe (h) v. <br />
	 * 
	 * @param lager Das Lager mit den Artikeln.
	 * @return Eine Liste mit allen Buechern, sortiert nach den Namen der Autoren.
	 */
	public Artikel[] aufgabe_h_v(Lager lager) {
		List<Artikel> sorted = lager.getArticles(artikel -> artikel instanceof Buch, (a1, a2) -> {
			final Buch a = (Buch) a1;
			final Buch b = (Buch) a2;
			for (int i = 0; i < a.getAutor().length(); i++) {
				if (b.getAutor().length() <= i) {
					return true;
				}
				int a_char = a.getAutor().charAt(i);
				int b_char = b.getAutor().charAt(i);
				if (b_char > a_char) {
					return false;
				} else if (a_char > b_char) {
					return true;
				}
			}
			return false;
		});
		Artikel[] array = new Artikel[sorted.size()];
		array = sorted.toArray(array);
		return array;
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
		List<Artikel> list = lager.filterAll(artikel -> artikel.getPreis() >= minPreis,
				artikel -> artikel.getPreis() <= maxPreis);
		Artikel[] array = new Artikel[list.size()];
		array = list.toArray(array);
		return array;
	}
}