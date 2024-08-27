package baseball.ver1;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class GameManager {

	private final Player player;

	private GameManager() {
		player = Player.getInstance();
		do {
			playGame();
		} while (checkQuit());
	}

	public static GameManager getInstance() {
		return new GameManager();
	}

	private void playGame() {

		Game game = Game.getInstance(player);
		game.start();
	}

	private boolean checkQuit() {
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
		return State.determineQuit(player.getGameManagerState());
	}


	private enum State {
		KEEP(1, true),
		QUIT(2, false)
		;

		private static final Map<Integer, State> stateList = Arrays.stream(values())
			.collect(Collectors.toMap(
				state -> state.value,
				state -> state
			));


		private State(int value, boolean quitFlag) {
			this.value = value;
			this.quitFlag = quitFlag;
		}

		private final int value;

		private final boolean quitFlag;

		public static boolean determineQuit(int flag) {
			return stateList.get(flag).quitFlag;
		}
	}

}
