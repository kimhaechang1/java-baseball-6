package baseball.ver3.presentation.dto;

public class ScoreDto {

	private final int strikeCount;

	private final int ballCount;

	private ScoreDto(final int strikeCount, final int ballCount) {
		this.strikeCount = strikeCount;
		this.ballCount = ballCount;
	}

	public static ScoreDto from(final int strikeCount, final int ballCount) {
		return new ScoreDto(strikeCount, ballCount);
	}

	public int getStrikeCount() {
		return strikeCount;
	}

	public int getBallCount() {
		return ballCount;
	}
}
