package rocks.cleanstone.game.command.completion;

import rocks.cleanstone.game.command.parameter.CommandParameter;

import java.util.List;

public interface CompletableParameter<T> extends CommandParameter<T> {
    List<String> getCompletion(CompletionContext<T> context);
}
