package A07_Sorting;


public class QuickSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		sort(personen, 0, personen.length - 1);
	}

	private void sort(Person[] personen, int left, int right) {
		if (left >= right)
			return;

		int p = partition(personen, left, right); // Pivotelement an die richtige Position verschieben
		sort(personen, left, p - 1);
		sort(personen, p + 1, right);
	}

	private int partition(Person[] personen, int left, int right) {

		Person pivotPerson = personen[right];
		int i = left - 1; // wieso -1
		int k = right;

		do {
			do {
				i++;
			} while (personen[i].compareTo(pivotPerson) < 0);

			do {
				k--;
			} while (k >= 0 && personen[k].compareTo(pivotPerson) > 0); // fehler

			if (i < k) { // nicht überholt
				Person temp = personen[i];
				personen[i] = personen[k];
				personen[k] = temp;
			}
		} while (i < k);

		Person temp = personen[i];
		personen[i] = pivotPerson;
		personen[right] = temp;

		return i;
	}
}