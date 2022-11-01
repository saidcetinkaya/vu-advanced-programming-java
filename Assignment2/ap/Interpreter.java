package nl.vu.labs.phoenix.ap;

import java.io.PrintStream;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

public class Interpreter<T extends SetInterface<BigInteger>> implements InterpreterInterface<T> {

	private HashMap<IdentifierInterface, SetInterface<BigInteger>> memory = new HashMap<>();
	private static final String REGEX_KEY = "[a-zA-Z][a-zA-Z||0-9]*$";
	private static final String REGEX_ADDITIVE = "(?=-|\\||\\+)";
	private static final String REGEX_MULTIPLICATIVE = "(?=\\*)";

	PrintStream out;

	public Interpreter() {
		out = new PrintStream(System.out);
	}

	@SuppressWarnings("unchecked")
	public T getMemory(String v) {

		for (IdentifierInterface key : memory.keySet()) {
			if (key.value().equals(v)) {
				return (T) memory.get(key);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public T eval(String s) {
		SetInterface<BigInteger> result = new Set<BigInteger>();

		try {
			result = parseStatement(s);
		} catch (APException e) {
			out.println(e.getMessage());
			return null;
		}

		return (T) result;
	}

	private T parseStatement(String v) throws APException {

		v = v.trim();

		if (v.length() == 0) {
			throw new APException("no statement");
		} else if (v.charAt(0) == '/') {
			// do nothing
		} else if (v.charAt(0) == '?') {
			return parsePrintStatement(v);
		} else if (v.contains("=")) {
			parseAssignment(v);
		} else {
			throw new APException("No command given");
		}
		return null;
	}

	private T parsePrintStatement(String v) throws APException {
		if (v.trim().length() - 1 == 0) {
			throw new APException("No set has been provided to print");
		}
		return parseExpression(v.substring(1));
	}

	@SuppressWarnings("resource")
	private void parseAssignment(String v) throws APException {
		if (v.charAt(0) == '=') {
			throw new APException("Variable name is not given");
		} else if (v.substring(v.indexOf("=") + 1).length() == 0) {
			throw new APException("No set has been given for assignment");
		}

		Scanner in = new Scanner(v);
		in.useDelimiter("=");
		String key = in.next().trim();
		String value = in.next().trim();

		IdentifierInterface keyInMap = null;

		for (IdentifierInterface mapKey : memory.keySet()) {
			if (mapKey.value().equals(key)) {
				keyInMap = mapKey;
			}
		}

		if (keyInMap == null) {
			keyInMap = parseIdentifier(key);
		}

		SetInterface<BigInteger> value1 = parseExpression(value).copy();
		memory.put(keyInMap, value1);
	}

	private IdentifierInterface parseIdentifier(String v) throws APException {
		IdentifierInterface key = new Identifier();
		v = v.trim();

		if (v.matches(REGEX_KEY)) {
			for (int i = 0; i < v.length(); i++) {
				key.addElement(v.charAt(i));
			}
		} else {
			throw new APException("Key has a leading digit or special character");
		}

		return key;
	}

	@SuppressWarnings("unchecked")
	private T parseExpression(String v) throws APException {
		v = v.trim();
		SetInterface<BigInteger> result = new Set<BigInteger>();
		String[] str = new String[0];

		str = mergeClosingBracket(v.split(REGEX_ADDITIVE));

		if (str.length > 0) {
			result = parseTerm(str[0]);
		}

		for (int i = 1; i < str.length; i++) {
			String term = str[i].substring(1, str[i].length());

			if (str[i].charAt(0) == ('+')) {
				result = result.union(parseTerm(term));
			} else if (str[i].charAt(0) == ('-')) {
				result = result.difference(parseTerm(term));
			} else if (str[i].charAt(0) == ('|')) {
				result = result.symDiff(parseTerm(term));
			}
		}

		return (T) result;
	}

	private String[] mergeClosingBracket(String[] str) throws APException {
		String[] result = new String[str.length];
		StringBuffer sb = new StringBuffer();
		int count = 0;
		int resultIndex = 0;

		for (int i = 0; i < str.length; i++) {

			for (int j = 0; j < str[i].length(); j++) {

				if (count == 0 && str[i].charAt(j) == ')') {
					throw new APException("Misplaced ')' ");
				} else if (str[i].charAt(j) == ')' && count > 0) {
					count -= 1;
				} else if (str[i].charAt(j) == '(') {
					count += 1;
				}
			}

			if (count > 0 || (count == 0 && str[i].contains(")"))) {
				sb.append(str[i]);
			}

			if (sb.length() == 0) {
				result[resultIndex] = str[i];
				resultIndex++;
			} else if (count == 0 && sb.length() > 0) {
				result[resultIndex] = sb.toString();
				resultIndex++;
				sb = new StringBuffer();
			}

		}

		if (count > 0) {
			throw new APException("Missing ')'");
		}

		result = Arrays.copyOf(result, resultIndex);

		return result;
	}

	@SuppressWarnings("unchecked")
	private T parseTerm(String v) throws APException {

		v = v.trim();
		SetInterface<BigInteger> result = new Set<BigInteger>();
		String[] str = new String[0];

		str = mergeClosingBracket(v.split(REGEX_MULTIPLICATIVE));

		if (str.length > 0) {
			result = parseFactor(str[0]);
		}

		for (int i = 1; i < str.length; i++) {
			String term = str[i].substring(1, str[i].length());
			result = result.intersection(parseFactor(term));
		}

		return (T) result;
	}

	@SuppressWarnings("unchecked")
	private T parseFactor(String v) throws APException {
		v = v.trim();

		if (v.length()==0) {
			throw new APException("No set given after operation");
		}else if(v.charAt(0) == '{') {
			return parseSet(v);
		} else if (v.charAt(0) == '(') {
			return parseComplexFactor(v);
		}
		SetInterface<BigInteger> result = getMemory(v) == null ? null : getMemory(v).copy();

		if (result == null) {
			throw new APException("Variable doesn't exist");
		}

		return (T) result;
	}

	private T parseComplexFactor(String v) throws APException {
		return parseExpression(v.substring(1, v.length() - 1));
	}

	@SuppressWarnings({ "unchecked", "resource" })
	private T parseSet(String v) throws APException {
		v = v.trim();
		paranthesisBalance(v);
		v = v.substring(1, v.length() - 1).trim();
		SetInterface<BigInteger> result = new Set<BigInteger>();

		if (v.length() == 0) {
			return (T) result;
		} else if (v.startsWith(",")) {
			throw new APException("There is a comma at the start of Set");
		}

		Scanner in = new Scanner(v);
		in.useDelimiter(",");

		while (in.hasNext()) {
			String candidate = in.next().trim();
			checkCandidate(candidate);
			BigInteger element = new BigInteger(candidate);
			result.add(element);
		}

		return (T) result;
	}

	private boolean paranthesisBalance(String v) throws APException {
		int count = 0;

		for (int j = 0; j < v.length(); j++) {
			if (count > 1) {
				throw new APException("There is nested set");
			} else if (count == 0 && v.charAt(j) == '}') {
				throw new APException("Misplaced '}' ");
			} else if (v.charAt(j) == '}' && count > 0) {
				count -= 1;
			} else if (v.charAt(j) == '{') {
				count += 1;
			} else if (count == 0) {
				throw new APException("There is immediate character after '}'");
			}
		}

		if (count > 0) {
			throw new APException("There is one or more '{'");
		} else if (count < 0) {
			throw new APException("There is one or more '}'");
		}

		return true;
	}

	private void checkCandidate(String candidate) throws APException {
		if (candidate.length() == 0) {
			throw new APException("Space is not allowed as Set element");
		} else if (!candidate.matches("[0-9]*")) {
			throw new APException("There is nondigit character in the Set");
		} else if (candidate.contains(" ")) {
			throw new APException("There is space between elements in the Set");
		} else if (candidate.length() > 1 && candidate.charAt(0) == '0') {
			throw new APException("Natural numbers can not start with 0");
		} else if (candidate.charAt(0) == '+' || candidate.charAt(0) == '-') {
			throw new APException("Sign is not allowed");
		}
	}

}
