package baseball.ver2.controller;

import baseball.ver2.model.Baseball;
import baseball.ver2.model.Score;
import baseball.ver2.model.service.BaseballService;
import baseball.ver2.view.InputView;
import baseball.ver2.view.PrintView;

public class BaseballController {

	private final InputView iv;
	private final PrintView pv;
	private final BaseballService baseballService;

	private static final int RESUME = 1;
	private static final int STOP = 2;

	private BaseballController() {
		this.baseballService = BaseballService.getInstance();
		this.iv = InputView.getInstance();
		this.pv = PrintView.getInstance();
	}

	public static BaseballController getInstance() {
		return new BaseballController();
	}

	public void start() {
		pv.showStartView();
		do {
			serviceStart();
		} while(checkQuit());
	}

	private void serviceStart() {
		baseballService.initialize();
		do {
			pv.showPlayerInputGuide();
			Baseball player = Baseball.from(iv.getPrediceNumbers());
			final Score result = baseballService.guess(player);
			pv.showGameScore(result.toViewStrikeDto(), result.toViewBallDto());
		} while (!baseballService.isGameover());
		pv.showGameClose();
		pv.showGameResume();
	}

	private boolean checkQuit() {
		return RESUME == iv.getResumeNumber();
	}

}
