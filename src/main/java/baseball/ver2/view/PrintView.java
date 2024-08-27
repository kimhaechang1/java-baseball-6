package baseball.ver2.view;

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
	private static final int FULL_STRIKE = 3;

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

	public void showGameScore(final ViewDto.StrikeDto strikeDto, final ViewDto.BallDto ballDto) {
		if (isNothing(strikeDto.getCount(), ballDto.getCount())) {
			System.out.println(NOTTHING_MESSAGE);
			return;
		}

		final StringBuilder msgBuilder = new StringBuilder();
		msgBuilder.append(ballDto);
		if (!msgBuilder.isEmpty()) {
			msgBuilder.append(BLOCK);
		}
		msgBuilder.append(strikeDto);

		System.out.println(msgBuilder);
	}

	private boolean isNothing(final int strikeCount, final int ballCount) {
		return (strikeCount + ballCount) == NOTTHING;
	}

	private boolean isGameClose(final int strikeCount) {
		return strikeCount == FULL_STRIKE;
	}


}
