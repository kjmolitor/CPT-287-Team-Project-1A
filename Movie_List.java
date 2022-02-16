package project_1a;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.NoSuchElementException;

public class Movie_List implements Iterable {
	protected Movie[] data;
	protected int capacity; // Maximum size of the regular array
	protected int numOfItems; // Number of elements stored in the array list
	private static final int DEFAULT_CAPACITY = 10;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	
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
		
		/** Doubles the capacity. 	*/
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
			if(isEmpty() || value.getReceiveDate().after(get(size() - 1).getReleaseDate()) || value.getReleaseDate().equals(get(size()-1).getReleaseDate())) {
				data[numOfItems++] = value; 
				return true;
			}
			List_Iterator it = iterator();
			while (it.hasNext()){
				Movie current = it.next();
				int i = 0;
				if(value.getReleaseDate().before(current.getReleaseDate())) {
					insert(i,value);
					break;
				}
				i++;
			}
			return true;
			// Time complexity: O(1)
		}
		
		/**
		 * Insert the movie at specified index
		 * @param index: index of the movie
		 * @param value: the new movie
		 */
		private void insert(int index, Movie value) {
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
		
		/**
		 * Remove a movie from the list;
		 * @param index: the index of the movie that wants to be removed
		 * @return: the removed movie
		 */
		@SuppressWarnings("unchecked")
		public final Movie remove(int index) {
			if (index < 0 || index >= size()) {
				throw new IndexOutOfBoundsException("Index out of bound: " + index);
			}
			Movie itemToRemove = data[index];
			for (int i = index + 1; i < numOfItems; i++) {
				data[i - 1] = data[i];
			}
			numOfItems--;
			return itemToRemove;
		}
		
		/**
		 * Get the index of the name if the name is in the movie list
		 * @param name: the user name 
		 * @return: the index of the name
		 */
		public int indexOfName(String name) {
			List_Iterator it = iterator();
			while (it.hasNext()){
				Movie current = it.next();
				int i = 0;
				if(name.toLowerCase().equals(current.getName().toLowerCase())) {return i;}
				i++;
			}
			
			return -1;
		}
		
		/**
		 * Check if the user name is in the movie list
		 * @param name: the user name
		 * @return: {true} if the user name is in the movie list; {false} otherwise
		 */
		public final boolean containsName(String name) {
			return indexOfName(name) != -1;
		}
		
		/**
		 * Check if the movie list has the release date
		 * @param release: the user release date
		 * @return: {true} if the release date is in the movie list; {false} otherwise
		 */
		public boolean hasReleaseDate(Date release) {
			List_Iterator it = iterator();
			while (it.hasNext()){
				Movie current = it.next();
				if(release.equals(current.getReleaseDate())){
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Add the movie with specified date from coming list to showing list
		 * @param release: the specified release date
		 * @param showing: showing list that need to add the movie with the specified date
		 * @return: the new showing list that has the movie with the specified date
		 */
		public Movie_List displayReleaseDate(Date release, Movie_List showing) {
			Movie_List result = new Movie_List(showing);
			List_Iterator it = iterator();
			while (it.hasNext()){
				Movie current = it.next();
				if(release.equals(current.getReleaseDate())){
					current.setStatus("released");
					result.add(current);
					System.out.print(current.getName() + ", " + dateFormat.format(current.getReleaseDate())
					+ ", " + current.getDescription() + "\n");
				}
			}
			return result;
		}
		
		
		/**
		 * search the index of the date in movie list that is after the specified date.
		 * @param date: the specified date
		 * @return: the index of the date that is after the specified date
		 */
		public int searchIndexBeforeDate(Date date) {
			int i=1;
			List_Iterator it = iterator();
			while (it.hasNext()){
				Movie current = it.next();
				if(date.before(current.getReleaseDate())) {
					return i;
				}
				i++;
			}
			return -1;
		}
		
		/**
		 * Return the movie list that has dates before the specified date
		 * @param date: the specified date
		 * @return: the movie list that has dates before the specified date
		 */
		public Movie_List displayBeforeDate(Date date) {
			Movie_List result = new Movie_List();
			int index = searchIndexBeforeDate(date);
			if(index == -1) {
				System.out.println("This doesn't have the release date before this date " + dateFormat.format(date));
				return result;
			}else if(index == 0) {
				result.add(data[0]);
				return result;
			}
			
			List_Iterator it = iterator();
			while (it.hasNext()){
				if (index < 2) {
					break;
				}
				Movie current = it.next();
				result.add(current);
				index--;
			}
			return result;
		}
		
		/**
		 * Update the description of the movie
		 * @param name: the name of the movie that need to update description
		 * @param description: the new description
		 * @return: {true} if the description is updated; {false} otherwise
		 */
		public boolean updateDescription(String name, String description) {
			
			List_Iterator it = iterator();
			while (it.hasNext()){
				Movie current = it.next();
				if(name.toLowerCase().equals(current.getName().toLowerCase())) {
					current.setDescription(description);
					return true;
				}
			}
			
			return false;
		}
		
		
		/**
		 * Update the release date of the movie in the list
		 * @param name: name of the movie
		 * @param release: release date of the movie
		 * @return
		 */
		public boolean updateRelease(String name, Date release) {
			List_Iterator it = iterator();
			while (it.hasNext()){
				Movie current = it.next();
				if(name.toLowerCase().equals(current.getName().toLowerCase())) {
					if(release.before(current.getReceiveDate())) {
						return false;
					}
					current.setReleaseDate(release);
					return true;
				}
			}
			return false;
		}
		
		
		@Override
		public final String toString() {
			String output = "";
			List_Iterator it = iterator();
			while (it.hasNext()){
				Movie current = it.next();
				output += String.format(current.getName() + ", " + dateFormat.format(current.getReleaseDate())
				+ ", " + current.getDescription() + "\n");
			}
			return output;
		}
		
		/**
		 * Write the string for the writer to overwrite input file
		 * @return: the string for the writer to overwrite input file
		 */
		public String toFile() {
			String output = "";
			List_Iterator it = iterator();
			while(it.hasNext()) {
				Movie current = it.next();
				output += String.format(current.getName() + ", " + dateFormat.format(current.getReleaseDate())
				+ ", " + current.getDescription() + ", "+ dateFormat.format(current.getReceiveDate()) + ", " + current.getStatus() +"\n");
			}
			
			
			return output;
		}
		
		
	/** Generates a list iterator at the beginning of the array list.
      	@return: a list iterator at the beginning of the array list
    */
    @Override
    public List_Iterator iterator() {
        return new List_Iterator() {
            // Data fields
            private int leftIndex = -1;  // Index of the left element at the current iterator position
            private int rightIndex = 0;  // Index of the right element at the current iterator position

            // Methods

            /** Tests whether there exists a next element at current iterator position.
                @return: {true} if there exists a next element at current iterator position; {false} otherwise
            */
            @Override
            public boolean hasNext() { return rightIndex != size(); }  // Time complexity: O(1)

            /** Tests whether there exists a previous element at current iterator position.
                @return: {true} if there exists a previous element at current iterator position; {false} otherwise
            */
            @Override
            public boolean hasPrevious() { return leftIndex != -1; }  // Time complexity: O(1)

            /** Moves the iterator forward and returns the element passed by.
                @return: element passed by in the iterator movement
                @throws NoSuchElementException : there is not a next element at current iterator position.
            */
            @Override
            @SuppressWarnings("unchecked")
            public Movie next() {
                if (!hasNext()) { throw new NoSuchElementException(); }
                leftIndex++;
                return data[rightIndex++];
            }  // Time complexity: O(1)

            /** Moves the iterator backward and returns the element passed by.
                @return: element passed by in the iterator movement
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            @SuppressWarnings("unchecked")
            public Movie previous() {
                if (!hasPrevious()) { throw new NoSuchElementException(); }
                rightIndex--;
                return data[leftIndex--];
            }  // Time complexity: O(1)

            /** Replaces the next element at current iterator position with the specified element.
                @param value: element with which to replace the next element at current iterator position
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            public void setNext(Movie value) {
                if (!hasNext()) { throw new NoSuchElementException(); }
                data[rightIndex] = value;
            }  // Time complexity: O(1)

            /** Replaces the previous element at current iterator position with the specified element.
                @param value: element with which to replace the previous element at current iterator position
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            public void setPrevious(Movie value) {
                if (!hasPrevious()) { throw new NoSuchElementException(); }
                data[leftIndex] = value;
            }

            /** Removes (and returns) the next element at current iterator position.
                @return: element removed
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            public Movie removeNext() {
                if (!hasNext()) { throw new NoSuchElementException(); }
                return remove(rightIndex);
            }  // Average time complexity: O(n - rightIndex)

            /** Removes (and returns) the previous element at current iterator position.
                @return: the element removed
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            public Movie removePrevious() {
                if (!hasPrevious()) { throw new NoSuchElementException(); }
                previous();
                return removeNext();
            }  // Average time complexity: O(n - leftIndex)

            /** Inserts an element at the current iterator position.
                @param value: element to insert
            */
            @Override
            public void add(Movie value) {
                insert(rightIndex, value);
                next();
            }  // Average time complexity: O(n - rightIndex)
        };
    }  // Time complexity: O(1)
}
