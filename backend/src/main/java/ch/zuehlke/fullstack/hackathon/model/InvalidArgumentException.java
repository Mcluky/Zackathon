package ch.zuehlke.fullstack.hackathon.model;

public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
