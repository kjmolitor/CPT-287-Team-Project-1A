package project_1a;

import java.util.NoSuchElementException;
import java.util.Arrays;

/** An advanced array */
public class Array_List<T> {
	// Data fields
	protected Object[] data; // Using a regular array to store the elements or inputs
	protected int capacity; // Maximum size of the regular array
	protected int numOfItems; // Number of elements stored in the array list
	private static final int DEFAULT_CAPACITY = 10;
	
	// Constructors
	
	public Array_List() { // Default constructor
		capacity = DEFAULT_CAPACITY;
		data = new Object[capacity];
	}
	
	public Array_List(int initialCapacity) { //Constructor with user-specified capacity
		capacity = initialCapacity;
		data = new Object[capacity];
	}
	
	public Array_List(Array_List<T> other) { //Copy constructor
		capacity = other.capacity;
		numOfItems = other.numOfItems;
		data = Arrays.copyOf(other.data, capacity); // Deep copy
	}
	
	// Methods
	
	/**
	 * Returns the number of elements in the array list.
	 * @return: number of elements in the array list.
	 */
	public int size() { return numOfItems;} // Time complexity : O(1)
	
	/**
	 * Tests whether the array list is empty.
	 * @return: {true} if the array list is empty; {false} otherwise
	 */
	public final boolean isEmpty() {return size() == 0;} // Time complexity: O(1)
	
	/**
	 * Return the element at user-specified index.
	 * @param index: user-specified index
	 * @return: element at the specified index
	 * @throws: IndexOutOfBoundsException: index < 0 || index >= size().
	 */
	@SuppressWarnings("unchecked")
	public final T get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index out of bound: " + index);
		}
		return (T)data[index];
	} // Time complexity: O(1)
	
	/**
	 * Update the elements at user-specified index.
	 * @param index: user-specified index
	 * @param value: value to replace the current value at user-specified index
	 * @throws: IndexOutOfBoundsException: index < 0 || index >= size().
	 */
	public void set(int index, T value) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index out of bound: " + index);
		}
		data[index] = value;
	} // Time complexity: O(1)
	
	/** Doubles he capacity. 	*/
	private void reserve() {
		capacity *= 2;
		Object[] newArr = new Object[capacity];
		System.arraycopy(data, 0, newArr, 0, numOfItems);
		data = newArr;
	} // Time complexity: O(n);
	
	
	/**
	 * Appends a new element to the end of the array list.
	 * @param value: new element to add
	 * @return: always {true} indicating addition was successful
	 */
	public boolean add(T value) {
		if (size() == capacity) { reserve(); }
		data[numOfItems++] = value;
		return true;
		// Time complexity: O(1)
	}
	
	public void insert(int index, T value) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index out of bound: " + index);
		}
		if(capacity == numOfItems) {reserve();	}
		for(int i = size() - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = value;
		numOfItems++;
	}
	
	@SuppressWarnings("unchecked")
	public final T remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index out of bound: " + index);
		}
		T itemToRemove = (T)data[index]; // Why shallow copy is OK here?
		for (int i = index + 1; i < numOfItems; i++) {
			data[i - 1] = data[i];
		}
		numOfItems--;
		return itemToRemove;
	}
	
	public int indexOf(T item) {
		for(int i = 0; i < numOfItems; i++) {
			if(item.equals(data[i])) {return i;}
		}
		return -1;
	}
	
	public final boolean contains(T item) {
		return indexOf(item) != -1;
	}
	
	@Override
	public final String toString() {
		String output = "[";
		for(int i = 0; i < numOfItems; i++) {
			output += data[i].toString();
			if (i != numOfItems - 1) {
				output += ", ";
			}
		}
		output += "]";
		return output;
	}
}
