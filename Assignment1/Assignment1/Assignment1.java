package Assignment1;

import java.io.PrintStream;
import java.util.Scanner;

public class Assignment1 {

	private static final int MAX_NUMBER_OF_ELEMENTS = 10;
	private static final String REGEX = "[a-zA-Z][a-zA-Z||0-9]*$";

	PrintStream out;
	Scanner input;

	Assignment1() {
		out = new PrintStream(System.out);
		input = new Scanner(System.in);
	}

	void printIdentifier(Identifier identifier) {
		int identifierSize = identifier.size();

		for (int i = 0; i < identifierSize; i++) {
			out.print(identifier.getChar(i));
		}
	}

	void print(Set set) {
		int sizeOfSet = set.size();

		out.print("{");

		for (int i = 0; i < sizeOfSet - 1; i++) {
			printIdentifier(set.getElement());
			set.deleteElement(set.getElement());
			out.print(" ");
		}

		if (set.size() > 0) {
			printIdentifier(set.getElement());
			set.deleteElement(set.getElement());
		}

		out.print("}\n");
	}

	void calculateAndGiveOutput(Set set1, Set set2) {
		out.print("Difference      = ");
		print(set1.difference(set2));
		out.print("Intersection    = ");
		print(set1.intersection(set2));
		out.print("Sym. diff .     = ");
		try {
			print(set1.symDiff(set2));
		} catch (Exception e) {
			out.println(e);
		}
		out.print("Union           = ");
		try {
			print(set1.union(set2));
		} catch (Exception e) {
			out.println(e);
		}
		
		out.print("\n");
	}

	Identifier createIdentifier(String str) {
		Identifier element = new Identifier();

		for (int i = 0; i < str.length(); i++) {
			element.addElement(str.charAt(i));
		}

		return element;
	}

	void createSet(String[] elements, Set set) throws Exception {
		for (int i = 0; i < elements.length; i++) {
			Identifier element = createIdentifier(elements[i]);

			if (!set.isInSet(element) && set.size() != MAX_NUMBER_OF_ELEMENTS) {
				set.addElement(element);
			} else if (set.size() == MAX_NUMBER_OF_ELEMENTS && !set.isInSet(element)) {
				set.init();
				throw new Exception("Error: Too many elements");
				// put this into addElement and get rid of else if
			}
		}
	}

	boolean checkCharacters(String[] elements) throws Exception {
		for (int j = 0; j < elements.length; j++) {
			if (!elements[j].matches(REGEX)) {
				throw new Exception("Error: There is a digit in first character or it contains special character");
			}
		}
		return true;
	}

	boolean checkWhiteSpace(String[] elements) throws Exception {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == "") {
				throw new Exception("Error: White space is used in a wrong place");
			}
		}
		return true;
	}

	boolean checkParantheses(String str) throws Exception {
		if (!str.startsWith("{")) {
			throw new Exception("Error: Missing '{' or there are some character before '{'");
		} else if (str.contains("}") && !str.endsWith("}")) {
			throw new Exception("Error: Input instead of eoln");
		} else if (!str.endsWith("}")) {
			throw new Exception("Error: Missing '}'");
		} else if (str.charAt(str.length() - 2) == ' ') {
			throw new Exception("Error: White space is used before '}'");
		}

		return true;
	}

	boolean inputContainsCorrectSet(Scanner in, Set set) throws Exception {

		String setString = in.nextLine();

		if (setString.length() == 0) {
			return false;
		}

		if (checkParantheses(setString)) {
			setString = setString.substring(1, setString.length() - 1);
		}

		if (setString.length() == 0)
			return true;

		String[] setArray = setString.split(" ");

		if (checkWhiteSpace(setArray) && checkCharacters(setArray)) {
			createSet(setArray, set);
		}

		return true;
	}

	boolean askSet(Scanner input, String question, Set set) {
		boolean loop = true;

		while (loop) {

			try {

				do {
					out.printf("%s", question);
					if (!input.hasNextLine()) {
						out.printf("\n");
						return false;
					}
				} while (!inputContainsCorrectSet(input, set));

				loop = false;

			} catch (Exception e) {
				out.println(e.getMessage());
			}
		}

		return true;
	}

	boolean askBothSets(Scanner input, Set set1, Set set2) {
		return askSet(input, "Give first set  : ", set1) && askSet(input, "Give second set : ", set2);
	}

	void start() {
		Set set1 = new Set(), set2 = new Set();

		while (askBothSets(input, set1, set2)) {
			calculateAndGiveOutput(set1, set2);
			set1.init();
			set2.init();
		}
	}

	public static void main(String[] argv) {
		new Assignment1().start();
	}
}