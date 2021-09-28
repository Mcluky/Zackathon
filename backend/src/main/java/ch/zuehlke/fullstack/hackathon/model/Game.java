package ch.zuehlke.fullstack.hackathon.model;

import java.util.ArrayList;
import java.util.List;

public record Game(String name, List<Player> players) {
    private static final int MAX_TURNS = 200;

    public GameResult play() throws InvalidArgumentException {
        final Grid startingGrid = Grid.getStartingGrid(players);
        return playTurns(startingGrid);
    }

    private GameResult playTurns(Grid startingGrid) throws InvalidArgumentException {
        Grid grid = startingGrid;
        String winner = "";
        List<Grid> turns = new ArrayList<>();
        for (int i = 0; i < MAX_TURNS; i++) {
            for (Player player : players) {
                Surroundings surroundings = grid.getSurroundings(player);
                final Grid newPlayField = grid.applyMove(player, player.decideMove(surroundings));
                turns.add(newPlayField);
                grid = newPlayField;
                if (newPlayField.isLastGrid()) {
                    winner = player.name();
                    break;
                }
            }
        }
        return new GameResult(startingGrid, turns, winner);
    }
}
