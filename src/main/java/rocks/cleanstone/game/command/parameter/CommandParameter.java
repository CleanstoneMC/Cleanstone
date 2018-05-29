package rocks.cleanstone.game.command.parameter;

import javax.annotation.Nullable;

public interface CommandParameter<T> {
    @Nullable
    T get(String parameter);

    Class getParameterClass();
}
