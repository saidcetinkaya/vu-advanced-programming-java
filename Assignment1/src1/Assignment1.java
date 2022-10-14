package Assignment1;

import java.io.PrintStream;
import java.util.Scanner;

public class Assignment1 {

	static final int MAX_NUMBER_OF_ELEMENTS = 10;
	static final String REGEX = "[a-zA-Z][a-zA-Z||0-9]*$";

	PrintStream out;
	Scanner input;

	Assignment1() {
		out = new PrintStream(System.out);
		input = new Scanner(System.in);
	}

	
	void calculateAndGiveOutput(Set set1, Set set2) {
		set1.printResults(set2);
	}

	String[] simplifySet(String[] abc) {
		String[] temp = new String[abc.length];
		int nOE = 0;
		int i, j;

		for (i = 0; i < abc.length; i++) {
			for (j = 0; j < i; j++) {
				if (abc[i].equals(abc[j])) {
					break;
				}
			}

			if (i == j) {
				temp[nOE] = abc[i];
				nOE++;
			}
		}

		String[] result = new String[nOE];

		for (int k = 0; k < nOE; k++) {
			result[k] = temp[k];
		}

		return result;
	}

	boolean inputContainsCorrectSet(Scanner in, Set set) {
		String a = in.nextLine();

		if (a.equals("^D")) {
			System.exit(0);
		} else if (!a.startsWith("{")) {
			out.print("missing '{' or there are some character before '{'\n ");
			return false;
		} else if (a.contains("}") && !a.endsWith("}")) {
			out.print("identifiers after eoln\n");
			return false;
		} else if (!a.endsWith("}")) {
			out.print("missing '}' \n");
			return false;
		} else if (a.charAt(a.length() - 2) == ' ') {
			out.print("white space is used in a wrong place\n");
			return false;
		}

		a = a.substring(1, a.length() - 1);

		if (a.length() == 0) {
			set.nOE = 0;
			return true;
		}

		String[] abc = a.split(" ");

		for (int i = 0; i < abc.length; i++) {
			if (abc[i] == "") {
				out.print("white space is used in a wrong place\n");
				return false;
			}
		}

		for (int j = 0; j < abc.length; j++) {

			if (!abc[j].matches(REGEX)) {
				out.print("There is a digit in first character or it contains special character \n");
				return false;
			}
		}

		abc = simplifySet(abc);

		if (abc.length > MAX_NUMBER_OF_ELEMENTS) {
			out.print("too many arguments\n");
			return false;
		}
		
		for(int i = 0; i < abc.length; i++) {
			set.append(new Identifier(abc[i]));
		}
		

		return true;
	}

	boolean askSet(Scanner input, String question, Set set ) {
		do {
			out.printf("%s", question);
			if (!input.hasNextLine()) {
				out.printf("\n");
				return false;
			}
		} while (!inputContainsCorrectSet(input, set));
		return true;
	}

	boolean askBothSets(Scanner input, Set set1, Set set2) {
		return askSet(input, "Give first set : ", set1) && askSet(input, "Give second set : ", set2);
	}

	void start() {
		Set set1 = new Set(), set2 = new Set();
		
		while (askBothSets(input, set1, set2)) {
			calculateAndGiveOutput(set1, set2);
			set1.nOE = 0;
			set2.nOE = 0;
		}
	}

	public static void main(String[] argv) {
		new Assignment1().start();
	}
}