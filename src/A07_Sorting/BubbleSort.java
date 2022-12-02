package A07_Sorting;


public class BubbleSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {

		// O(n²)

		for (int i = 0; i < personen.length; i++) {
			boolean anyChange = false;
			for (int j = 0; j < personen.length - 1 - i; j++) { // codierfehler

				if (personen[j].compareTo(personen[j + 1]) > 0){
					Person temp = personen[j];
					personen[j] = personen[j + 1];
					personen[j + 1] = temp;
					anyChange = true;
				}
			}
			if (anyChange == false) {
				break;
			}
		}

	}
	
}
