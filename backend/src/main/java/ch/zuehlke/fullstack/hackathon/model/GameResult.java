package ch.zuehlke.fullstack.hackathon.model;

import java.util.List;

public record GameResult(Grid startGrid, List<Grid> turns, String winner) {

}
