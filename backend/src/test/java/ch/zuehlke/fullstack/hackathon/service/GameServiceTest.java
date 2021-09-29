package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.GameResult;
import ch.zuehlke.fullstack.hackathon.model.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameServiceTest {

    private static final String script1 = """
            (function() {
                var random = Math.floor(Math.random() * (4 - 1 + 1) + 1)
                if (random === 1) {
                    return moves.DOWN;
                }
                if (random === 2) {
                    return moves.DOWN;
                }
                if (random === 3) {
                    return moves.RIGHT;
                }
                if (random === 4) {
                    return moves.RIGHT;
                }
            })
            """;
    private static final String script2 = """
            (function() {
                var random = Math.floor(Math.random() * (4 - 1 + 1) + 1)
                if (random === 1) {
                    return moves.UP;
                }
                if (random === 2) {
                    return moves.LEFT;
                }
                if (random === 3) {
                    return moves.UP;
                }
                if (random === 4) {
                    return moves.LEFT;
                }
            })
            """;

    @Test
    void test_game() throws InvalidArgumentException {
        final GameService testee = new GameService(new GameHostingService());

        final String gameRoom = "SomeRoom";
        testee.createOrUpdateGame(script1, gameRoom, "Player1");
        final GameResult gameResultUnstarted = testee.getGameResultFor(gameRoom, "Player1");
        testee.createOrUpdateGame(script2, gameRoom, "Player2");
        final GameResult gameResultFinished = testee.getGameResultFor(gameRoom, "Player1");

        System.out.println("finished in " + gameResultFinished.turns().size() + " turns");
        assertTrue(gameResultUnstarted.turns().isEmpty());
        assertFalse(gameResultFinished.turns().isEmpty());
    }

}