package osu.damek.usedcars.exception;

public class NotOwnerException extends RuntimeException{
    public NotOwnerException(String message) {
        super(message);
    }
}

