package ch.zuehlke.fullstack.hackathon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void test() {
        final Position[] expected = {new Position(2, 1), new Position(2, 3), new Position(1, 2), new Position(3, 2)};

        final Position[] actual = new Position(2, 2).getSurroundingsPositions();

        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }
}