package nl.vu.labs.phoenix.ap;

public class LinkedList<E extends Comparable<E>> implements ListInterface<E> {

	private Node first;
	private Node last;
	private Node current;
	private int nOE;

	public LinkedList() {
		init();
	}

	private class Node {

		E data;
		Node prior, next;

		public Node(E data) {
			this(data, null, null);
		}

		public Node(E data, Node prior, Node next) {
			this.data = data == null ? null : data;
			this.prior = prior;
			this.next = next;
		}

	}

	public boolean isEmpty() {
		return nOE == 0;
	}

	public LinkedList<E> init() {
		last = current = first = null;
		nOE = 0;
		return this;
	}

	public int size() {
		return nOE;
	}

	public LinkedList<E> insert(E d) {
		if (isEmpty()) {
			first = current = last = new Node(d);
		} else if (current.data.compareTo(d) == 0) {
			if (current.equals(last)) {
				current = last = current.next = new Node(d, current, null);
			} else {
				current = current.next = current.next.prior = new Node(d, current, current.next);
			}
		} else if (current.data.compareTo(d) < 0) {
			if (current.equals(last)) {
				current = last = current.next = new Node(d, current, null);
			} else if (current.next.data.compareTo(d) < 0) {
				goToNext();
				return insert(d);
			} else {
				current = current.next = current.next.prior = new Node(d, current, current.next);
			}
		} else if (current.data.compareTo(d) > 0) {
			if (current.equals(first)) {
				current = first = current.prior = new Node(d, null, current);
			} else if (current.prior.data.compareTo(d) > 0) {
				goToPrevious();
				return insert(d);
			} else {
				current = current.prior = current.prior.next = new Node(d, current.prior, current);
			}
		}
		nOE++;
		return this;
	}

	public E retrieve() {
		return current.data;
	}

	public LinkedList<E> remove() {
		if (size() == 1) {
			first = current = last = null;
		} else if (current.equals(last)) {
			last = current = last.prior;
			current.next = null;
		} else if (current.equals(first)){
			first = current = current.next;
			current.prior = null;
		} else {
			current.next.prior = current.prior;
			current = current.prior.next = current.next;
		}
		nOE--;
		return this;
	}

	public boolean find(E d) {
		if (isEmpty()) {
			return false;
		} else if (first.data.compareTo(d) > 0) {
			goToFirst();
			return false;
		} else if (last.data.compareTo(d) < 0) {
			goToLast();
			return false;
		}
		
		goToFirst();
		while (!current.data.equals(d)) {
			if (!goToNext()) {
				return false;
			}
		}
		return true;
	}

	public boolean goToFirst() {
		if (isEmpty()) {
			return false;
		}
		current = first;
		return true;
	}

	public boolean goToLast() {
		if (isEmpty()) {
			return false;
		}
		current = last;
		return true;
	}

	public boolean goToNext() {
		if (isEmpty() || current.data.equals(last.data)) {
			return false;
		}
		current = current.next;
		return true;
	}

	public boolean goToPrevious() {
		if (isEmpty() || current.data.equals(first.data)) {
			return false;
		}
		current = current.prior;
		return true;
	}

	public LinkedList<E> copy() {
		LinkedList<E> copy = new LinkedList<E>();
		goToFirst();
		for (int i = 0; i < nOE; i++) {
			copy.insert(retrieve());
			goToNext();
		}
		return copy;
	}
}
