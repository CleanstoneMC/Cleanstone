package rocks.cleanstone.game.command.parameter;

import javax.annotation.Nullable;
import rocks.cleanstone.game.command.completion.CompletionContext;

public interface CommandParameter<T> {
    @Nullable
    T get(CompletionContext<T> context);

    Class getParameterClass();
}
