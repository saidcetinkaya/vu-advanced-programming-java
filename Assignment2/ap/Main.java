package nl.vu.labs.phoenix.ap;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	PrintStream out;

	Main() {
		out = new PrintStream(System.out);
	}

	private void print(Set<BigInteger> set) {
		int sizeOfSet = set.size();

		for (int i = 0; i < sizeOfSet - 1; i++) {
			out.print(set.get());
			set.remove(set.get());
			out.print(" ");
		}

		if (set.size() > 0) {
			out.print(set.get());
			set.remove(set.get());
		}

		out.print("\n");
	}

	private boolean program(Scanner in, InterpreterInterface<Set<BigInteger>> interpreter) {

		do {

			if (!in.hasNextLine()) {
				out.printf("\n");
				return false;
			}

			Set<BigInteger> result = interpreter.eval(in.nextLine());

			if (!(result == null)) {
				print(result);
			}

		} while (in.hasNextLine());

		return true;
	}

	private void start() {
		InterpreterInterface<Set<BigInteger>> interpreter = new Interpreter<Set<BigInteger>>();
		Scanner in = new Scanner(System.in);

		while (program(in, interpreter)) {

		}

	}

	public static void main(String[] argv) {
		new Main().start();
	}
}
