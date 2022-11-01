package nl.vu.labs.phoenix.ap;

/* [1] Set Specification
 * 	   -- Complete the specification for a set interface.
 * 		  See the List interface for inspiration
 */
public interface SetInterface<T extends Comparable<T>> {
	
	
	/*
	 *
	 * Elements  : Objects of the type T.
	 * Structure : Non-structured
	 * Domain    : All rows of non duplicate elements of type T are valid values for a set.
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
	
	/* 
	 * [2] Mandatory methods. Make sure you do not modify these!
	 * 	   -- Complete the specifications of these methods
	 */
	
	SetInterface<T> init ();
	/* PRE  - 
	   POST - The set value is empty.
	*/
	
	boolean add(T t);
	/* PRE  - 
	   POST - True : A copy of element is now in the set.
	   		  False: A copy of element is already at the set.
	*/
	
	T get();
	/* PRE  - 
	   POST - If set is not empty, a random copy of an element has been returned. 
	   		  Otherwise, null is returned.
	*/
	
	boolean remove(T t);
	/* PRE  - 
	   POST - True : The element of the type T removed from the set.
	   		  False: The element is not in the set.	  
	*/
	
	int size();

	/* PRE  - 
	   POST - The amount of elements of the set has been returned.
	*/
	
	SetInterface<T> copy();
	/* PRE  - 
	   POST - A copy of set has been returned.
	*/
	
	
	
	
	/*
	 * [3] Methods for set operations 
	 * 	   -- Add methods to perform the 4 basic set operations 
	 * 		  (union, intersection, difference, symmetric difference)
	 */
	
	
	SetInterface<T> difference(SetInterface<T> set);
	/* PRE  - 
	   POST - The difference of two sets has been returned.
	*/
	
	SetInterface<T> intersection(SetInterface<T> set);
	/* PRE  - 
	   POST - The intersection of two sets has been returned.
	*/
	
	SetInterface<T> union(SetInterface<T> set);
	/* PRE  - 
	   POST - The union of two sets has been returned.
	*/
	
	SetInterface<T> symDiff(SetInterface<T> set);
	/* PRE  - 
	   POST - The symmetric difference of two sets has been returned.		 
	*/
	
	
	/* 
	 * [4] Add anything else you think belongs to this interface 
	 */
	
	
	boolean isInSet(T element);
	/* PRE  - 
	   POST - True : The element is in the set.
	   		  False: The element is not in the set.
	*/
	
	boolean isEmpty();
	/* PRE  - 
	   POST - True : The set is empty.
	   		  False: The set is not empty.
	*/
	
}