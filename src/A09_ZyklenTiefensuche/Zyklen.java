package A09_ZyklenTiefensuche;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import basisAlgorithmen.Graph;
import basisAlgorithmen.Vertex;
import basisAlgorithmen.WeightedEdge;

public class Zyklen {


	private boolean visited[];

	private HashMap<Integer, Integer> follow;

	private Graph g;
	
	public Zyklen(Graph g) {
		this.g = g;
	}
	
	/**
	 * Retourniert einen Zyklus eines Graphen, sofern einer existiert
	 * @param g zu prüfender Graph
	 * @return Anzahl der Komponenten
	 */
	public List<Integer> getCycle() {
		int n = g.numVertices();
		visited = new boolean[n];
		follow = new HashMap<Integer, Integer>(n);
		for (int i=0; i < n; i++) {		// der Reihe nach mit allen Knoten durchmachen
			if (visited[i]) {
				continue;
			}
			List<Integer> res = checkForCycle(i, -1);
			if (res != null) {			// Zyklus gefunden?
				return res;
			}
		}
		return null;
	}

	private List<Integer> checkForCycle(Integer v, Integer pred) {
		if (follow.containsKey(v)) {
			return foundCycle(v);
		}
		if (visited[v]) {				// Knoten bereits besucht
			return null;
		}
		visited[v] = true;
		follow.put(pred, v);			// Nachfolger in Liste einf?gen
		List<WeightedEdge> lwe = g.getEdges(v);
		for (WeightedEdge we : lwe) {	// alle Kanten durchgehen
			if (we.to_vertex == pred && !g.isDirected()) {	// ungerichteter Graph: direkter Weg zur?ck
				continue;
			}
			List<Integer> res = checkForCycle(we.to_vertex, v);	// Rekursion
			if (res != null) {	// Zyklus gefunden
				return res;
			}
		}
		follow.remove(pred);	// Backtracking: Vorg?nger wieder entfernen
		return null;			// kein Zyklus gefunden
	}

	/**
	 * Generiert Liste des Weges aus follow-HashMap
	 * @param start Startpunkt des Zyklus
	 * @return Liste der Knoten des Zyklus in passender Besuchsreihenfolge
	 */
	private List<Integer> foundCycle(Integer start) {
		List<Integer> cycle = new ArrayList<Integer>();
		Integer cur = start;
		do {
			cycle.add(cur);
			cur = follow.get(cur);
		} while (cur != null);
		cycle.add(start);
		return cycle;
	}
	

}
