package ch.zuehlke.fullstack.hackathon.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static ch.zuehlke.fullstack.hackathon.model.FieldType.*;

public class StartGridGenerator {


    private final Field[][] field;

    public StartGridGenerator(int size) {
        field = emptyField(size);
    }

    private static Field[][] emptyField(int size) {
        final Field[][] field = new Field[size][size];
        for (Field[] row : field) {
            Arrays.fill(row, new Field(EMPTY));
        }
        return field;
    }

    public Grid getStartingGrid(List<Player> players, String gameName) throws InvalidArgumentException {
        final Map<Player, Position> startingPositions = getStartingPositions(players);
        return new Grid(gameName, getStartingField(startingPositions), startingPositions, false);
    }

    private Map<Player, Position> getStartingPositions(List<Player> players) throws InvalidArgumentException {
        if (players.size() == 0) {
            return Map.of();
        }
        if (players.size() == 1) {
            return Map.of(players.get(0), new Position(0, 0));
        }
        if (players.size() == 2) {
            return Map.of(players.get(0), new Position(0, 1), players.get(1), new Position(1, 0));
        }
        throw new InvalidArgumentException("invalid number of players: " + players.size() + ", cannot distribute starting positions");
    }

    private Field[][] getStartingField(Map<Player, Position> playerPositions) {
        field[9][9] = new Field(FLAG);
        addPlayers(playerPositions);
        addBlocks();
        return field;
    }

    private void addPlayers(Map<Player, Position> playerPositions) {
        for (Map.Entry<Player, Position> entry : playerPositions.entrySet()) {
            Player player = entry.getKey();
            Position position = entry.getValue();
            field[position.x()][position.y()] = new Field(PLAYER, player.name());
        }
    }

    private void addBlocks() {
        field[5][5] = new Field(BORDER);
        field[5][6] = new Field(BORDER);
        field[4][7] = new Field(BORDER);
        field[9][5] = new Field(BORDER);
        field[8][6] = new Field(BORDER);
        field[7][7] = new Field(BORDER);
        field[5][9] = new Field(BORDER);
    }
}
