package rocks.cleanstone.game.command.completion;

import java.util.List;
import rocks.cleanstone.game.command.parameter.CommandParameter;

public interface CompletableParameter<T> extends CommandParameter<T> {
    List<String> getCompletion(CompletionContext<T> context);
}
