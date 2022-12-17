package A05_Breitensuche;

public class NodeWithLevel {

    private Node<Integer> node;
    private int level;

    public NodeWithLevel(Node<Integer> node, int level) {
        this.node = node;
        this.level = level;
    }

    public Node<Integer> getNode() {
        return node;
    }

    public void setNode(Node<Integer> node) {
        this.node = node;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
