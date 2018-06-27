package rocks.cleanstone.game.command.completion;

import java.util.List;

public interface CompletableValue<T> {
    List<String> getCompletion(Class<T> parameterClass, String message);
}
