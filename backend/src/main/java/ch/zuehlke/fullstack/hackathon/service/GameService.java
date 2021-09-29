package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class GameService {

    private final GameHostingService gameHostingService;

    @Autowired
    public GameService(GameHostingService gameHostingService) {
        this.gameHostingService = gameHostingService;
    }

    public void createOrUpdateGame(String script, String gameRoom, String playerName) throws InvalidArgumentException {
        final ScriptExecutor scriptExecutor = new ScriptExecutor();
        final Function<Surroundings, Move> moveFunction = scriptExecutor.generateMoveEvaluator(script);
        gameHostingService.addPlayer(new Player(playerName, moveFunction), gameRoom);
    }

    public GameResult getGameResultFor(String gameRoom, String player) throws InvalidArgumentException {
        System.out.println("result polled for: " + gameRoom + " / " + player);
        return gameHostingService.getFinishedGame(gameRoom, player);
    }

    public void reset(String gameRoom, String player) {
        System.out.println(player + " resets GameRoom: " + gameRoom);
        gameHostingService.reset(gameRoom);
    }
}
