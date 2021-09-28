package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Optional.ofNullable;

@Service
public class GameHostingService {

    private final Map<String, Game> games;
    private final Map<String, GameResult> finishedGames;

    public GameHostingService() {
        this.games = new HashMap<>();
        this.finishedGames = new HashMap<>();
    }

    public void addPlayer(Player player, String gameName) throws InvalidArgumentException {
        final Optional<Game> optionalGame = ofNullable(games.get(gameName));
        if (optionalGame.isPresent()) {
            final Game updatedGame = addPlayerToExistingGame(player, gameName, optionalGame.get());
            games.put(gameName, updatedGame);
            System.out.println("added player: " + player.name() + " to game: " + gameName);
            System.out.println("playing game");
            playGame(updatedGame);
        } else {
            games.put(gameName, new Game(gameName, List.of(player)));
            System.out.println("game: " + gameName + " created with player: " + player.name());
            System.out.println("waiting for second player");
        }
    }

    private Game addPlayerToExistingGame(Player player, String gameName, Game existingGame) throws InvalidArgumentException {
        List<Player> existingPlayers = new ArrayList<>(List.copyOf(existingGame.players()));
        if (existingGame.players().contains(player)) {
            throw new InvalidArgumentException("Player already exists");
        }
        existingPlayers.add(player);
        return new Game(gameName, existingPlayers);
    }

    public void playGame(Game game) throws InvalidArgumentException {
        final GameResult gameResult = game.play();

        System.out.println("finished playing game: " + game.name());
        finishedGames.put(game.name(), gameResult);
        games.remove(game.name());
    }

    public GameResult getFinishedGame(String gameName, String playerName) throws InvalidArgumentException {
        return ofNullable(finishedGames.get(gameName)).orElse(getUnstartedGameFor(playerName));
    }

    private GameResult getUnstartedGameFor(String player) throws InvalidArgumentException {
        return new GameResult(Grid.getStartingGrid(List.of(new Player(player, null))), new ArrayList<>(), "");
    }


}
