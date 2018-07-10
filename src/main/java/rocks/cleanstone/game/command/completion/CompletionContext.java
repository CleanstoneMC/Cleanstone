package rocks.cleanstone.game.command.completion;

public interface CompletionContext<T> {
    String getInput();

    Class<T> getExpectedType();
}
