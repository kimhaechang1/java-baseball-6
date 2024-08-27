package baseball.ver2.model;

import baseball.ver2.view.dto.ViewDto;

public class Score {

	private final Strike strike;

	private final Ball ball;

	private Score(final int strikeCount, final int ballCount) {
		this.strike = new Strike(strikeCount);
		this.ball = new Ball(ballCount);
	}

	public static Score of(final int strikeCount, final int ballCount) {
		return new Score(strikeCount, ballCount);
	}

	public int getStrikeCount() {
		return strike.getCount();
	}

	public ViewDto.StrikeDto toViewStrikeDto() {
		return this.strike.toViewDto();
	}

	public ViewDto.BallDto toViewBallDto() {
		return this.ball.toViewDto();
	}

	static class Strike {
		private final int count;

		public Strike(int count) {
			this.count = count;
		}

		public int getCount() {
			return count;
		}

		public ViewDto.StrikeDto toViewDto() {
			return new ViewDto.StrikeDto(count);
		}
	}

	static class Ball {

		private final int count;

		public Ball(int count) {
			this.count = count;
		}

		public ViewDto.BallDto toViewDto() {
			return new ViewDto.BallDto(count);
		}
	}
}
