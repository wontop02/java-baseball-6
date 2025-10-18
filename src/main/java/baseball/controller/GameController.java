package baseball.controller;

import baseball.domain.GameResult;
import baseball.view.InputView;
import baseball.view.OutputView;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    final int NUMBER_LENGTH = 3;

    public void start(GameResult gameResult,
                      InputView inputView,
                      OutputView outputView) {

        // 컴퓨터가 숫자 생성
        List<Integer> computer = createNumbers();

        while(true) {
            // 게임 결과 초기화
            gameResult.reset();

            // System.out.println("확인용: " + computer);
            // 사용자 입력
            String input = inputView.requestNumbers();
            List<Integer> player = convertToIntegerList(input);

            // 비교
            compareNumbers(computer, player, gameResult);

            outputView.hintMessage(gameResult.getBall(), gameResult.getStrike());

            if (gameResult.getStrike() == NUMBER_LENGTH) {
                outputView.successMessage(NUMBER_LENGTH);
                String inputNum = inputView.requestRetryOrFinish();
                int retryOrFinish = validateRetryOrFinish(inputNum);
                if (retryOrFinish == 1) {
                    computer = createNumbers();
                    continue;
                }
                return;
            }
        }
    }

    // 재시작, 종료 입력 검증
    public int validateRetryOrFinish(String input) {
        int number = Integer.parseInt(input);
        if (number != 1 && number != 2)
            throw new IllegalArgumentException("1, 2 중 하나의 숫자만 입력할 수 있습니다.");
        return number;
    }

    // 3자리 숫자 입력 검증, input String -> List<Integer>로 변환
    public List<Integer> convertToIntegerList(String input) {
        // 문자 검증
        if (!input.matches("[1-9]+"))
            throw new IllegalArgumentException("1-9 사이의 숫자만 입력할 수 있습니다.");

        String[] arr = input.split("");
        // 길이 검증
        if (arr.length != NUMBER_LENGTH)
            throw new IllegalArgumentException("숫자는 " + NUMBER_LENGTH + "자리여야 합니다.");

        List<Integer> player = new ArrayList<>();
        for (String str : arr) {
            int num = Integer.parseInt(str);
            // 중복 숫자 검증
            if (player.contains(num))
                throw new IllegalArgumentException("중복된 숫자는 입력할 수 없습니다.");
            player.add(num);
        }
        return player;
    }

    // 컴퓨터의 랜덤 3자리 수 생성
    public List<Integer> createNumbers() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < NUMBER_LENGTH) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }

    // 볼, 스트라이크 판단 로직
    public void compareNumbers(List<Integer> computer,
                               List<Integer> player,
                               GameResult gameResult) {
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            int num = computer.get(i);
            if (num == player.get(i)) {
                gameResult.plusStrike();
                continue;
            }

            if (player.contains(num)) {
                gameResult.plusBall();
            }
        }
    }
}
