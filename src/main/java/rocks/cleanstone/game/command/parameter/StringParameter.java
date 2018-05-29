package rocks.cleanstone.game.command.parameter;

import javax.annotation.Nullable;

public class StringParameter implements CommandParameter<String> {
    @Nullable
    @Override
    public String get(String parameter) {
        return parameter;
    }

    @Override
    public Class getParameterClass() {
        return String.class;
    }
}
