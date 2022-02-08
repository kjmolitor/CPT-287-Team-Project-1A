package project_1a;


public class Ordered_Array_List<T extends Comparable<T>> extends Array_List<T> {
    // Constructors

    public Ordered_Array_List() { super(); }  // Default constructor

    public Ordered_Array_List(Ordered_Array_List<T> other) { super(other); }  // Copy constructor

    // Methods

    @Override
    public void set(int index, T value) {
        throw new UnsupportedOperationException("Operation not supported in ordered array list.");
    }

    /** Inserts an item in order into the ordered array list.
        @param value: item to be inserted
        @return: {true} if element successfully inserted; {false} if element already exists in the list
    */
    @Override
    public boolean add(T value) {

    	if (isEmpty()|| value.compareTo(get(size() - 1)) >= 0 ) { return super.add(value); }
        for (int i = 0; i < size(); i++) {
            if (get(i).compareTo(value) == 0) { return false; }
            if (get(i).compareTo(value) > 0) {
                super.insert(i, value);
                break;
            }
        }
        return true;
    }

    @Override
    public void insert(int index, T value) {
        throw new UnsupportedOperationException("Operation not supported in ordered array list.");
    }

    /** Finds the index of the specified element in the ordered array list.
        @param value: element to search for
        @return: index of the specified element in the list, or {-1} if the list does not contain the element
    */
    @Override
    public int indexOf(T value) {
    	 int i = 0, j = size() - 1;
    	    while (i <= j) {
    	        int mid = (i + j) / 2;
    	        if (value.compareTo(get(mid)) < 0) { j = mid - 1; }
    	        else if (value.compareTo(get(mid)) > 0) { i = mid + 1; }
    	        else { return mid; }
    	    }
    	    return -1;
    }
    
}