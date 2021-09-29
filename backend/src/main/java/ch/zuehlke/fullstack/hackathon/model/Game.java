package ch.zuehlke.fullstack.hackathon.model;

import java.util.ArrayList;
import java.util.List;

public record Game(String name, List<Player> players) {
    private static final int MAX_TURNS = 200;

    public GameResult play() throws InvalidArgumentException {
        final StartGridGenerator startGridGenerator = new StartGridGenerator(10);
        final Grid startingGrid = startGridGenerator.getStartingGrid(players, name);
        return playTurns(startingGrid);
    }

    private GameResult playTurns(Grid startingGrid) throws InvalidArgumentException {
        Grid grid = startingGrid;
        String winner = "";
        List<Grid> turns = new ArrayList<>();
        for (int i = 1; i < MAX_TURNS; i++) {
            for (Player player : players) {
                Surroundings surroundings = grid.getSurroundings(player);
                System.out.println(player.name() + "has environment: " + surroundings);
                final Grid newPlayField = grid.applyMove(player, player.decideMove(surroundings), i, name);
                turns.add(newPlayField);
                grid = newPlayField;
                if (newPlayField.isLastGrid()) {
                    winner = player.name();
                    break;
                }
            }
            if (!winner.equals("")) {
                break;
            }
        }
        markLastGrid(turns);
        return new GameResult(startingGrid, turns, winner);
    }

    private void markLastGrid(List<Grid> turns) {
        final Grid lastGrid = turns.get(turns.size() - 1);
        if (!lastGrid.isLastGrid()) {
            turns.remove(lastGrid);
            turns.add(lastGrid.convertToLastGrid());
        }
    }
}
