package baseball;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum GameManagerState {

	KEEP(1, true),
	QUIT(2, false)
	;

	private static final
	Map<Integer, GameManagerState> stateList = Arrays.stream(values())
												.collect(Collectors.toMap(
													state -> state.value,
													state -> state
												));


	private GameManagerState(int value, boolean quitFlag) {
		this.value = value;
		this.quitFlag = quitFlag;
	}

	private final int value;

	private final boolean quitFlag;

	public static boolean determineQuit(int flag) {
		return stateList.get(flag).quitFlag;
	}
}
