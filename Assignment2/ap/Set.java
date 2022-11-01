package nl.vu.labs.phoenix.ap;

public class Set<T extends Comparable<T>> implements SetInterface<T> {

	private LinkedList<T> elements;

	public Set() {
		init();
	}

	public Set(Set<T> set) {
		elements = set.elements.copy();
	}

	public Set<T> init() {
		elements = new LinkedList<T>();
		return this;
	}

	public boolean add(T t) {
		if (isInSet(t)) {
			return false;
		}
		elements.insert(t);
		return true;
	}

	public T get() {
		if(isEmpty()) {
			return null;
		}
		return elements.retrieve();
	}

	public boolean remove(T t) {
		if (isInSet(t)) {
			elements.remove();
			return true;
		}
		return false;
	}

	public int size() {
		return elements.size();
	}

	public Set<T> copy() {
		return new Set<T>(this);
	}

	public Set<T> difference(SetInterface<T> set) {
		Set<T> result = new Set<T>(this);
		int setSize = size();

		if (!set.isEmpty() && elements.goToFirst()) {
			for (int i = 0; i < setSize; i++) {
				if (set.isInSet(get())) {
					result.remove(get());
				}
				elements.goToNext();
			}
		}
		
		return result;
	}

	public Set<T> intersection(SetInterface<T> set) {
		return union(set).difference(difference(set)).difference(set.difference(this));
	}

	public Set<T> union(SetInterface<T> set) {
		Set<T> result = (Set<T>) set.difference(this);
		int setSize = size();

		if (elements.goToFirst()) {
			for (int i = 0; i < setSize; i++) {
				result.add(get());
				elements.goToNext();
			}
		}
		
		return result;
	}

	public Set<T> symDiff(SetInterface<T> set) {
		return union(set).difference(intersection(set));
	}

	public boolean isInSet(T element) {
		return elements.find(element);
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

}
