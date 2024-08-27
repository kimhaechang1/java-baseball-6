package baseball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import camp.nextstep.edu.missionutils.Randoms;

public class Computer {

	private final List<Integer> myCards;
	private final Set<Integer> kindOfCards;

	public Computer() {
		myCards = pickComputerCard();
		kindOfCards = Collections.unmodifiableSet(new HashSet<>(myCards));
	}

	private List<Integer> pickComputerCard() {
		List<Integer> temp = new ArrayList<>();
		temp.add(Randoms.pickNumberInRange(1, 9));
		while(temp.size() < 3){
			int randomNumber = Randoms.pickNumberInRange(1, 9);
			if (!temp.contains(randomNumber)) {
				temp.add(randomNumber);
			}
		}
		return Collections.unmodifiableList(temp);
	}

	public boolean scoring(final List<Integer> playerNumbers) {

		HashMap<BaseballState, Integer> scoreBoard = new HashMap<>();
		IntStream.range(0, playerNumbers.size())
			.boxed()
			.forEach(idx -> {
				Optional<BaseballState> state = getState(playerNumbers.get(idx), myCards.get(idx));
				state.ifPresent(s -> scoreBoard.put(s, scoreBoard.getOrDefault(s, 0) + 1));
			});
		return determineAfterScore(scoreBoard);
	}

	private Optional<BaseballState> getState(final int playerNumber, final int computerNumber) {

		if (!kindOfCards.contains(playerNumber)) {
			return Optional.empty();
		}

		if (playerNumber == computerNumber) {
			return Optional.of(BaseballState.STRIKE);
		} else {
			return Optional.of(BaseballState.BALL);
		}
	}

	private boolean determineAfterScore(final HashMap<BaseballState, Integer> scoreBoard) {

		int strikeCount = scoreBoard.getOrDefault(BaseballState.STRIKE, 0);
		int ballCount = scoreBoard.getOrDefault(BaseballState.BALL, 0);

		printResultMessage(strikeCount, ballCount);

		return strikeCount == 3;
	}

	private void printResultMessage(final int strikeCount, final int ballCount) {

		StringBuilder msgBuilder = new StringBuilder();

		if (ballCount > 0) {
			msgBuilder.append(ballCount).append("볼");
		}

		if (msgBuilder.length() > 0) {
			msgBuilder.append(" ");
		}

		if (strikeCount != 0) {
			msgBuilder.append(strikeCount).append("스트라이크");
		}

		if (msgBuilder.length() == 0) {
			msgBuilder.append("낫싱");
		}

		System.out.println(msgBuilder);
	}

	private enum BaseballState { BALL, STRIKE, }
}
