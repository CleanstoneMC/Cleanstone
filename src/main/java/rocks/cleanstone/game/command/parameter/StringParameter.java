package rocks.cleanstone.game.command.parameter;

import javax.annotation.Nullable;
import rocks.cleanstone.game.command.completion.CompletionContext;

public class StringParameter implements CommandParameter<String> {
    @Nullable
    @Override
    public String get(CompletionContext<String> context) {
        return context.getInput();
    }

    @Override
    public Class<String> getParameterClass() {
        return String.class;
    }
}
