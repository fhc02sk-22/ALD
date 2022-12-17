package A06_Tiefensuche;

import java.util.ArrayList;
import java.util.List;

import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: Länge des Films
	 */
	protected int compare(Film a, Film b) {

		if (a.getLänge() < b.getLänge()) {
			return -1;
		}
		if (a.getLänge() > b.getLänge()) {
			return 1;
		}
		return 0;
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	public List<String> getNodesInOrder(Node<Film> node) {

		ArrayList<String> als = new ArrayList<String>();
		if (node != null) {
			als.addAll(getNodesInOrder(node.getLeft()));
			als.add(node.getValue().getTitel());
			als.addAll(getNodesInOrder(node.getRight()));
		}
		return als;
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren Länge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale Länge des Spielfilms
	 * @param max Maximale Länge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {
		return getMinMaxPreOrder(root, min, max);
	}

	/**
	 * Rekursive Hilfsfunktion
	 */
	private List<String> getMinMaxPreOrder(Node<Film> node, double min, double max) {
		ArrayList<String> result = new ArrayList<>();
		if (node == null) {
			return result;
		}
		double laenge = node.getValue().getLänge();

		if (laenge >= min && laenge < max) {
			result.add(node.getValue().getTitel());
		}
		if (laenge >= min) {
			result.addAll(getMinMaxPreOrder(node.getLeft(), min, max));
		}
		if (laenge < max) {
			result.addAll(getMinMaxPreOrder(node.getRight(), min, max));
		}
		return result;
	}

}
