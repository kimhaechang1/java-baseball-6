package baseball.ver3.presentation.view;

import baseball.ver2.view.dto.ViewDto;

public final class PrintView {

	/*
	 * 사용자에게 출력하는 View
	 * */

	private static final String GAME_START_MESSAGE = "숫자 야구 게임을 시작합니다.";
	private static final String PLAYER_INPUT_GUIDE_MESSAGE = "숫자를 입력해주세요 : ";
	private static final String GAME_CLOSE_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
	private static final String GAME_RESUME_MESSAGE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
	private static final String NOTTHING_MESSAGE = "낫싱";

	private static final String BLOCK = " ";

	private static final String BALL = "볼";
	private static final String STRIKE = "스트라이크";

	private static final int NOTTHING = 0;

	private PrintView() {}

	public static PrintView getInstance() {
		return new PrintView();
	}

	public void showStartView() {
		System.out.println(GAME_START_MESSAGE);
	}

	public void showPlayerInputGuide() {
		System.out.print(PLAYER_INPUT_GUIDE_MESSAGE);
	}

	public void showGameClose() {
		System.out.println(GAME_CLOSE_MESSAGE);
	}

	public void showGameResume() {
		System.out.println(GAME_RESUME_MESSAGE);
	}

	public void showNothing() {
		System.out.println(NOTTHING_MESSAGE);
	}

	public void showBallWithStrike(final int ballCount, final int strikeCount) {
		String format = String.format("%d%s %d%s", ballCount, BALL, strikeCount, STRIKE);
		System.out.println(format);
	}

	public void showStrike(final int strikeCount) {
		String format = String.format("%d%s", strikeCount, STRIKE);
		System.out.println(format);
	}

	public void showBall(final int ballCount) {
		String format = String.format("%d%s", ballCount, BALL);
		System.out.println(format);
	}

}
