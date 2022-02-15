package project_1a;



public interface Iterator {
	/**
	 * Test whether there is a next element.
	 * @return: {true} if there is a next element; {false} otherwise
	 */
	boolean hasNext();
	
	/**
	 * Moves the iterator forward and returns the value passed by.
	 * @return: value passed by during the movement 
	 * @throws NoSuchElementException: hasNext() is false.
	 */
	Movie next();
	
	/**
	 * Removes and returns the next element.
	 * @return: next element.
	 * @throws NoSuchElementException: hasNext() is false.
	 */
	Movie removeNext();
	
}
