package nl.vu.labs.phoenix.ap;


/* [1] Identifier Specification
 * 	   -- Complete the specification for an Identifier interface.
 * 		  See the List interface for inspiration
 */
public interface IdentifierInterface {
	
	/*
	 *
	 * Elements: Objects of the type Char
	 * Structure: Linear
	 * Domain: Alphanumeric characters that starts with letter.
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
	
	/* 
	 * [2] Mandatory methods. Make sure you do not modify these!
	 * 	   -- Complete the specifications of these methods
	 */
	
	String value();
	/* PRE  - 
	   POST - A string element is returned.
	*/
	
	
	/* 
	 * [3] Add anything else you think belongs to this interface 
	 */
	
	IdentifierInterface init (char element);
	/* PRE  - Char element has to be letter.
	   POST - Identifier object will contain the char element and length will be one.
	*/
	
	IdentifierInterface addElement (char element);
	/* PRE  - Char element has to be letter or digit.
	   POST - A char element is added to the end of the identifier character collection.
	*/
	
	char getChar (int index);
	/* PRE  - The index is not greater or equal to size of element and also non-negative.
	   POST - A char element is returned.
	*/
	
	boolean equals(IdentifierInterface element);
	/* PRE  - 
	   POST - True : The values of identifiers are equal.
	   		  False: The values of identifiers are not equal.
	*/
	
	int size();
	/* PRE  - 
	   POST - The amount of characters of the identifier has been returned.
	*/
}
