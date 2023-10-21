package baseball;

import static org.assertj.core.api.Assertions.assertThat;

import baseball.controller.PlayerInputParser;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayerInputParserTest {
    PlayerInputParser playerInputParser = new PlayerInputParser();

    @Test
    void testParsePlayerInput() {
        String validPlayerInput = "465";
        List<Integer> expectedList = new ArrayList<>() {{
            add(4);
            add(6);
            add(5);
        }};

        assertThat(playerInputParser.ParsePlayerInput(validPlayerInput)).isEqualTo(expectedList);
    }
}
