package baseball.ver2.model.Service;

import baseball.ver2.model.Baseball;
import baseball.ver2.model.Score;

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

	public Score guess(final Baseball player) {
		Score score = computer.match(player);
		changeState(score.getStrikeCount());
		return score;
	}






}
