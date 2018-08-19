package rocks.cleanstone.game.command.parameter;

import rocks.cleanstone.game.command.completion.CompletableParameter;
import rocks.cleanstone.game.command.completion.CompletionContext;
import rocks.cleanstone.game.command.completion.CompletionMatch;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class EnumParameter<T extends Enum<T>> implements CompletableParameter<T> {

    @Override
    public List<CompletionMatch> getCompletion(CompletionContext<T> context) {
        //noinspection unchecked
        T[] enumConstants = context.getExpectedType().getEnumConstants();

        try {
            int ordinal = Integer.parseInt(context.getInput());

            if (ordinal >= 0 && ordinal < enumConstants.length) {
                String enumConstantName = enumConstants[ordinal].toString();
                return singletonList(new CompletionMatch(enumConstantName.toLowerCase(Locale.ENGLISH)));
            }
        } catch (NumberFormatException ignored) { // not a number, try string completion
        }

        return Arrays.stream(enumConstants)
                .map(Enum::toString)
                .map(c -> c.toLowerCase(Locale.ENGLISH))
                .filter(enumValue -> enumValue.startsWith(context.getInput().toLowerCase(Locale.ENGLISH)))
                .map(CompletionMatch::new)
                .collect(Collectors.toList());
    }

    @Nullable
    @Override
    public T get(CompletionContext<T> context) {
        return getCompletion(context).stream()
                .map(CompletionMatch::getMatch)
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
