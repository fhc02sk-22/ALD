package A04_TraverseTree;

import A01_Stack.Stack;
import A01_Stack.StackEmptyException;

import java.util.HashSet;
import java.util.Set;


public class Wörterbuch {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	private Wort root;
	
	public Wort getRoot() {
		return root;
	}

	/**
	 * Zählt alle Wörter des Teilbaums ab einem bestimmten Wort
	 * @param w Wort
	 * @return Zahl der Wörter (=Anzahl der Elemente)
	 */
	public int countWordsInSubTree(Wort w) {

		if (w == null)
			return 0;

		int ergLeft = w.getLeft() != null ? countWordsInSubTree(w.getLeft()) : 0;
		int ergRight = w.getRight() != null ? countWordsInSubTree(w.getRight()) : 0;

		return ergLeft + ergRight + 1;
	}

	/**
	 * Liefert die Menge aller Wörter retour, die ein spezifisches Präfix haben.
	 * @param prefix Wörter müssen diesen Präfix haben
	 * @return Menge aller zutreffenden Wörter
	 */
	public Set<String> getWordsWithPrefix(String prefix) {

		try {
			return getWordsWithPrefixIterativ(prefix);
		} catch (StackEmptyException e) {
			throw new RuntimeException(e);
		}


		//return getWordsWithPrefixInteral(getRoot(), prefix);
	}

	private Set<String> getWordsWithPrefixIterativ(String prefix) throws StackEmptyException {
		Stack<Wort> stack = new Stack<>();
		Set<String> result = new HashSet<>();

		stack.push(getRoot());

		while (stack.getCount() > 0) {

			Wort w = stack.pop();
			if (w.getRight() != null)
				stack.push(w.getRight());

			if (w.getLeft() != null)
				stack.push(w.getLeft());

			if (w.getWort().startsWith(prefix))
				result.add(w.getWort());
		}

		return result;
	}

	private Set<String> getWordsWithPrefixInteral(Wort w, String prefix) {

		Set<String> result = new HashSet<>();
		if (w == null)
			return result;

		if (w.getWort().startsWith(prefix)) {
			result.add(w.getWort());
		}

		result.addAll(getWordsWithPrefixInteral(w.getLeft(), prefix));
		result.addAll(getWordsWithPrefixInteral(w.getRight(), prefix));

		return result;
	}
	

	/**
	 * Neues Wort hinzufügen
	 * @param wort Hinzuzufügendes Wort
	 */
	public void add(String wort) {
		Wort neu = new Wort(wort);
		if (root == null) {			// Fall 1: Baum ist leer
			root = neu;
			return;
		}
		Wort w = root;				// Fall 2: Baum ist nicht leer
		while (true) {
			int vgl = wort.compareTo(w.getWort());
			if (vgl < 0) {			// Neues Wort ist lexikographisch kleiner
				if (w.getLeft() == null) {
					w.setLeft(neu);
					neu.setParent(w);
					return;
				}
				w = w.getLeft();
			}
			else if (vgl > 0) {		// Neues Wort ist lexikographisch größer
				if (w.getRight() == null) {
					w.setRight(neu);
					neu.setParent(w);
					return;
				}
				w = w.getRight();
			}
			else {					// Neues Wort ist lexikographisch gleich
				return;
			}
		}
	}

	public Wort find(String s) {
		return find(root, s);
	}
	
	private Wort find(Wort current, String s) {
		if (current == null) {
			return null;
		}
		int vgl = s.compareTo(current.getWort());
		if (vgl == 0) {		// Gefunden
			return current;
		}
		else if (vgl < 0) {	// Links
			return find(current.getLeft(), s);
		}
		else {				// Rechts
			return find(current.getRight(), s);
		}
	}
	
}
