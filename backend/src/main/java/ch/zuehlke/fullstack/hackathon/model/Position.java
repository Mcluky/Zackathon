package ch.zuehlke.fullstack.hackathon.model;

import static ch.zuehlke.fullstack.hackathon.model.Move.*;

public record Position(int x, int y) {

    public Position getNewPosition(Move move) {
        return switch (move) {
            case UP -> new Position(x - 1, y);
            case DOWN -> new Position(x + 1, y);
            case LEFT -> new Position(x, y - 1);
            case RIGHT -> new Position(x, y + 1);
        };
    }

    public Position[] getSurroundingsPositions() {
        return new Position[]{getNewPosition(LEFT), getNewPosition(RIGHT), getNewPosition(UP), getNewPosition(DOWN)};
    }
}
