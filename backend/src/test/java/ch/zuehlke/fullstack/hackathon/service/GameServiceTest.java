package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.GameResult;
import ch.zuehlke.fullstack.hackathon.model.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private static final String script1 = """
            (function() {
                if (surroundings.up() === fieldObjects.FLAG) {
                    return moves.UP;
                }
                            
                return moves.DOWN;
                })
            """;
    private static final String script2 = """
            (function() {
                const allSurroundings = [surroundings.up(), surroundings.down(), surroundings.left(), surroundings.right()]
                
                if (surroundings.up() === fieldObjects.FLAG) {
                    return moves.UP;
                }
                            
                return moves.DOWN;
                })
            """;

    @Test
    void test_game() throws InvalidArgumentException {
        final GameService testee = new GameService(new GameHostingService());

        final String gameRoom = "SomeRoom";
        testee.createOrUpdateGame(script1, gameRoom, "Player1");
        final GameResult gameResultUnstarted = testee.getGameResultFor(gameRoom, "Player1");
        testee.createOrUpdateGame(script1, gameRoom, "Player2");
        final GameResult gameResultFinished = testee.getGameResultFor(gameRoom, "Player1");

        assertTrue(gameResultUnstarted.turns().isEmpty());
        assertEquals(400, gameResultFinished.turns().size());
        assertEquals("", gameResultFinished.winner());
        assertFalse(gameResultFinished.turns().isEmpty());

    }

}