package ch.zuehlke.fullstack.hackathon.service;


import ch.zuehlke.fullstack.hackathon.model.FieldObjects;
import ch.zuehlke.fullstack.hackathon.model.Move;
import ch.zuehlke.fullstack.hackathon.model.Moves;
import ch.zuehlke.fullstack.hackathon.model.Surroundings;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

import java.util.function.Function;

public class ScriptExecutor {


    public ScriptExecutor() {

    }

    public Function<Surroundings, Move> generateMoveEvaluator(String script) {
        Context polyglot = Context.newBuilder("js").allowHostAccess(HostAccess.ALL).build();
        polyglot.getBindings("js").putMember("moves", new Moves());
        polyglot.getBindings("js").putMember("fieldObjects", new FieldObjects());
        final Value playerFunction = polyglot.eval("js", script);

        return surroundings -> {
            polyglot.getBindings("js").putMember("surroundings", surroundings);
            final Value move = playerFunction.execute();
            return move.as(Move.class);
        };
    }
}
