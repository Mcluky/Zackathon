package ch.zuehlke.fullstack.hackathon.model;

import java.util.function.Function;

public record Player(String name, Function<Surroundings, Move> moveFunction) {

    public Player(String name) {
            this(name, null);
    }

    public Move decideMove(Surroundings surroundings) {
        return moveFunction.apply(surroundings);
    }
}
