package rocks.cleanstone.console.legacy;

/**
 * Text input which originates from the server console.
 */
public class ConsoleInputEvent {
    private final String input;

    public ConsoleInputEvent(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
