package A08_GraphZusammenhaengend;


import basisAlgorithmen.Graph;
import basisAlgorithmen.WeightedEdge;

import java.util.List;

public class ConnectedComponents {

	private boolean[] visited;

	/**
	 * Retourniert die Anzahl der zusammenhaengenden Komponenten eines Graphen
	 * @param g zu pruefender Graph
	 * @return Anzahl der Komponenten
	 */
	public int getNumberOfComponents(Graph g) {
		int size = g.numVertices();
		visited = new boolean[size];

		int counter = 0;
		for (int i = 0; i < size; i++) {
			if (visited[i] == true)
				continue;

			counter++;
			deepSearch(g, i);
		}

		return counter;
	}

	private void deepSearch(Graph g, int i) {

		if (visited[i] == true)
			return;

		visited[i] = true;
		List<WeightedEdge> listEdges = g.getEdges(i);
		for(WeightedEdge we : listEdges) {
			deepSearch(g, we.to_vertex);
		}
	}

}
