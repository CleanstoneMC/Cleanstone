package rocks.cleanstone.game.command.parameter;

import javax.annotation.Nullable;

public class IntParameter implements CommandParameter<Integer> {
    @Nullable
    @Override
    public Integer get(String parameter) {
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Class getParameterClass() {
        return Integer.class;
    }
}
