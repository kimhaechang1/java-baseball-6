package baseball.ver3.domain.vo;

import java.util.Objects;

public class Score {

	private final int strikeCount;
	private final int ballCount;

	private Score(final int strikeCount, final int ballCount) {
		this.strikeCount = strikeCount;
		this.ballCount = ballCount;
	}

	public static Score from(final int strikeCount, final int ballCount) {
		return new Score(strikeCount, ballCount);
	}

	public int getBallCount() {
		return ballCount;
	}

	public int getStrikeCount() {
		return strikeCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Score score = (Score)o;
		return strikeCount == score.strikeCount && ballCount == score.ballCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(strikeCount, ballCount);
	}
}
