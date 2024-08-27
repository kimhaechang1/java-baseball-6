package baseball;

import java.util.List;

public class Game {

	private final Player player;
	private final Computer computer;

	private Game(final Player player) {
		this.player = player;
		this.computer = Computer.getInstance();
	}

	public static Game getInstance(final Player player) {
		return new Game(player);
	}

	public void start() {
		List<Integer> playerBaseballNumber;
		do {
			System.out.print("숫자를 입력해주세요 : ");
			playerBaseballNumber = player.getBaseballNumber();
		} while (!computer.scoring(playerBaseballNumber));
		System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
	}
}
