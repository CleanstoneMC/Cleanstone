package rocks.cleanstone.game.command.parameter;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import rocks.cleanstone.game.command.completion.CompletableValue;

public class EnumParameter<T extends Enum<T>> implements CommandParameter<T>, CompletableValue<T> {

    @Override
    public List<String> getCompletion(Class<T> parameterClass, String message) {
        return Arrays.stream(parameterClass.getEnumConstants())
                .map(Enum::toString)
                .filter(enumValue -> enumValue.toLowerCase(Locale.ENGLISH)
                        .startsWith(message.toLowerCase(Locale.ENGLISH)))
                .collect(Collectors.toList());
    }

    @Nullable
    @Override
    public T get(String parameter) {
        throw new UnsupportedOperationException("can't get EnumParameter without parameterClass");
    }

    @Nullable
    @Override
    public T get(Class<? super T> parameterClass, String parameter) {
        String lowercaseParameter = parameter.toLowerCase(Locale.ENGLISH);
        return (T) Arrays.stream(parameterClass.getEnumConstants())
                .filter(enumEntry -> enumEntry.toString().toLowerCase(Locale.ENGLISH)
                        .startsWith(lowercaseParameter))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Class getParameterClass() {
        return Enum.class;
    }
}
