package rocks.cleanstone.game.command.parameter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import rocks.cleanstone.game.command.completion.CompletableParameter;
import rocks.cleanstone.game.command.completion.CompletionContext;

public class EnumParameter<T extends Enum<T>> implements CompletableParameter<T> {

    @Override
    public List<String> getCompletion(CompletionContext<T> context) {
        //noinspection unchecked
        T[] enumConstants = context.getExpectedType().getEnumConstants();

        try {
            int ordinal = Integer.parseInt(context.getInput());

            if (ordinal >= 0 && ordinal < enumConstants.length) {
                return Collections.singletonList(enumConstants[ordinal].toString().toLowerCase(Locale.ENGLISH));
            }
        } catch (NumberFormatException ignored) { // not a number, try string completion
        }

        return Arrays.stream(enumConstants)
                .map(Enum::toString)
                .map(c -> c.toLowerCase(Locale.ENGLISH))
                .filter(enumValue -> enumValue.startsWith(context.getInput().toLowerCase(Locale.ENGLISH)))
                .collect(Collectors.toList());
    }

    @Nullable
    @Override
    public T get(CompletionContext<T> context) {
        return getCompletion(context).stream()
                .map(completion -> completion.toUpperCase(Locale.ENGLISH))
                .map(completion -> Enum.valueOf(context.getExpectedType(), completion))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Class getParameterClass() {
        return Enum.class;
    }
}
