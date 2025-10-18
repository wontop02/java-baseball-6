package baseball.view;

public class OutputView {

    public void hintMessage(int ball, int strike) {
        if (ball == 0 && strike == 0) {
            System.out.println("낫싱");
            return;
        }

        if (ball != 0)
            System.out.print(ball + "볼 ");

        if (strike != 0)
            System.out.print(strike + "스트라이크");

        System.out.println();
    }

    public void successMessage(int numberLength) {
        System.out.println(numberLength + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }
}
