package baseball;

public class GameManager {

	private final Player player;

	private GameManager() {
		player = new Player();
		do {
			playGame();
		} while (checkQuit());
	}

	private static GameManager instance;

	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}

	private void playGame() {

		Game game = new Game(player);
		game.start();
	}

	private boolean checkQuit() {
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
		return GameManagerState.determineQuit(player.getGameManagerState());
	}


}
