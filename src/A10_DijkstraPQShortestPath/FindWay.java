package A10_DijkstraPQShortestPath;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class FindWay {
	protected Graph graph;
	protected int[] pred; // Vorgänger
	
	public FindWay(Graph graph) {
		this.graph = graph;
		this.pred = new int[graph.numVertices()]; // Vorgäner
	}

	/**
	 * Liefert den Weg von (from) nach (to) als Liste zurück
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg von Start nach Ziel oder null
	 */
	public List<Integer> findWay(int from, int to) {
		initPathSearch();
		if (!calculatePath(from, to)) {
			// keinen Weg gefunden
			return null; // leer liste wäre auch ok
		}
		return createWay(from, to);
	}

	/**
	 * Initialisierungsfunktion
	 */
	abstract protected void initPathSearch();

	/**
	 * Berechnungsfunktion für Weg von (from) nach (to)
	 */
	abstract protected boolean calculatePath(int from, int to);
	
	/**
	 * Weg von (to) nach (from) aus Vorgängerknoten rekonstruieren
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg als Liste
	 */
	protected ArrayList<Integer> createWay(int from, int to) {
		ArrayList<Integer> way = new ArrayList<Integer>();

		int currentVertex = to;
		while (from != currentVertex) {
			way.add(0, currentVertex);
			currentVertex = pred[currentVertex];
		}

		way.add(0, from);

		return way;
	}
}
