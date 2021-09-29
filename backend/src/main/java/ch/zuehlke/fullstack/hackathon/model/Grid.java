package ch.zuehlke.fullstack.hackathon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.zuehlke.fullstack.hackathon.model.FieldType.*;
import static java.util.Optional.ofNullable;

public record Grid(String name, Field[][] field, Map<Player, Position> playerPositions, boolean isLastGrid) {

    @JsonIgnore
    public Grid applyMove(Player player, Move move) throws InvalidArgumentException {
        final Position pos = getPosition(player);
        final Position newPosition = pos.getNewPosition(move);
        Field desiredField = getFieldFor(newPosition);
        if (desiredField.moveToIsPossible()) {
            return movePlayer(player, pos, newPosition, desiredField);
        }
        System.out.println(player.name() + " bumped into a wall (/.-)");
        return this;
    }

    private Position getPosition(Player player) throws InvalidArgumentException {
        return ofNullable(playerPositions.get(player)).orElseThrow(() -> new InvalidArgumentException("trying to apply move for player that is not in the game"));
    }

    private Field getFieldFor(Position newPosition) {
        try {
            return field[newPosition.x()][newPosition.y()];
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Field(BORDER);
        }
    }

    private Grid movePlayer(Player player, Position pos, Position newPosition, Field desiredField) {
        Field[][] playground = copyPlayground();
        boolean isFinalGrid = desiredField.isFlag();
        playground[pos.x()][pos.y()] = new Field(EMPTY);
        playground[newPosition.x()][newPosition.y()] = new Field(PLAYER, player.name());
        Map<Player, Position> newPlayerPositions = new HashMap<>(Map.copyOf(this.playerPositions));
        newPlayerPositions.put(player, newPosition);
        System.out.println(player.name() + " moved to " + newPosition + " onto " + field[newPosition.x()][newPosition.y()].type().name());
        return new Grid(name, playground, newPlayerPositions, isFinalGrid);
    }

    private Field[][] copyPlayground() {
        Field[][] playGround = new Field[field.length][field[0].length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                playGround[i][j] = field[i][j];
            }
        }
        return playGround;
    }

    @JsonIgnore
    public Surroundings getSurroundings(Player player) throws InvalidArgumentException {
        final Position position = getPosition(player);
        final Position[] surroundingsPositions = position.getSurroundingsPositions();
        if (surroundingsPositions.length != 4) {
            throw new InvalidArgumentException("Internal logic error: got " + surroundingsPositions.length + " surrounding positions for player: " + player.name());
        }
        return new Surroundings(
                getFieldFor(surroundingsPositions[0]).type(),
                getFieldFor(surroundingsPositions[1]).type(),
                getFieldFor(surroundingsPositions[2]).type(),
                getFieldFor(surroundingsPositions[3]).type());
    }

    @Override
    public String toString() {
        final StringBuilder fieldBuilder = new StringBuilder("####################\n " +name + ":\n |");
        for (Field[] row : field) {
            for (Field cell: row) {
                fieldBuilder.append(cell.getSpacedType());
                fieldBuilder.append(" | ");
            }
            fieldBuilder.append("\n |");
        }
        fieldBuilder.append("######################");
        return fieldBuilder.toString();
    }
}
