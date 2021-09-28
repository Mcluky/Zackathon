package ch.zuehlke.fullstack.hackathon.model;

import java.util.function.Function;

public record Player(String name, Function<Surroundings, Move> moveFunction) {

    public Move decideMove(Surroundings surroundings) {
        return moveFunction.apply(surroundings);
    }
}
