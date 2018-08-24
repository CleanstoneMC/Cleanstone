package rocks.cleanstone.game.command.parameter;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.completion.CompletionContext;

import javax.annotation.Nullable;

@Component
public class IntParameter implements CommandParameter<Integer> {
    @Nullable
    @Override
    public Integer get(CompletionContext<Integer> context) {
        try {
            return Integer.parseInt(context.getInput());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Class<Integer> getParameterClass() {
        return Integer.class;
    }
}
