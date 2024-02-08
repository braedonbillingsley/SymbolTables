public class BinarySearchST<Key extends Comparable, Value> implements SymbolTable<Key, Value>
{
    // private fields
    private Key[] keys;     // array of keys
    private Value[] values; // array of values
    private int size;

    public BinarySearchST(int capacity) {
        // capacity is the potential space we will use
        // size is the actual space used
        // wanted to do keys = new Key[capacity], but Java doesn't do it
        // so this below is a workaround (ugly!)
        keys = (Key[])new Object[capacity];
        values = (Value[])new Object[capacity];
    }

    private int rank(Key key) {
        // returns the index of where the key is located
        int low = 0;            // low index
        int high = size - 1;    // high index

        while (low <= high) {
            int mid = low + (high - low) / 2;   // find halfway/mid index
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {          // if (key < keys[mid])
                high = mid - 1;
            }
            else if (cmp > 0) {     // else if (key > keys[mid])
                low = mid + 1;
            }
            else {                  // else we know key == keys[mid]
                return mid;
            }
        }

        return low;
    }

    @Override
    public void put(Key key, Value val) {

    }

    @Override
    public Value get(Key key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }
}
