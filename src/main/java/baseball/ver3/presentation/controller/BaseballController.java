package baseball.ver3.presentation.controller;

import baseball.ver3.presentation.dto.PlayerNumberDto;
import baseball.ver3.presentation.dto.ScoreDto;
import baseball.ver3.service.BaseballService;
import baseball.ver3.presentation.view.InputView;
import baseball.ver3.presentation.view.PrintView;

public class BaseballController {

	private final InputView inputView;
	private final PrintView printView;
	private final BaseballService baseballService;

	private static final int RESUME = 1;

	private static final int NOTHING = 0;

	private static final int ZERO_VALUE = 0;

	private BaseballController() {
		this.baseballService = BaseballService.getInstance();
		this.inputView = InputView.getInstance();
		this.printView = PrintView.getInstance();
	}

	public static BaseballController getInstance() {
		return new BaseballController();
	}

	public void turnOn() {
		// TODO: Controller를 시작할 때 사용되는것, 즉 게임기를 작동시켰을 때 나와야 하는 멘트를 View의 행동으로 구현
		printView.showStartView();
		do {
			start();
		} while(checkQuit());
	}

	private void start() {
		gameInitialize();
		playSet();
		closeGame();
	}

	private void playSet() {
		do {
			printView.showPlayerInputGuide();
			final PlayerNumberDto playerNumberDto = PlayerNumberDto.of(inputView.getPrediceNumbers());
			final ScoreDto scoreDto = baseballService.guess(playerNumberDto);
			scoring(scoreDto);
		} while (!baseballService.isGameover());
		printView.showGameClose();
	}

	private void gameInitialize() {
		baseballService.initialize();
	}


	private void closeGame() {
		printView.showGameResume();
	}

	private boolean checkQuit() {
		return RESUME == inputView.getResumeNumber();
	}

	private void scoring(final ScoreDto dto) {
		if (isNothing(dto)) {
			printView.showNothing();
			return;
		}

		if (dto.getBallCount() == ZERO_VALUE) {
			printView.showStrike(dto.getStrikeCount());
			return;
		}

		if (dto.getStrikeCount() == ZERO_VALUE) {
			printView.showBall(dto.getBallCount());
			return;
		}

		printView.showBallWithStrike(dto.getStrikeCount(), dto.getBallCount());

	}

	private boolean isNothing(final ScoreDto dto) {
		return dto.getStrikeCount() + dto.getBallCount() == NOTHING;
	}

}
