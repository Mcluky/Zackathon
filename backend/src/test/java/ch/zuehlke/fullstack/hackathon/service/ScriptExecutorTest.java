package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.Move;
import ch.zuehlke.fullstack.hackathon.model.Surroundings;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static ch.zuehlke.fullstack.hackathon.model.FieldType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ScriptExecutorTest {

    @Test
    void generateMoveEvaluator() {
        final Function<Surroundings, Move> moveFunction = new ScriptExecutor().generateMoveEvaluator(
                """
                        (function() {
                        if (surroundings.up() === fieldObjects.FLAG) {
                            return moves.UP;
                        }
                                            
                        return moves.DOWN;
                        })
                        """
        );

        assertEquals(Move.UP, moveFunction.apply(new Surroundings(FLAG, EMPTY, FLAG, FLAG)));
        assertEquals(Move.DOWN, moveFunction.apply(new Surroundings(FLAG, EMPTY, PLAYER, FLAG)));
    }

}