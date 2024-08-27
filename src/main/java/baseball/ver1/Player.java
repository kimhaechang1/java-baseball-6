package baseball;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import camp.nextstep.edu.missionutils.Console;

public class Player {

	private Player() {}

	public static Player getInstance() {
		return new Player();
	}

	public List<Integer> getBaseballNumber() {

		return toBaseballNumber(validate(Console.readLine(), 3));
	}

	private List<Integer> toBaseballNumber(String readLine) {

		List<Integer> playerNumbers = IntStream.range(0, readLine.length())
			.map(idx -> Character.getNumericValue(readLine.charAt(idx)))
			.boxed()
			.collect(Collectors.toList());

		return Collections.unmodifiableList(playerNumbers);
	}

	public int getGameManagerState() {
		// TODO: validate void 만들기
		return Character.getNumericValue(validate(Console.readLine(), 1, 2));
	}

	private String validate(final String readLine, final int length) throws IllegalArgumentException {
		if (readLine.length() != length) throw new IllegalArgumentException("입력값의 길이가 "+length+" 여야 합니다.");
		return readLine;
	}

	private Character validate(final String readLine, final int min, final int max) throws IllegalArgumentException {
		String filtered = validate(readLine, 1);
		if (inRange(min, max, filtered.charAt(0) - '0')) {
			return filtered.charAt(0);
		}
		throw new IllegalArgumentException("잘못된 입력값 입니다.");
	}

	private boolean inRange(final int min, final int max, final int value) {
		return min <= value && value <= max;
	}

}
