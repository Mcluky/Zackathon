package ch.zuehlke.fullstack.hackathon.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridTest {

    @Test
    void testToString() throws InvalidArgumentException {
        String expected = """
                ####################
                 startingGrid:
                 |EMPTY  | PLAYER | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  |\s
                 |PLAYER | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  |\s
                 |EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  |\s
                 |EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  |\s
                 |EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | BORDER | EMPTY  | EMPTY  |\s
                 |EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | BORDER | BORDER | EMPTY  | EMPTY  | EMPTY  |\s
                 |EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | BORDER | EMPTY  |\s
                 |EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | BORDER | EMPTY  | EMPTY  |\s
                 |EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | BORDER | EMPTY  | EMPTY  | EMPTY  |\s
                 |EMPTY  | EMPTY  | EMPTY  | EMPTY  | EMPTY  | BORDER | EMPTY  | EMPTY  | EMPTY  | FLAG   |\s
                 |######################""";
        final Grid startingGrid = new StartGridGenerator(10).getStartingGrid(List.of(new Player("Dude"), new Player("Dudette")));
        assertEquals(expected, startingGrid.toString());
    }
}