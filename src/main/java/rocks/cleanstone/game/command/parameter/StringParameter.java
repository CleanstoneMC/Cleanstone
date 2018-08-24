package rocks.cleanstone.game.command.parameter;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.completion.CompletionContext;

import javax.annotation.Nullable;

@Component
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
