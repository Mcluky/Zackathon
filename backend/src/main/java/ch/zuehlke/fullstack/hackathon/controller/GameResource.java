package ch.zuehlke.fullstack.hackathon.controller;

import ch.zuehlke.fullstack.hackathon.model.GameResult;
import ch.zuehlke.fullstack.hackathon.model.InvalidArgumentException;
import ch.zuehlke.fullstack.hackathon.service.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class GameResource {

    private final GameService gameService;

    @Autowired
    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }

    @ApiOperation(value = "Get game result",
            notes = "This can be polled to receive game results")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned game result"),
            @ApiResponse(code = 500, message = "If something fails internally")})
    @GetMapping("game-room/{game-room}/player/{player}/result")
    public GameResult getResult(@PathVariable("game-room") String gameRoom, @PathVariable("player") String player) throws InvalidArgumentException {
        return gameService.getGameResultFor(gameRoom, player);
    }

    @ApiOperation(value = "Post script",
            notes = "This can be used ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added player to game"),
            @ApiResponse(code = 500, message = "If something fails internally")})
    @PostMapping("game-room/{game-room}/player/{player}/register")
    public void postScript(String script, @PathVariable("game-room") String gameRoom, @PathVariable("player") String player) throws InvalidArgumentException {
        gameService.createOrUpdateGame(script, gameRoom, player);
    }
}
