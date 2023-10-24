package baseball.controller;

import baseball.model.Numbers;
import baseball.view.InputView;
import baseball.view.OutputView;
import java.util.List;

public class BaseBallController {
    private int ballCount;
    private int strikeCount;

    private String playerInput;

    private List<Integer> playerNumbers;
    private List<Integer> answerNumbers;
    private static final String BALL = "볼";
    private static final String STRIKE = "스트라이크";
    private static final String NOTHING = "낫싱";
    private static final OutputView OUTPUT_VIEW = new OutputView();
    private static final InputView INPUT_VIEW = new InputView();
    private static final InputValidator INPUT_VALIDATOR = new InputValidator();
    private static final Numbers PLAYER_NUMBERS = new Numbers();
    private static final Numbers ANSWER_NUMBERS = new Numbers();
    private static final RandomNumberGenerator RANDOM_NUMBER_GENERATOR = new RandomNumberGenerator();
    private static final PlayerInputParser PLAYER_INPUT_PARSER = new PlayerInputParser();

    public void startBaseBallGame() {
        OUTPUT_VIEW.printGameStartMessage();
        initGame();
        runGame();
    }

    private void runGame() {
        while (!isGameCleared()) {
            OUTPUT_VIEW.printPlayerNumberInputPrompt();
            playerInput = INPUT_VIEW.enterPlayerInput();
            INPUT_VALIDATOR.isValidNumber(playerInput);
            PLAYER_NUMBERS.setNumbers(PLAYER_INPUT_PARSER.ParsePlayerInput(playerInput));
            playerNumbers = PLAYER_NUMBERS.getNumbers();

            ballCount = countBall();
            strikeCount = countStrike();
            OUTPUT_VIEW.printBaseBallHint(makeHintMessage());
        }
        OUTPUT_VIEW.printGameClearMessage();
        askRestartGame();
        ChooseGameRepeatOrQuit();
    }

    private void initGame() {
        this.ballCount = 0;
        this.strikeCount = 0;
        ANSWER_NUMBERS.setNumbers(RANDOM_NUMBER_GENERATOR.GenerateRandomNumber());
        answerNumbers = ANSWER_NUMBERS.getNumbers();
    }

    private void endGame() {
        OUTPUT_VIEW.printGameEndMessage();
    }

    public int countBall() {
        int result = 0;
        for (int i = 0; i < answerNumbers.size(); i++) {
            if (playerNumbers.contains(answerNumbers.get(i)) && playerNumbers.get(i) != answerNumbers.get(i)) {
                result++;
            }
        }
        return result;
    }

    public int countStrike() {
        int result = 0;
        for (int i = 0; i < answerNumbers.size(); i++) {
            if (playerNumbers.get(i) == answerNumbers.get(i)) {
                result++;
            }
        }
        return result;
    }

    private boolean isGameCleared() {
        return strikeCount == 3;
    }


    private String makeHintMessage() {
        if (ballCount == 0 && strikeCount == 0) {
            return NOTHING;
        } else if (strikeCount == 0) {
            return ballCount + BALL;
        } else if (ballCount == 0) {
            return strikeCount + STRIKE;
        }
        return ballCount + BALL + " " + strikeCount + STRIKE;
    }

    private void askRestartGame() {
        OUTPUT_VIEW.printContinueGameChoicePrompt();
        playerInput = INPUT_VIEW.enterPlayerInput();
        INPUT_VALIDATOR.isValidContinueGameChoice(playerInput);
    }

    private void restartGame() {
        initGame();
        runGame();
    }


    private void ChooseGameRepeatOrQuit() {
        if (playerInput.equals("1")) {
            restartGame();
        } else if (playerInput.equals("2")) {
            endGame();
        }
    }
}
