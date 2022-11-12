package A01_Stack;


public class Stack<T>
{
	 private Node<T> first;
     private int counter;
    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws StackEmptyException 
     */
    public T pop() throws StackEmptyException {
        if (first == null)
            throw new StackEmptyException();

        Node<T> result = first;
        first = first.getNext();
        counter--;
        result.setNext(null);
        return result.getData();
    }
    
    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i) {
        Node<T> newElement = new Node<>(i);

        newElement.setNext(first);
        first = newElement;
        counter++;
    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
        return counter;
    }

    public int getCountAlternative() {
        int counter = 0;
        Node<T> iterator = first;

        while (iterator != null) {
            counter++;
            iterator = iterator.getNext();

        }
        return counter;
    }
}
