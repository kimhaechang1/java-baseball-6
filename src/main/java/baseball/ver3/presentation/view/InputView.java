package baseball.ver3.presentation.view;

import java.util.List;
import java.util.stream.IntStream;

import baseball.ver2.view.validator.Validator;
import camp.nextstep.edu.missionutils.Console;

public class InputView {

	/*
	 * 사용자로부터 입력값을 최초로 받는 View
	 * 입력값에 대한 저수준의 유효성검사를 진행한다.
	 * */

	private static final int PARSING_START_FOR_RANGE = 0;

	private static final int RESUME_START_RANGE = 1;
	private static final int RESUME_END_RANGE = 2;

	private static final char CHAR_TO_INT_FLAG = '0';

	private InputView() {}

	public static InputView getInstance() {
		return new InputView();
	}

	public List<Integer> getPrediceNumbers() {

		String readLine = Console.readLine();

		Validator.emptyValidate(readLine);
		Validator.lengthValidate(readLine, LengthStrategy.INPUT_FOR_PREDICT_LENGTH.getLength());
		Validator.duplicateValidate(readLine);

		return IntStream.range(PARSING_START_FOR_RANGE, readLine.length())
				.map(idx -> readLine.charAt(idx) - CHAR_TO_INT_FLAG)
				.boxed()
				.toList();
	}

	public int getResumeNumber() {

		String readLine = Console.readLine();

		Validator.emptyValidate(readLine);
		Validator.lengthValidate(readLine, LengthStrategy.INPUT_FOR_RESUME_LENGTH.getLength());
		Validator.integerRangeValidate(RESUME_START_RANGE, RESUME_END_RANGE, readLine);

		return readLine.charAt(PARSING_START_FOR_RANGE) - CHAR_TO_INT_FLAG;
	}

	public enum LengthStrategy {
		INPUT_FOR_PREDICT_LENGTH(3),
		INPUT_FOR_RESUME_LENGTH(1)
		;
		private final int length;

		private LengthStrategy(int length) {
			this.length = length;
		}

		public int getLength() {
			return this.length;
		}
	}
}
