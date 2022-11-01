package Assignment1;

public interface IdentifierInterface {
	
	/*
	 *
	 * Elements: Objects of the type Char
	 * Structure: Linear
	 * Domain: Alphanumeric characters that starts with letter
	 *
	 * Constructors
	 * 
	 * Identifier();
	 * 	 PRE -
	 *   POST - A new identifier object has beeen created and it has undefined value.
	 *
	 * Identifier (Identifier identifier);
	 *   PRE  - 
	 *   POST - A new Identifier-object has been made and it is a copy of the value of the other identifier.
	 *
	 */
	
	void init (char element);
	/* PRE  - Char element has to be letter.
	   POST - Identifier object will contain the char element and length will be one.
	*/
	
	void addElement (char element);
	/* PRE  - Char element has to be letter or digit.
	   POST - A char element is added to the end of the identifier character collection.
	*/
	
	char getChar (int index);
	/* PRE  - The index is not greater or equal to size of element and also non-negative.
	   POST - A char element is returned.
	*/
	
	boolean compare(Identifier element);
	/* PRE  - 
	   POST - If the values of identifiers are equal, returns true; otherwise returns false.
	*/
	
	int size();
	/* PRE  - 
	   POST - The amount of characters of the identifier has been returned.
	*/
}
