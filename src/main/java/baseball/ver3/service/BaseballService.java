package baseball.ver3.service;

import baseball.ver3.domain.vo.Baseball;
import baseball.ver3.domain.vo.Score;
import baseball.ver3.presentation.dto.PlayerNumberDto;
import baseball.ver3.presentation.dto.ScoreDto;

public class BaseballService {

	private Baseball computer;

	private boolean isGameover;

	private static final int FULL_STIKE = 3;

	private BaseballService() {}

	public static BaseballService getInstance() {
		return new BaseballService();
	}

	public void initialize() {
		computer = Baseball.random();
		isGameover = false;
	}

	public boolean isGameover() {
		return this.isGameover;
	}

	private void changeState(final int presentGameStrike) {
		if (presentGameStrike == FULL_STIKE)
			this.isGameover = true;
	}

	public ScoreDto guess(final PlayerNumberDto playerDto) {
		Baseball playerBaseball = toBaseball(playerDto);
		Score score = computer.match(playerBaseball);
		changeState(score.getStrikeCount());
		return ScoreDto.from(score.getStrikeCount(), score.getBallCount());
	}

	private Baseball toBaseball(final PlayerNumberDto dto) {
		return Baseball.from(dto.getPlayerNumber());
	}
}
