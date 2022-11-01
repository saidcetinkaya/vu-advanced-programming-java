package Assignment1;

public class Identifier implements IdentifierInterface {

	private StringBuffer sb;

	public Identifier() {
		sb = new StringBuffer();
	}

	public Identifier(Identifier identifier) {
		sb = new StringBuffer(identifier.sb.toString());
	}

	public void init(char element) {
		sb.append(element);
	}

	public void addElement(char element) {
		sb.append(element);
	}

	public char getChar(int index) {
		return sb.charAt(index);
	}

	public boolean compare(Identifier element) {
		return sb.toString().equals(element.sb.toString());
	}

	public int size() {
		return sb.length();
	}
}
