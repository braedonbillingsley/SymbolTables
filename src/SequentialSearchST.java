public class SequentialSearchST<Key, Value> implements SymbolTable<Key, Value> {
    private Node head;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node next;

        //constructor
        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    public SequentialSearchST() {
        head = null;
        size = 0;
    }

    @Override
    public void put(Key key, Value val) {
        // if the key is not in the list, put it up front at the head
        // but if key is already in the list, replace the value
        //  in that node

        // loop through the nodes to see if the key is there
        Node current = head;
        while (current != null) {
            if (key.equals(current.key)) {
                // we found the key! So replace the value
                current.value = val;
                return; // done!
            }
            current = current.next;
        }

        // if we got here, we know key doesn't exist in list
        // so make a new node and put it at the front
        head = new Node(key, val, head);
        size++;
    }

    @Override
    public Value get(Key key) {
        for (Node current = head; current != null; current = current.next) {
            if (key.equals(current.key)) {
                return current.value;
            }
        }

        // if I got here, I know key is not in the list
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedQueue<>();

        Node current = head;
        while (current != null) {
            q.enqueue(current.key);
            current = current.next;
        }
        return q;
    }
}