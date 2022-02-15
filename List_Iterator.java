package project_1a;

public interface List_Iterator {
	
	boolean hasNext();

	/** Tests whether there exists a previous element at current iterator position.
	    @return: {true} if there exists a previous element at current iterator position; {false} otherwise
	*/
	boolean hasPrevious();

	/** Moves the iterator backward and returns the element passed by.
	    @return: element passed by in the iterator movement
	    @throws NoSuchElementException: there is not a previous element at current iterator position.
	*/
	Movie previous();
	
	Movie next();

	/** Removes (and returns) the previous element at current iterator position.
	    @return: element removed
	    @throws NoSuchElementException: there is not a previous element at current iterator position.
	*/
	Movie removePrevious();

	Movie removeNext();
	/** Replaces the next element at current iterator position with the specified element.
	    @param value: element with which to replace the next element at current iterator position
	    @throws NoSuchElementException: there is not a next element at current iterator position.
	*/
	void setNext(Movie value);

	/** Replaces the previous element at current iterator position with the specified element.
	    @param value: element with which to replace the previous element at current iterator position
	    @throws NoSuchElementException: there is not a previous element at current iterator position.
	*/
	void setPrevious(Movie value);

	/** Inserts an element at the current iterator position.
	    @param value: element to insert
	*/
	void add(Movie value);

}