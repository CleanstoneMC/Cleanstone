package rocks.cleanstone.game.command.completion;

public class SimpleCompletionContext<T> implements CompletionContext<T> {
    private final String input;
    private final Class<T> expectedType;

    public SimpleCompletionContext(String input, Class<T> expectedType) {
        this.input = input;
        this.expectedType = expectedType;
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public Class<T> getExpectedType() {
        return expectedType;
    }
}
