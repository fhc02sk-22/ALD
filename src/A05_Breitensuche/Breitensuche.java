package A05_Breitensuche;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Breitensuche extends BaseTree<Integer> {

	@Override
	protected int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück
	 * @param start Startknoten für Teilbaum
	 * @return Liste der Knoten in Breitenfolge
	 */
	public List<Integer> getBreadthFirstOrder(Node<Integer> start) {

		List<Integer> result = new ArrayList<>();

		Queue<Node<Integer>> queue = new ArrayDeque<>();
		queue.add(start);

		while (!queue.isEmpty()) {
			Node<Integer> node = queue.poll();
			if (node.getLeft() != null)
				queue.add(node.getLeft());
			if (node.getRight() != null)
				queue.add(node.getRight());

			result.add(node.getValue());
		}

		return result;
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück,
	 * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
	 * @param start Startknoten für Teilbaum
	 * @param level Nur Knoten dieser Ebene ausgeben
	 * @return Liste aller Knoten
	 */
	public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {

		List<Integer> result = new ArrayList<>();

		Queue<NodeWithLevel> queue = new ArrayDeque<>();
		queue.add(new NodeWithLevel(start, 1));

		while (!queue.isEmpty()) {
			NodeWithLevel node = queue.poll();
			if (node.getNode().getLeft() != null && node.getLevel() + 1 <= level )
				queue.add(new NodeWithLevel(node.getNode().getLeft(), node.getLevel() + 1));
			if (node.getNode().getRight() != null && node.getLevel() + 1 <= level)
				queue.add(new NodeWithLevel(node.getNode().getRight(), node.getLevel() + 1));

			if (node.getLevel() == level)
				result.add(node.getNode().getValue());
		}

		return result;
	}

}
