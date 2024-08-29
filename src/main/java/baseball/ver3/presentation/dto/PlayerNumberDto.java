package baseball.ver3.presentation.dto;

import java.util.Collections;
import java.util.List;

public class PlayerNumberDto {

	private final List<Integer> playerNumber;

	private PlayerNumberDto(final List<Integer> inputPlayerNumberList) {
		this.playerNumber = Collections.unmodifiableList(inputPlayerNumberList);
	}

	public List<Integer> getPlayerNumber() {
		return playerNumber;
	}

	public static PlayerNumberDto of(final List<Integer> inputPlayerNumberList) {
		return new PlayerNumberDto(inputPlayerNumberList);
	}
}
