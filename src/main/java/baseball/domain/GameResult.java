package baseball.domain;

public class GameResult {
    private int ball;
    private int strike;

    public GameResult() {
    }

    public void reset() {
        ball = 0;
        strike = 0;
    }

    public int getBall() {
        return ball;
    }

    public int getStrike() {
        return strike;
    }

    public void plusBall() {
        ball++;
    }

    public void plusStrike() {
        strike++;
    }

}
