package Assignment1;

import java.io.PrintStream;

public class Set {
	static final int MAX_NUMBER_OF_ELEMENTS = Assignment1.MAX_NUMBER_OF_ELEMENTS*2;//20

	PrintStream out;
	Identifier[] elements;
	int nOE;

	Set() {
		out = new PrintStream(System.out);
		elements = new Identifier[MAX_NUMBER_OF_ELEMENTS];
		nOE = 0;
	}

	void append(Identifier element) {
		elements[nOE] = element;
		nOE++;
	}

	

	Set difference(Set set) {
		Set result = new Set();

		for (int i = 0; i < nOE; i++) {
			for (int j = 0; j < set.nOE; j++) {
				if (elements[i].x.equals(set.elements[j].x)) {
					// since there is match go to next item of elements and reset second loop
					if((i+1) < nOE) {
						i++;
						j = -1;
					} else {// since i is already (nOE-1)
						break;
					}
					
				} else if (j == set.nOE - 1) {
					// there is no match, so distinct item
					result.append(elements[i]);
				}
			}
		}
		return result;
	}

	Set intersection(Set set) {
		Set result = new Set();
		
		for (int i = 0; i < nOE; i++) {
			for (int j = 0; j < set.nOE; j++) {
				if (elements[i].x.equals(set.elements[j].x)) {
					result.append(elements[i]);
					if((i+1) < nOE) {
						i++;
						j = -1;
					} else {// since i is already (nOE-1)
						break;
					}
				}
			}
		}
		return result;
	}

	Set union(Set set) {
		Set result = difference(set);
		
		for(int i = 0; i < set.nOE; i++) {
			result.append(set.elements[i]);
		}
		
		return result;
	}

	Set symDiff(Set set) {
		Set result = union(set).difference(intersection(set));
		return result;
		
	}
	
	void print(Set set) {
		out.print("{");
		for (int i = 0; i < set.nOE - 1; i++) {
			out.print(set.elements[i].x + " ");
		}
		if (set.nOE > 0) {
			out.print(set.elements[set.nOE - 1].x + "}\n");
		} else {
			out.print("}\n");
		}
	}
	
	void printResults(Set set) {
		out.print("difference = ");
		print(difference(set));
		out.print("intersection = ");
		print(intersection(set));
		out.print("union = ");
		print(union(set));
		out.print("sym. diff . = ");
		print(symDiff(set));
	}

}
