package A10_DijkstraPQShortestPath;
import java.util.HashMap;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		/*
			- Je Knoten soll das Land festgelegt werden
			- Grenzübertritt soll zusätzliche Wegkosten von 2 hinzugefügt

		 */

		Graph g = new ListGraph(8, false);
		g.addEdge(0, 4,  3);
		g.addEdge(0, 5,  4);
		g.addEdge(1, 3,  1);
		g.addEdge(1, 4,  6);
		g.addEdge(1, 6,  2);
		g.addEdge(2, 3,  3);
		g.addEdge(2, 4,  4);
		g.addEdge(2, 7,  4);
		g.addEdge(3, 6,  2);
		g.addEdge(3, 7,  1);
		g.addEdge(5, 6,  3);

		HashMap<Integer, String> vertexCountry = new HashMap<>();
		vertexCountry.put(0, "AT");
		vertexCountry.put(1, "AT");
		vertexCountry.put(2, "AT");
		vertexCountry.put(3, "AT");
		vertexCountry.put(4, "AT");
		vertexCountry.put(5, "DE");
		vertexCountry.put(6, "AT");
		vertexCountry.put(7, "AT");


		DijkstraPQShortestPath dfs = new DijkstraPQShortestPath(g, vertexCountry);
		List<Integer> way = dfs.findWay(0, 7);
		printWay(way);

	}
	
	public static void printWay(List<Integer> way) {
		if (way == null) {
			System.out.println("Kein Weg gefunden.");
			return;
		}
		for (int i=0; i < way.size(); i++) {
			if (i != 0)
				System.out.print(" -> ");
			System.out.print(way.get(i));
		}
	}
}
