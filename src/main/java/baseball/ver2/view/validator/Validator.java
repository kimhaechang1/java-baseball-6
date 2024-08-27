package baseball.ver2.view.validator;

import java.util.stream.IntStream;

public class Validator {

	private static final int INDEX_OF_START = 0;

	private Validator() {}

	public static void emptyValidate(final String console) {
		if (console.isEmpty() || console.isBlank()) {
			throw new IllegalArgumentException();
		}
	}

	public static void lengthValidate(final String console, final int length) {
		if (console.length() != length) {
			throw new IllegalArgumentException();
		}
	}

	public static void numericValidate(final String console) {
		try {
			Integer.parseInt(console);
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException();
		}
	}

	public static void integerRangeValidate(final int min, final int max, String console) {
		numericValidate(console);
		int value = Integer.parseInt(console);
		if (value < min || value > max) {
			throw new IllegalArgumentException();
		}
	}

	public static void duplicateValidate(final String console) {
		long distinctCount = IntStream.range(INDEX_OF_START, console.length())
			.map(console::charAt)
			.distinct()
			.count();

		if (console.length() != distinctCount) {
			throw new IllegalArgumentException();
		}
	}

}
