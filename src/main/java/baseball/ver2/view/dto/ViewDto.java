package baseball.ver2.view.dto;

public class ViewDto {

	private static final String EMPTY_STRING = "";

	public static class BallDto {

		private static final String forView = "볼";

		private final int count;
		public BallDto(int count) {
			this.count = count;
		}

		public int getCount() {
			return count;
		}

		public String toString() {
			if (count == 0)
				return  EMPTY_STRING;
			return count + forView;
		}
	}

	public static class StrikeDto {

		private static final String forView = "스트라이크";

		private final int count;
		public StrikeDto(int count) {
			this.count = count;
		}

		public int getCount() {
			return count;
		}

		public String toString() {
			if (count == 0)
				return  EMPTY_STRING;
			return count + forView;
		}
	}
}
