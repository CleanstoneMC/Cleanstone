package rocks.cleanstone.game.command.parameter;

import rocks.cleanstone.game.command.completion.CompletionContext;

import javax.annotation.Nullable;

public interface CommandParameter<T> {
    @Nullable
    T get(CompletionContext<T> context);

    Class getParameterClass();
}
