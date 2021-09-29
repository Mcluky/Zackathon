package ch.zuehlke.fullstack.hackathon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void test() {
        final Position[] expected = {new Position(2, 1), new Position(2, 3), new Position(1, 2), new Position(3, 2)};

        final Position[] actual = new Position(2, 2).getSurroundingsPositions();

        assertEquals(expected, actual);
    }
}