package baseball.ver3.domain.vo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import camp.nextstep.edu.missionutils.Randoms;

public class Baseball {

	private final int ZERO_INDEX = 0;

	private final int MAX_SIZE = 3;

	private final List<Integer> numberList;

	private final Set<Integer> numberPool;

	private Baseball(final List<Integer> numberList) {
		this.numberList = numberList;
		this.numberPool = Set.copyOf(numberList);
	}

	public static Baseball from(final List<Integer> numberList) {
		return new Baseball(Collections.unmodifiableList(numberList));
	}

	public static Baseball random() {
		return from(
			Stream.generate(() -> Randoms.pickNumberInRange(1, 9))
				.distinct()
				.limit(3)
				.toList()
		);
	}

	public boolean contain(Integer number) {
		return numberPool.contains(number);
	}

	private Integer get(int idx) {
		return this.numberList.get(idx);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Baseball that = (Baseball)o;
		return IntStream.range(ZERO_INDEX, numberList.size())
			.allMatch(idx -> Objects.equals(that.numberList.get(idx), this.numberList.get(idx)));
	}

	public Score match(Baseball other) {
		int strikeCount = 0;
		int ballCount = 0;

		for(int index = ZERO_INDEX; index < MAX_SIZE; index++) {
			if (isStrike(get(index), other.get(index))) {
				strikeCount++;
				continue;
			}
			if (isBall(other.get(index))) {
				ballCount++;
			}
		}

		return Score.from(strikeCount, ballCount);
	}

	private boolean isStrike(final int ownerValue, final int otherValue) {
		return ownerValue == otherValue;
	}

	private boolean isBall(int otherValue) {
		return contain(otherValue);
	}

	public int hashCode() {
		return Objects.hash(numberList);
	}
}
