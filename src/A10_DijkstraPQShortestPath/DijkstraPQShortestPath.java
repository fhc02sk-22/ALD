package A10_DijkstraPQShortestPath;

import java.util.List;

public class DijkstraPQShortestPath extends FindWay {
	private int[] dist;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	/**
	 * Startentfernung initialisieren
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		dist = new int[numv];
		for (int i = 0; i < numv; i++) {
			dist[i] = 9999; // Summen im Graph dürfen nie mehr ergeben
		}
	}

	/**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein Rückgabewert
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected boolean calculatePath(int from, int to) {

		// 1. schritt > eigene Distanz festlegen
		dist[from] = 0;

		// 2. schritt > heap aufbauen
		VertexHeap vertexHeap = new VertexHeap(graph.numVertices());
		for (int i = 0; i < graph.numVertices(); i++) {
			vertexHeap.insert(new Vertex(i, dist[i]));
		}

		while (!vertexHeap.isEmpty()) {
			int currentVertex = vertexHeap.remove().vertex; // currentVertex = 0
			List<WeightedEdge> edgesFromCurrent = graph.getEdges(currentVertex); // Knoten 4 / Knoten 5 => Liefert die verbunden Knoten von Vertext
			for (WeightedEdge we : edgesFromCurrent) {
				int newCosts = dist[currentVertex] + we.weight;
				int nextVertex = we.to_vertex; // wo möchte ich hin

				if (newCosts < dist[nextVertex]) {
					dist[nextVertex] = newCosts; // neue wegkosten festlegen
					pred[nextVertex] = currentVertex;
					vertexHeap.setCost(nextVertex, newCosts);
				}
			}
		}

		if (dist[to] == 9999) {
			return false;	// Zielknoten nicht gefunden
		}

		return true;
	}
}
