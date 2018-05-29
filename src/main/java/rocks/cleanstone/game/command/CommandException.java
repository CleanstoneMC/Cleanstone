package rocks.cleanstone.game.command;

public class CommandException extends RuntimeException {

    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable throwable) {
        super(throwable);
    }

    public CommandException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
