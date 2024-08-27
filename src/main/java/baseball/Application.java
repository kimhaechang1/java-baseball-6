package baseball;

import baseball.ver1.GameManager;
import baseball.ver2.controller.BaseballController;

public class Application {
    public static void main(String[] args) {
        // TODO: 숫자 야구 게임 구현
        // final GameManager gameManager = GameManager.getInstance();

        final BaseballController controller = BaseballController.getInstance();
        controller.start();
    }
}
