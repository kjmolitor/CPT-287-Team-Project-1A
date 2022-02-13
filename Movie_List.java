package project_1a;

import java.util.Arrays;

public class Movie_List {
	protected Movie[] data;
	protected int totalShowing;
	protected int totalComing;
	protected int capacity; // Maximum size of the regular array
	protected int numOfItems; // Number of elements stored in the array list
	private static final int DEFAULT_CAPACITY = 10;
	
	
	// Constructors
		public Movie_List() { // Default constructor
			capacity = DEFAULT_CAPACITY;
			data = new Movie[capacity];
		}
		
		public Movie_List(int initialCapacity) { //Constructor with user-specified capacity
			capacity = initialCapacity;
			data = new Movie[capacity];
		}
		
		public Movie_List(Movie_List other) { //Copy constructor
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
		public final Movie get(int index) {
			if (index < 0 || index >= size()) {
				throw new IndexOutOfBoundsException("Index out of bound: " + index);
			}
			return data[index];
		} // Time complexity: O(1)
		
		/**
		 * Update the elements at user-specified index.
		 * @param index: user-specified index
		 * @param value: value to replace the current value at user-specified index
		 * @throws: IndexOutOfBoundsException: index < 0 || index >= size().
		 */
		public void set(int index, Movie value) {
			if (index < 0 || index >= size()) {
				throw new IndexOutOfBoundsException("Index out of bound: " + index);
			}
			data[index] = value;
		} // Time complexity: O(1)
		
		/** Doubles he capacity. 	*/
		private void reserve() {
			capacity *= 2;
			Movie[] newArr = new Movie[capacity];
			System.arraycopy(data, 0, newArr, 0, numOfItems);
			data = newArr;
		} // Time complexity: O(n);
		
		
		/**
		 * Appends a new element to the end of the array list.
		 * @param value: new element to add
		 * @return: always {true} indicating addition was successful
		 */
		public boolean add(Movie value) {
			if (size() == capacity) { reserve(); }
			data[numOfItems++] = value;
			return true;
			// Time complexity: O(1)
		}
		
		public void insert(int index, Movie value) {
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
		public final Movie remove(int index) {
			if (index < 0 || index >= size()) {
				throw new IndexOutOfBoundsException("Index out of bound: " + index);
			}
			Movie itemToRemove = data[index]; // Why shallow copy is OK here?
			for (int i = index + 1; i < numOfItems; i++) {
				data[i - 1] = data[i];
			}
			numOfItems--;
			return itemToRemove;
		}
		
		public int indexOf(Movie item) {
			for(int i = 0; i < numOfItems; i++) {
				if(item.equals(data[i])) {return i;}
			}
			return -1;
		}
		
		public final boolean contains(Movie item) {
			return indexOf(item) != -1;
		}
		
		@Override
		public final String toString() {
			String output = "";
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			for(int i = 0; i < numOfItems; i++) {
				output += String.format(data[i].getStatus() +", "+ data[i].getName() +", "+ dateFormat.format(data[i].getReleaseDate())
						+", "+ data[i].getDescription()+", "+ dateFormat.format(data[i].getReceiveDate()) +"\n");
				if (i != numOfItems - 1) {
					output += "\n";
				}
			}
			return output;
		}
}
