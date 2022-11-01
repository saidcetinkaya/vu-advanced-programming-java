package Assignment1;

public interface SetInterface {
	
	/*
	 *
	 * Elements: Objects of the type Identifier
	 * Structure: Non-structured
	 * Domain: Max size of the group of elements is 20
	 *
	 * Constructors
	 *
	 * Set();
	 *   PRE  - 
	 *   POST - A new Set-object has been made and its value is empty set.
	 *
	 * Set (Set set);
	 *   PRE  - 
	 *   POST - A new Set-object has been made and it is a copy of the value of the other set.
	 *
	 */
	
	int MAX_NUMBER_OF_ELEMENTS = 20;
	
	void init ();
	/* PRE  - 
	   POST - The set value is empty.
	*/
	void addElement (Identifier element);
	/* PRE  - The maximum number of elements are not reached in the set.
	   POST - A copy of element is now at the end of the set.
	*/
	
	Identifier getElement ();
	/* PRE  - The set is not empty.
	   POST - A random copy of an element is returned.	  
	*/
	
	void deleteElement(Identifier element);
	/* PRE  - 
	   POST - The element of the type identifier deleted from the set.	  
	*/
	
	Set difference(Set set);
	/* PRE  - 
	   POST - The difference of two sets is returned.
	*/
	
	Set intersection(Set set);
	/* PRE  - 
	   POST - The intersection of two sets is returned.
	*/
	
	Set union(Set set) throws Exception;
	/* PRE  - 
	   POST - SUCCESS: The union of two sets is returned.
	   		  FAILURE: The new set exceeds maximum number of element.
	*/
	
	Set symDiff(Set set) throws Exception;
	/* PRE  - 
	   POST - SUCCESS: The symmetric difference of two sets is returned.
	   		  FAILURE: The new set exceeds maximum number of element.
	*/
	
	boolean isInSet(Identifier element);
	/* PRE  - 
	   POST - True if the element is in the set otherwise false.
	*/
	
	boolean isEmpty();
	/* PRE  - 
	   POST - True if the set is empty otherwise false.
	*/
	
	int size();
	/* PRE  - 
	   POST - The amount of elements of the set has been returned.
	*/
	
	
}
