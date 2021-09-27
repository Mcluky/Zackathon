package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.Field;
import ch.zuehlke.fullstack.hackathon.model.GameResult;
import ch.zuehlke.fullstack.hackathon.model.Grid;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static ch.zuehlke.fullstack.hackathon.model.FieldType.*;

@Service
public class GameService {

    public void createOrUpdateGame(String script, String gameRoom, String player) {
        System.out.println("game created with: " + script + " / " + gameRoom + " / " + player);
    }

    public GameResult getGameResultFor(String gameRoom, String player) {
        System.out.println("result polled for: " + gameRoom + " / " + player);
        return new GameResult(new Grid("startingGrid", getStartingField()), new Grid[0]);
    }


    private Field[][] getStartingField() {
        final Field[][] field = new Field[10][10];
        for (Field[] row : field) {
            Arrays.fill(row, new Field(EMPTY));
        }
        field[0][0] = new Field(FLAG);
        field[9][9] = new Field(FLAG);
        field[3][3] = new Field(PLAYER);
        field[5][5] = new Field(PLAYER);
        return field;
    }
}
