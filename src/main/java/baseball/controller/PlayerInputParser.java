package baseball.controller;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerInputParser {
    public List<Integer> ParsePlayerInput(String validPlayerInput) {
        List<Integer> parsedNumbers = validPlayerInput.chars()
                .mapToObj(Character::getNumericValue)
                .collect(Collectors.toList());
        return parsedNumbers;
    }
}
