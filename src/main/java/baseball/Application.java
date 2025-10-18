package baseball;

import baseball.controller.GameController;
import baseball.domain.GameResult;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Application {
    public static void main(String[] args) {
        GameResult gameResult = new GameResult();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        GameController gameController = new GameController();

        // 처음 시작할 때 한 번만 나오기 때문에 View에 포함 X
        System.out.println("숫자 야구 게임을 시작합니다.");

        gameController.start(gameResult, inputView, outputView);
    }
}
