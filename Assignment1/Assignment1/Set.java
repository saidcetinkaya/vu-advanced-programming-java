package Assignment1;

public class Set implements SetInterface {
	public static final int MAX_NUMBER_OF_ELEMENTS = 20;

	private Identifier[] elements;
	private int nOE;

	public Set() {
		elements = new Identifier[MAX_NUMBER_OF_ELEMENTS];
		nOE = 0;
	}

	public Set(Set set) {
		elements = new Identifier[set.elements.length];
		nOE = set.nOE;
		copyElements(elements, set.elements, nOE);
	}

	private void copyElements(Identifier[] dest, Identifier[] src, int amount) {
		for (int i = 0; i < amount; i++) {
			dest[i] = new Identifier(src[i]);
		}
	}

	public void init() {
		nOE = 0;
	}

	public void addElement(Identifier element) {
		elements[nOE] = new Identifier(element);
		nOE++;
	}

	public Identifier getElement() {
		return elements[nOE - 1];
	}

	public void deleteElement(Identifier element) {

		if (isInSet(element)) {
			Identifier[] temp = new Identifier[MAX_NUMBER_OF_ELEMENTS];

			int j = 0;

			for (int i = 0; i < nOE; i++) {
				if (!element.compare(elements[i])) {
					temp[j] = elements[i];
					j++;
				}
			}

			elements = temp;
			nOE--;
		}
	}

	public Set difference(Set set) {
		Set result = new Set(this);

		for (int i = 0; i < set.nOE; i++) {
			if (isInSet(set.elements[i])) {
				result.deleteElement(set.elements[i]);
			}
		}

		return result;
	}

	public Set intersection(Set set) {
		return union(set).difference(difference(set)).difference(set.difference(this));
	}

	public Set union(Set set) throws Exception {
		Set result = difference(set);

		for (int j = 0; j < set.nOE; j++) {
			if (result.size() + 1 > MAX_NUMBER_OF_ELEMENTS) {
				throw new Exception("The new set exceeds maximum number of element.");
			}
			result.addElement(set.elements[j]);
		}

		return result;
	}

	public Set symDiff(Set set) throws Exception {
		return union(set).difference(intersection(set));
	}

	public boolean isInSet(Identifier element) {
		for (int i = 0; i < nOE; i++) {
			if (elements[i].compare(element)) {
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty() {
		return nOE == 0;
	}

	public int size() {
		return nOE;
	}
}
