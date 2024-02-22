public class BST<K extends Comparable<K>, V> implements SymbolTable<K, V> {

    private Node root;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        Node parent; // Optional, but sometimes helpful.
        int nodeInSubtree; // author calls this int N;

        public Node(K key, V value) {
            this.key  = key;
            this.value = value;
            left = null;
            right = null;
            nodeInSubtree = 0;
        }
    }

    public BST() {
        root = null;
    }

    /**
     * Put a key-value pair into the table
     *
     * @param k
     * @param val
     */
    @Override
    public void put(K k, V val) {

    }

    public V get(K keyToFind) {
        return get(root, keyToFind); // start private recursive call at the root.
    }

    /**
     * Returns the value paired with the given key recursively.
     *
     * @param keyToFind
     */
    private V get(Node current, K keyToFind) {
        if (current == null) {
            return null;
        }

        int cmp = keyToFind.compareTo(current.key);

        if (cmp < 0) { // keyToFind < current.key
            // go left
            return get(current.left, keyToFind);

        } else if (cmp > 0){ // keyToFind > current.key
            // go right
            return get(current.right, keyToFind);
        } else { // keyToFind == current.key
            // found it
            return current.value;
        }
    }


    /**
     * Returns the value paired with the given key.
     *
     * @param keyToFind
     */
    public V getIterative(K keyToFind) {
        Node current = root;
        while (current != null) {
            int cmp = keyToFind.compareTo(current.key);
            if (cmp < 0) { // keyToFind < current.key
                // go left
                current = current.left;

            } else if (cmp > 0){ // keyToFind > current.key
                // go right
                current = current.right;
            } else { // keyToFind == current.key
                // found it
                return current.value;
            }
        }
        return null;
    }

    /**
     * Remove key (and it's value) from the table.
     *
     * @param k
     */
    @Override
    public void delete(K k) {
        SymbolTable.super.delete(k);
    }

    /**
     * Returns true if there is a value paired with a key.
     *
     * @param k
     */
    @Override
    public boolean contains(K k) {
        return SymbolTable.super.contains(k);
    }

    /**
     * Returns true if the table is empty.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return SymbolTable.super.isEmpty();
    }

    /**
     * Returns the number of key-value pairs in the table.
     *
     * @return
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node current) {
        return size(current.left) + size(current.right) + 1;
    }
    /**
     * Returns an iterator that refers to all the keys
     * in the table.
     */
    @Override
    public Iterable<K> keys() {
        Queue<K> q = new LinkedQueue<>();
        inOrder(root, q);
        return q;
    }

    private void inOrder(Node current, Queue<K> q)
    {
        if (current == null)  {
            return;
        }

        // Recursive calls
        // Traverse left subtree
        inOrder(current.left, q);
        q.enqueue(current.key);
        // Traverse right subtree
        inOrder(current.right, q);
    }
}
