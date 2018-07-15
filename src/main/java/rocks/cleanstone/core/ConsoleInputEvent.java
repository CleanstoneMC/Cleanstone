package rocks.cleanstone.core;

public class ConsoleInputEvent {
    private final String input;

    public ConsoleInputEvent(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
