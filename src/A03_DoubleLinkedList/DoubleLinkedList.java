package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{
    private Node<T> first;

    private Node<T> last;

    private Node<T> current;
    /**
     * Einfügen einer neuen <T>
     * @param a <T>
     */
    public void add(T a) {
        Node<T> n = new Node<T>(a);
        if (last == null) {     // Liste ist noch leer
            first = n;
            last = n;
        }
        else {                  // Liste enth?lt bereits Elemente
            last.setNext(n);
            n.setPrevious(last);
            last = n;
        }
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {
        current = first;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
        current = last;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {
        return first;
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {
        return last;
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {
        if (current == null) {
            return null;
        }
        T a = current.getData();
        current = current.getNext();
        return a;
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {
        if (current == null) {
            return null;
        }
        T a = current.getData();
        current = current.getPrevious();
        return a;
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {
        if (current != null)
            current = current.getNext();
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {
        if (current != null)
            current = current.getPrevious();
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {
        if (current == null) {
            throw new CurrentNotSetException();
        }
        return current.getData();
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {
        Node<T> n = first;
        int cnt = 1;
        while (cnt != pos && n != null) {
            n = n.getNext();
            cnt++;
        }
        return (n != null) ? n.getData() : null;
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos) {
        Node<T> n = first;
        int cnt = 1;
        while (cnt != pos && n != null) {   // Element suchen
            n = n.getNext();
            cnt++;
        }
        if (n == null) {    // keine Node an dieser Position
            return;
        }
        removeNode(n);
        if (current == n) {
            current = null;
        }
    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {
        if (current == null) {
            throw new CurrentNotSetException();
        }
        removeNode(current);
        if (current.getNext() != null) {
            current = current.getNext();
        }
        else {
            current = current.getPrevious();
        }
    }

    private void removeNode(Node n) {
        if (n == first) {
            first = n.getNext();
            if (first != null) {
                first.setPrevious(null);
            }
        }
        else if (n == last) {
            last = n.getPrevious();
            if (last != null) {
                last.setNext(null);
            }
        }
        else {
            n.getPrevious().setNext(n.getNext());
            n.getNext().setPrevious(n.getPrevious());
        }
    }

    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {
        Node<T> n = new Node(a);
        if (current == null) {
            throw new CurrentNotSetException();
        }
        else if (current == last) {
            last.setNext(n);
            n.setPrevious(last);
            last = n;
        }
        else {
            n.setNext(current.getNext());
            n.setPrevious(current);
            current.getNext().setPrevious(n);
            current.setNext(n);
        }
        current = n;
    }
}
