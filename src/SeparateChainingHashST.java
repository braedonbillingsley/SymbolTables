public class SeparateChainingHashST<K, V> implements SymbolTable<K, V> {

    private SequentialSearchST<K, V>[] table;
    private int tableSize; //size of the array (number of buckets)
    private int size; // number of actual keys in the table

    /**
     * Initializes a new Separate Chaining Hash Symbol Table with the specified table size.
     *
     * @param tableSize the size of the hash table, must be a positive integer
     */
    public SeparateChainingHashST(int tableSize) {
        this.tableSize = tableSize;

        // Creates an array (each element is default initialized to null)
        table = new SequentialSearchST[tableSize];

        // Loop through array, replace null with an empty linked list object
        for (int i = 0; i < tableSize; i++) {
            table[i] = new SequentialSearchST();
        }
    }


    /**
     * Initializes a new Separate Chaining Hash Symbol Table with hardcoded table size.
     *
     */
    public SeparateChainingHashST() {
        // call the other constructor and set up 997 buckets
        this(997);
    }

    /**
     * Private helper method to generate an index number for the given key.
     *
     * @param key the key to hash
     * @return the index number generated for the key
     */
    // private helper method - the hash function
    private int hash(K key) {

        // Take a key and generate an index number
        return (key.hashCode() & 0x7fffffff) % tableSize;

    }

    /**
     * Inserts a key-value pair into the hash table.
     *
     * @param k the key to insert
     * @param val the value to associate with the key
     */
    @Override
    public void put(K k, V val) {
        // if the table doesn't contain the key, bump the size up
        if (!table[hash(k)].contains(k)) {
            size++;
        }

        // Add the new key
        // Or replace the value associated with the key if already there
        table[hash(k)].put(k, val);
    }

    /**
     * Returns the value associated with the specified key.
     *
     * @param k the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    @Override
    public V get(K k) {
        return table[hash(k)].get(k);
    }

    /**
     * Returns the number of key-value pairs in the table.
     *
     * @return the size of the table
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator that refers to all the keys
     * in the table.
     */
    @Override
    public Iterable<K> keys() {
        // create an empty queue as a collector/container
        Queue<K> q = new LinkedQueue();

        // loop through the table and collect bags
        for (int i = 0; i < tableSize; i++) {
            for (K singleKey : table[i].keys()) {
                q.enqueue(singleKey);
            }
        }

        return q;
    }


}
