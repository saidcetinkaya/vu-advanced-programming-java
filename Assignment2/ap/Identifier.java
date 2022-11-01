package nl.vu.labs.phoenix.ap;

public class Identifier implements IdentifierInterface {
	
	private StringBuffer sb;

	public Identifier() {
		sb = new StringBuffer();
	}

	public Identifier(Identifier identifier) {
		sb = new StringBuffer(identifier.sb.toString());
	}
	
	public String value() {
		return sb.toString();
	}

	public Identifier init(char element) {
		sb.append(element);
		return this;
	}

	public Identifier addElement(char element) {
		sb.append(element);
		return this;
	}

	public char getChar(int index) {
		return sb.charAt(index);
	}

	public boolean equals(IdentifierInterface element) {
		return sb.toString().equals(element.value());
	}

	public int size() {
		return sb.length();
	}
}
