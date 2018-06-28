package rocks.cleanstone.game.command.parameter;

import javax.annotation.Nullable;

public interface CommandParameter<T> {
    @Nullable
    T get(String parameter);

    @Nullable
    default T get(Class<? super T> parameterClass, String parameter) {
        return get(parameter);
    }

    Class getParameterClass();
}
